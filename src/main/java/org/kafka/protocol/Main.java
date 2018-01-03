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

package org.kafka.protocol;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.kafka.protocol.gen.KafkaProtocolLexer;
import org.kafka.protocol.gen.KafkaProtocolParser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private final static Pattern regexp = Pattern.compile("<pre>(.|\\n)*?<\\/pre>");
    private final static Path testFolder = Paths.get("/Users/marco.helmich/go/src/kafkaprotocol");
    private final static CodeGenerator codeGen = new GoGenerator();

    public static void main(String[] args) throws IOException {

//        if (args.length != 2) {
//            printUsage();
//            return;
//        }

        Main main = new Main();
//        main.run(Paths.get(args[0], args[1]);
        main.run(Paths.get(testFolder.toString(), "test.txt").toString(), testFolder.toString());
    }

    private static void printUsage() {
        System.out.println("usage:");
        System.out.println("gen <source file> <folder to generate files into>");
    }

    private void run(String source, String destFolder) throws IOException {
        Path originalFile = Paths.get(source);
        Path dest = Paths.get(destFolder);
        System.out.println("Reading in file " + originalFile.toAbsolutePath().toString());
        System.out.println("Writing output into " + testFolder.toAbsolutePath().toString());
        Path filteredPath = Files.createTempFile("filtered-", "");
        filterOriginalFile(originalFile, filteredPath);
        generateGoFiles(filteredPath, dest);
        System.out.println("Generation finished!!");
    }

    private void filterOriginalFile(Path src, Path dest) throws IOException {
        StringBuilder sb = new StringBuilder();
        Files.readAllLines(src).forEach(sb::append);
        Matcher m = regexp.matcher(sb.toString());

        try (BufferedWriter writer = Files.newBufferedWriter(dest)) {
            while (m.find()) {
                String match = m.group();
                writer.append(match);
                writer.newLine();
            }
        }
    }

    private void generateGoFiles(Path file, Path dest) throws IOException {
        List<String> lines = Files.readAllLines(file);
        for (String line : lines) {
            CharStream stream = CharStreams.fromString(line);
            KafkaProtocolLexer lexer = new KafkaProtocolLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            KafkaProtocolParser parser = new KafkaProtocolParser(tokens);
            ParseTree tree = parser.bnf_element();
            KafkaProtocolListener listener = new KafkaProtocolListener();
            ParseTreeWalker.DEFAULT.walk(listener, tree);
            generateGoSourceFile(listener, dest);
        }
    }

    private void generateGoSourceFile(KafkaProtocolListener listener, Path dest) {
        try {
            codeGen.generateGoSourceFile(listener, dest);
        } catch (Exception e) {
            listener.dump();
            e.printStackTrace();
        }
    }
}
