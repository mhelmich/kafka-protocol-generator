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

        Map<String, List<String>> complex = listener.getComplexTypeToDefinitions();
        Map<String, List<String>> primitive = listener.getPrimitiveTypeToDefinitions();

        try (BufferedWriter writer = Files.newBufferedWriter(f)) {
            writer.write(FILE_HEADER);
            writer.newLine();

            for (Map.Entry<String, List<String>> e : complex.entrySet()) {

                generateStruct(writer, e.getKey(), e.getValue(), primitive);

                List<String> lineDef = new LinkedList<>();
                for (String s : e.getValue()) {
                    List<String> pTypes = primitive.get(s);
                    if (pTypes == null) {
                        pTypes = complex.get(s);
                    }
                    String primitiveType = pTypes.get(0);
                    String goType = bnfTypeToGoType(primitiveType);
                    if (goType == null) {
                        // must be a complex type then
                        goType = "*" + gofyName(primitiveType);
                    }
                    lineDef.add(INDENT + gofyName(s) + " " + goType + "\n");
                }

                String goCode = String.format(STRUCT_TEMPLATE, gofyName(e.getKey()), String.join("", lineDef));
                System.err.println(goCode);
                writer.append(goCode);
                writer.newLine();
            }
        }
    }

    private void generateStruct(BufferedWriter writer, String structName, List<String> definitions, Map<String, List<String>> primitive) throws IOException {
        structName = gofyName(structName);
        List<String> goDefinitions = new LinkedList<>();
        for (String def : definitions) {

        }
        String goCode = String.format(STRUCT_TEMPLATE, structName, String.join("", goDefinitions));
        writer.append(goCode);
        writer.newLine();
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
