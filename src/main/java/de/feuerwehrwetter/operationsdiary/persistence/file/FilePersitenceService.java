package de.feuerwehrwetter.operationsdiary.persistence.file;

import com.google.gson.Gson;
import de.feuerwehrwetter.operationsdiary.core.OperationsDiaryHolder;
import de.feuerwehrwetter.operationsdiary.core.config.OperationsDiaryProperties;
import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@Service
public class FilePersitenceService {

    private final OperationsDiaryProperties properties;
    private final Gson gson;

    public void loadFromFile() throws IOException {
        log.info("Loading operations diary file");

        createFileIfNotExists();

        final var operationsDiary = parseJson();
        storeInHolder(operationsDiary);
    }

    public void writeToFile() throws IOException {
        log.info("Writing operations diary file");

        try (final FileWriter writer = new FileWriter(properties.path().getFile())) {
            gson.toJson(OperationsDiaryHolder.getOperationsDiary(), writer);
        }
    }

    private OperationsDiary parseJson() throws IOException {
        return gson.fromJson(new FileReader(properties.path().getFile()), OperationsDiary.class);
    }

    private void storeInHolder(final OperationsDiary operationsDiary) {
        OperationsDiaryHolder.setOperationsDiary(Objects.requireNonNullElseGet(operationsDiary, this::newEmptyOperationsDiary));
    }

    private OperationsDiary newEmptyOperationsDiary() {
        return new OperationsDiary(new ArrayList<>());
    }

    private void createFileIfNotExists() throws IOException {
        if (!properties.path().exists()) {
            Files.createFile(properties.path().getFile().toPath());
        }
    }
}
