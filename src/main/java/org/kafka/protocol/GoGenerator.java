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

    private final static Map<String, String> bnfTypeToGoType = ImmutableMap
            .<String, String>builder()
            .put("INT64", "int64")
            .put("INT32", "int32")
            .put("INT16", "int16")
            .put("INT8", "int8")
            .put("STRING", "string")
            .put("NULLABLE_STRING", "string")
            .put("BYTES", "[]byte")
            .put("BOOLEAN", "bool")
            .put("RECORDS", "[]byte")
            .build();

    private final static String INDENT = "    ";
    private final static String FILE_HEADER = "" +
            "package kafkaprotocol\n" +
            "import ()\n";

    private final static String STRUCT_TEMPLATE = "" +
            "type %s struct {\n" +
            "%s" +
            "}\n";

    private final static String DECODE_TEMPLATE = "" +
            "func (dec *Decodable) decode() err {\n" +
            "%s\n" +
            "}\n";

    private final static String PRIMITIVE_ASSIGNMENT_TEMPLATE = "" +
            "%s := dec.%s()\n";

    private final static String COMPLEX_ASSIGNMENT_TEMPLATE = "" +
            "";

    private final static String ENCODE_TEMPLATE = "" +
            "func (enc *Encodable) encode() err {\n" +
            "%s\n" +
            "}\n";

    @Override
    public void generateGoSourceFile(KafkaProtocolListener listener, Path testFolder) throws IOException {
        Path entireFileName = getPath(listener, testFolder);
        Files.deleteIfExists(entireFileName);
        Path f = Files.createFile(entireFileName);

        Map<String, List<TypeDefinition>> complex = listener.getComplexTypeToDefinitions();
        Map<String, List<TypeDefinition>> primitive = listener.getPrimitiveTypeToDefinitions();

        try (BufferedWriter writer = Files.newBufferedWriter(f)) {
            writer.write(FILE_HEADER);
            writer.newLine();

            for (Map.Entry<String, List<TypeDefinition>> e : complex.entrySet()) {
                List<MemberVar> memberVars = massageData(e.getValue(), primitive);
                // skip gofying names in case it's a request or response because they come gofied already
                String structName = goifyStructName(e.getKey()) + listener.getVersionNumber();
                generateStruct(writer, structName, memberVars);
                generateEncodeFunction(writer, structName, memberVars);
                generateDecodeFunction(writer, structName, memberVars);
            }
        }
    }

    private String goifyStructName(String structName) {
        // skip gofying names in case it's a request or response because they come gofied already
        return endsWithDigit(structName) ? structName : gofyName(structName);
    }

    private List<MemberVar> massageData(List<TypeDefinition> definitions, Map<String, List<TypeDefinition>> primitive) {
        List<MemberVar> memberVars = new LinkedList<>();
        for (TypeDefinition def : definitions) {
            final String gofiedName = gofyName(def.name);
            final boolean isComplex;
            final boolean isArray;
            final String goType;
            List<TypeDefinition> primitiveTypeList = primitive.get(def.name);
            if (primitiveTypeList == null) {
                // this is a complex type that is defined later
                // for all intents and purposes type and field name are called the same
                goType = def.isArray ? "[]*" + gofiedName : "*" + gofiedName;
                isComplex = true;
                isArray = def.isArray;
            } else {
                // this is a regular primitive field
                // in the end I hope everything resolves to this
                TypeDefinition primitiveType = primitiveTypeList.get(0);
                goType = bnfTypeToGoType(primitiveType.name);
                isComplex = false;
                isArray = def.isArray;
            }
            memberVars.add(new MemberVar(gofiedName, isComplex, isArray, goType));
        }
        return memberVars;
    }

    private void generateStruct(
            BufferedWriter writer,
            String structName,
            List<MemberVar> memberVars
    ) throws IOException {
        List<String> goDefinitions = new LinkedList<>();

        for (MemberVar member : memberVars) {
            if (member.isComplex) {
                // this is a complex type that is defined later
                // for all intents and purposes type and field name are called the same
                String goType = member.isArray ? "[]*" + member.name : "*" + member.name;
                goDefinitions.add(INDENT + member.name + " " + goType + "\n");
            } else {
                // this is a regular primitive field
                // in the end I hope everything resolves to this
                String goType = member.isArray ? "[]" + member.type : member.type;
                goDefinitions.add(INDENT + member.name + " " + goType + "\n");
            }
        }

        // join the struct definitions together and feed them into the template
        String goCode = String.format(STRUCT_TEMPLATE, structName, String.join("", goDefinitions));
        writer.append(goCode);
        writer.newLine();
    }

    private void generateEncodeFunction(BufferedWriter writer, String structName, List<MemberVar> memberVars) throws IOException {

    }

    private void generateDecodeFunction(BufferedWriter writer, String structName, List<MemberVar> memberVars) throws IOException {
        List<String> assignments = new LinkedList<>();
        String goCode = String.format(DECODE_TEMPLATE, String.join("\n", assignments));
        writer.append(goCode);
        writer.newLine();
    }

    private String primitiveAssignment(String varName, String methodName) {
        return String.format(PRIMITIVE_ASSIGNMENT_TEMPLATE, varName, methodName);
    }

    private boolean endsWithDigit(String s) {
        return Character.isDigit(s.charAt(s.length() - 1));
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

    private static class MemberVar {
        final String name;
        final boolean isComplex;
        final boolean isArray;
        final String type;

        MemberVar(String name, boolean isComplex, boolean isArray, String type) {
            this.name = name;
            this.isComplex = isComplex;
            this.isArray = isArray;
            this.type = type;
        }

        @Override
        public String toString() {
            return name + " - " + isComplex + " - " + type;
        }
    }
}
