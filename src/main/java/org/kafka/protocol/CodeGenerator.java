package org.kafka.protocol;

import java.io.IOException;
import java.nio.file.Path;

@FunctionalInterface
interface CodeGenerator {
    void generateGoSourceFile(KafkaProtocolListener listener, Path testFolder) throws IOException;
}
