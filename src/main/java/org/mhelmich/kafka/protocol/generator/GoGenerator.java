/*
 * Copyright 2018 Marco Helmich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mhelmich.kafka.protocol.generator;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
            .put("RECORDS", "*RecordBatch") // yes, this is a pre-existing go class
            .build();

    private final static String COPYRIGHT = "" +
            "/*\n" +
            " * Copyright 2018 Marco Helmich\n" +
            " *\n" +
            " * Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            " * you may not use this file except in compliance with the License.\n" +
            " * You may obtain a copy of the License at\n" +
            " *\n" +
            " *     http://www.apache.org/licenses/LICENSE-2.0\n" +
            " *\n" +
            " * Unless required by applicable law or agreed to in writing, software\n" +
            " * distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            " * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            " * See the License for the specific language governing permissions and\n" +
            " * limitations under the License.\n" +
            " */\n";

    private final static String INDENT = "    ";
    private final static String FILE_HEADER = "" +
            "package kafkawireformat\n" +
            "import ()\n";

    private final static String STRUCT_TEMPLATE = "" +
            "type %s struct {\n" +
            "%s" +
            "}\n";

    private final static String DECODE_TEMPLATE = "" +
            "func (that *%s) Decode(dec *Decoder) error {\n" +
            "%s\n" +
            "}\n";

    private final static String PRIMITIVE_DECODING_TEMPLATE =
            INDENT + "that.%s = dec.%s()\n";

    private final static String COMPLEX_DECODING_TEMPLATE =
            INDENT + "that.%s = new(%s)\n" +
            INDENT + "that.%s.Decode(dec)\n";

    private final static String COMPLEX_ARRAY_DECODING_TEMPLATE = "" +
            INDENT + "{\n" +
            INDENT + INDENT + "arrayLength := dec.ReadInt32()\n" +
            INDENT + INDENT + "if int(arrayLength) == -1 {\n" +
            INDENT + INDENT + INDENT + "that.%s = nil\n" +
            INDENT + INDENT + "} else {\n" +
            INDENT + INDENT + INDENT + "buf := make([]%s, arrayLength)\n" +
            INDENT + INDENT + INDENT + "var i int32\n" +
            INDENT + INDENT + INDENT + "for i = 0; i < arrayLength; i++ {\n" +
            INDENT + INDENT + INDENT + INDENT + "item := new(%s)\n" +
            INDENT + INDENT + INDENT + INDENT + "item.Decode(dec)\n" +
            INDENT + INDENT + INDENT + INDENT + "buf[i] = item\n" +
            INDENT + INDENT + INDENT + "}\n" +
            INDENT + INDENT + INDENT + "that.%s = buf\n" +
            INDENT + INDENT + "}\n" +
            INDENT + "}\n";

    private final static String ENCODE_TEMPLATE = "" +
            "func (that *%s) Encode(enc *Encoder) error {\n" +
            "%s\n" +
            "}\n";

    private final static String PRIMITIVE_ENCODING_TEMPLATE = INDENT +
            "enc.%s(that.%s)";

    private final static String COMPLEX_ENCODING_TEMPLATE =
            INDENT + "that.%s.Encode(enc)\n";

    private final static String COMPLEX_ARRAY_ENCODING_TEMPLATE = "" +
            INDENT + "{\n" +
            INDENT + INDENT + "arrayLength := len(that.%s)\n" +
            INDENT + INDENT + "enc.WriteInt32(int32(arrayLength))\n" +
            INDENT + INDENT + "for i := 0; i < arrayLength; i++ {\n" +
            INDENT + INDENT + INDENT + "that.%s[i].Encode(enc)\n" +
            INDENT + INDENT + "}\n" +
            INDENT + "}\n" +
            "";

    @Override
    public void generateGoSourceFile(KafkaProtocolListener listener, Path testFolder) throws IOException {
        seedBaseFilesIntoDirectory(testFolder);
        Path entireFileName = getPath(listener, testFolder);
        Files.deleteIfExists(entireFileName);
        Path f = Files.createFile(entireFileName);

        Map<String, List<TypeDefinition>> complex = listener.getComplexTypeToDefinitions();
        Map<String, List<TypeDefinition>> primitive = listener.getPrimitiveTypeToDefinitions();

        try (BufferedWriter writer = Files.newBufferedWriter(f)) {
            writer.write(COPYRIGHT);
            writer.newLine();
            writer.write(FILE_HEADER);
            writer.newLine();

            String rootStructName;
            if (listener.getVersionNumber() != null && !complex.isEmpty()) {
                rootStructName = findRootStructName(complex.keySet());
            } else {
                rootStructName = "";
            }

            if (complex.isEmpty()) {
                generateEmptyStubs(writer, listener.getFileName().replace("_", ""));
            } else {
                for (Map.Entry<String, List<TypeDefinition>> e : complex.entrySet()) {
                    List<MemberVar> memberVars = massageData(e.getValue(), primitive);
                    // skip gofying names in case it's a request or response because they come gofied already
                    String structName = goifyStructName(e.getKey(), rootStructName);
                    generateStruct(writer, structName, memberVars, rootStructName);
                    generateEncodeFunction(writer, structName, memberVars);
                    generateDecodeFunction(writer, structName, memberVars, rootStructName);
                }
            }
        }
    }

    private void generateEmptyStubs(BufferedWriter writer, String structName) throws IOException {
        String goCode = String.format(STRUCT_TEMPLATE, structName, "");
        writer.append(goCode);
        writer.newLine();

        goCode = String.format(ENCODE_TEMPLATE, structName, INDENT + "return nil\n");
        writer.append(goCode);
        writer.newLine();

        goCode = String.format(DECODE_TEMPLATE, structName, INDENT + "return nil\n");
        writer.append(goCode);
        writer.newLine();
    }

    private String findRootStructName(Set<String> complexNames) {
        return complexNames.stream().filter(this::endsWithDigit).collect(Collectors.toList()).get(0);
    }

    private String goifyStructName(String structName, String rootStructName) {
        // skip gofying names in case it's a request or response because they come gofied already
        return endsWithDigit(structName) || structName.equals("RequestHeader") || structName.equals("ResponseHeader")
                ? structName
                : namespaceStructName(gofyName(structName), rootStructName);
    }

    private String namespaceStructName(String structName, String rootStructName) {
        return rootStructName + "_" + structName;
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
            List<MemberVar> memberVars,
            String rootStructName
    ) throws IOException {
        List<String> goDefinitions = new LinkedList<>();

        for (MemberVar member : memberVars) {
            if (member.isComplex) {
                // this is a complex type that is defined later
                // for all intents and purposes type and field name are called the same
                String goType = member.isArray ? "[]*" + namespaceStructName(member.name, rootStructName) : "*" + namespaceStructName(member.name, rootStructName);
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
        List<String> assignments = new LinkedList<>();
        for (MemberVar member : memberVars) {
            if (!member.isArray && (member.type.equals("int8") || member.type.equals("int16") || member.type.equals("int32") || member.type.equals("int64") || member.type.equals("string") || member.type.equals("bool"))) {
                assignments.add(String.format(PRIMITIVE_ENCODING_TEMPLATE, "Write" + gofyName(member.type), member.name));
            } else if(member.isArray && member.isComplex) {
                assignments.add(String.format(COMPLEX_ARRAY_ENCODING_TEMPLATE, member.name, member.name));
            } else if (member.isArray && (member.type.equals("int32") || member.type.equals("string") || member.type.equals("int64"))) {
                assignments.add(String.format(PRIMITIVE_ENCODING_TEMPLATE, "Write" + gofyName(member.type) + "Array", member.name));
            } else if (!member.isArray && member.type.equals("[]byte")) {
                assignments.add(String.format(PRIMITIVE_ENCODING_TEMPLATE, "WriteByteArray", member.name));
            } else if (!member.isArray && member.type.equals("*RecordBatch")) {
                assignments.add(String.format(COMPLEX_ENCODING_TEMPLATE, member.name));
            } else if(member.isComplex && !member.isArray) {
                assignments.add(String.format(COMPLEX_ENCODING_TEMPLATE, member.name));
            } else {
                System.err.println("Can't encode type in " + structName +": " + member.type + " array " + member.isArray);
            }
        }
        assignments.add(INDENT + "return nil");
        String goCode = String.format(ENCODE_TEMPLATE, structName, String.join("\n", assignments));
        writer.append(goCode);
        writer.newLine();
    }

    private void generateDecodeFunction(BufferedWriter writer, String structName, List<MemberVar> memberVars, String rootStructName) throws IOException {
        List<String> assignments = new LinkedList<>();
        for (MemberVar member : memberVars) {
            if (!member.isArray && (member.type.equals("int8") || member.type.equals("int16") || member.type.equals("int32") || member.type.equals("int64") || member.type.equals("string") || member.type.equals("bool"))) {
                assignments.add(String.format(PRIMITIVE_DECODING_TEMPLATE, member.name, "Read" + gofyName(member.type)));
            } else if (member.isArray && (member.type.equals("int32") || member.type.equals("string") || member.type.equals("int64"))) {
                assignments.add(String.format(PRIMITIVE_DECODING_TEMPLATE, member.name, "Read" + gofyName(member.type) + "Array"));
            } else if(member.isComplex && !member.isArray) {
                String type = member.type.substring(1, member.type.length());
                type = rootStructName + "_" + type;
                assignments.add(String.format(COMPLEX_DECODING_TEMPLATE, member.name, type, member.name));
            } else if (!member.isArray && member.type.equals("*RecordBatch")) {
                assignments.add(String.format(COMPLEX_DECODING_TEMPLATE, member.name, "RecordBatch", member.name));
            } else if (member.isComplex && member.isArray) {
                String type = member.type.substring(3, member.type.length());
                type = rootStructName + "_" + type;
                assignments.add(String.format(COMPLEX_ARRAY_DECODING_TEMPLATE, member.name, "*" + type, type, member.name));
            } else if(member.type.equals("[]byte")) {
                assignments.add(String.format(PRIMITIVE_DECODING_TEMPLATE, member.name, "ReadByteArray"));
            } else {
                System.err.println("Can't decode type in " + structName + ": " + member.type + " array " + member.isArray);
            }
        }

        assignments.add(INDENT + "return nil");
        String goCode = String.format(DECODE_TEMPLATE, structName, String.join("", assignments));
        writer.append(goCode);
        writer.newLine();
    }

    private String primitiveAssignment(String varName, String methodName) {
        return String.format(PRIMITIVE_DECODING_TEMPLATE, varName, methodName);
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

    private void seedBaseFilesIntoDirectory(Path testFolder) {
        String decoder = "/decoder.go";
        Path filePath = Paths.get(testFolder.toAbsolutePath().toString(), decoder);
        if (!Files.exists(filePath)) {
            try (InputStream in = this.getClass().getResourceAsStream(decoder)) {
                Files.deleteIfExists(filePath);
                Files.copy(in, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String encoder = "/encoder.go";
        filePath = Paths.get(testFolder.toAbsolutePath().toString(), encoder);
        if (!Files.exists(filePath)) {
            try (InputStream in = this.getClass().getResourceAsStream(encoder)) {
                Files.deleteIfExists(filePath);
                Files.copy(in, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String records = "/records.go";
        filePath = Paths.get(testFolder.toAbsolutePath().toString(), records);
        if (!Files.exists(filePath)) {
            try (InputStream in = this.getClass().getResourceAsStream(records)) {
                Files.deleteIfExists(filePath);
                Files.copy(in, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String encoderDecoder = "/encoder_decoder_test.go";
        filePath = Paths.get(testFolder.toAbsolutePath().toString(), encoderDecoder);
        if (!Files.exists(filePath)) {
            try (InputStream in = this.getClass().getResourceAsStream(encoderDecoder)) {
                Files.deleteIfExists(filePath);
                Files.copy(in, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
