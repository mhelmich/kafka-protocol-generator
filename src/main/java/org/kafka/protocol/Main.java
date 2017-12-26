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
    private final static Pattern regexp = Pattern.compile("<pre>(.|\\n)*?<\\/pre>", Pattern.MULTILINE);
    private final static Path testFolder = Paths.get("/Users/marco.helmich/go/src/go-kafka-protocol");
    private final static CodeGenerator codeGen = new GoGenerator();

    public static void main(String[] args) throws IOException {

//        if (args.length != 1) {
//            printUsage();
//            return;
//        }

        Main main = new Main();
//        String filename = args[0];
        Path originalFile = Paths.get(testFolder.toString(), "test.txt");
        Path filteredPath = Files.createTempFile("filtered-", "");
        main.filterOriginalFile(originalFile, filteredPath);
        main.generateGoFiles(filteredPath);
    }

    private static void printUsage() {
        System.out.println("usage:");
        System.out.println("gen <source file> <folder to generate files into>");
    }

    private void filterOriginalFile(Path src, Path dest) throws IOException {
        StringBuilder sb = new StringBuilder();
        Files.readAllLines(src).forEach(sb::append);
        Matcher m = regexp.matcher(sb.toString());

        try (BufferedWriter writer = Files.newBufferedWriter(dest)) {
            while (m.find()) {
                String match = m.group();
                System.err.println(match);
                writer.append(match);
                writer.newLine();
            }
        }
    }

    private void generateGoFiles(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        for (String line : lines) {
            CharStream stream = CharStreams.fromString(line);
            KafkaProtocolLexer lexer = new KafkaProtocolLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            KafkaProtocolParser parser = new KafkaProtocolParser(tokens);
            ParseTree tree = parser.bnf_element();
            KafkaProtocolListener listener = new KafkaProtocolListener();
            ParseTreeWalker.DEFAULT.walk(listener, tree);
            generateGoSourceFile(listener);
        }
    }

    private void generateGoSourceFile(KafkaProtocolListener listener) {
        try {
            codeGen.generateGoSourceFile(listener, testFolder);
        } catch (Exception e) {
            listener.dump();
            e.printStackTrace();
        }
    }
}
