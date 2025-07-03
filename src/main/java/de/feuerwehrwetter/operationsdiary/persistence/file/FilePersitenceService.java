package de.feuerwehrwetter.operationsdiary.persistence.file;

import com.google.gson.Gson;
import de.feuerwehrwetter.operationsdiary.core.config.OperationsDiaryProperties;
import de.feuerwehrwetter.operationsdiary.core.model.Operation;
import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@RequiredArgsConstructor
@Service
public class FilePersitenceService {

    private final OperationsDiaryProperties properties;
    private final Gson gson;

    public OperationsDiary loadFromFile() throws IOException {
        log.info("Loading operations diary file");

        createDirIfNotExists();

        return loadOperationFilesAndParseJson();
    }

    public void writeToFile(Operation operation) {
        log.info("Writing operation '{}' to file", operation.uuid());
        createOperationFile(operation);
    }

    private void createOperationFile(Operation operation) {
        try (final FileWriter writer = new FileWriter(getPathOf(operation).toFile())) {
            gson.toJson(operation, writer);
        } catch (IOException e) {
            log.error("Failed to write operation file", e);
        }
    }

    private Path getPathOf(final Operation operation) throws IOException {
        return properties.dir().resolve(properties.filenameTemplate().formatted(operation.uuid()));
    }

    private OperationsDiary loadOperationFilesAndParseJson() throws IOException {
        try (Stream<Path> pathStream = Files.list(properties.dir())) {
            final Map<UUID, Operation> operationMap = pathStream
                    .filter(Files::isRegularFile)
                    .map(this::loadOperationFile)
                    .collect(Collectors.toMap(
                            Operation::uuid,
                            Function.identity()));

            return new OperationsDiary(operationMap);
        }
    }

    private Operation loadOperationFile(Path path) {
        try {
            return gson.fromJson((Reader) new FileReader(path.toFile()), Operation.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDirIfNotExists() throws IOException {
        if (!Files.exists(properties.dir())) {
            Files.createDirectories(properties.dir());
        }
    }
}
