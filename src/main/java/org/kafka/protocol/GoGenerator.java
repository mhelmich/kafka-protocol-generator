package org.kafka.protocol;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class GoGenerator implements CodeGenerator {

    private final static Map<String, String> bnfTypeToGoType = ImmutableMap.<String, String>builder()
            .put("INT64", "int64")
            .put("INT32", "int32")
            .put("INT16", "int16")
            .put("INT8", "int8")
            .put("STRING", "string")
            .put("NULLABLE_STRING", "string")
            .put("BYTES", "[]byte")
            .put("BOOLEAN", "boolean")
            .put("RECORDS", "[]byte")
            .build();

    private final static String INDENT = "    ";
    private final static String FILE_HEADER = "" +
            "package kafka-protocol\n" +
            "import ()\n";

    private final static String STRUCT_TEMPLATE = "" +
            "type %s struct {\n" +
            "%s" +
            "}\n";

    @Override
    public void generateGoSourceFile(KafkaProtocolListener listener, Path testFolder) throws IOException {
        Path entireFileName = getPath(listener, testFolder);
        Files.deleteIfExists(entireFileName);
        Path f = Files.createFile(entireFileName);

        Map<String, List<ComplexType>> complex = listener.getComplexTypeToDefinitions();
        Map<String, List<String>> primitive = listener.getPrimitiveTypeToDefinitions();

        try (BufferedWriter writer = Files.newBufferedWriter(f)) {
            writer.write(FILE_HEADER);
            writer.newLine();

            for (Map.Entry<String, List<ComplexType>> e : complex.entrySet()) {
                generateStruct(writer, e.getKey(), e.getValue(), primitive);
                generateFunctions(writer);
            }
        }
    }

    private void generateStruct(BufferedWriter writer, String structName, List<ComplexType> definitions, Map<String, List<String>> primitive) throws IOException {
        // skip gofying names in case it's a request or response because they come gofied already
        if (!Character.isDigit(structName.charAt(structName.length()-1))) {
            structName = gofyName(structName);
        }
        List<String> goDefinitions = new LinkedList<>();
        for (ComplexType def : definitions) {
            String goType;
            List<String> primitiveTypeList = primitive.get(def.name);
            if (primitiveTypeList == null) {
                // this is a complex type that is defined later
                // for all intents and purposes type and field name are called the same
                String gofiedName = gofyName(def.name);
                goType = def.isArray ? "*[]" + gofiedName : "*" + gofiedName;
                goDefinitions.add(INDENT + gofiedName + " " + goType + "\n");
            } else {
                // this is a regular primitive field
                // in the end I hope everything resolves to this
                String primitiveType = primitiveTypeList.get(0);
                goType = bnfTypeToGoType(primitiveType);
                goDefinitions.add(INDENT + gofyName(def.name) + " " + goType + "\n");
            }
        }

        // join the struct definitions together and feed them into the template
        String goCode = String.format(STRUCT_TEMPLATE, structName, String.join("", goDefinitions));
        writer.append(goCode);
        writer.newLine();
    }

    private void generateFunctions(BufferedWriter writer) {

    }

    private String bnfTypeToGoType(String bnfType) {
        return bnfTypeToGoType.get(bnfType);
    }

    private Path getPath(KafkaProtocolListener listener, Path testFolder) {
        String fileName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, listener.getFileName());
        return Paths.get(testFolder.toAbsolutePath().toString() + File.separator + fileName + ".go");
    }

    private String gofyName(String name) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }
}
