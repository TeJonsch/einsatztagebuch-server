package de.feuerwehrwetter.operationsdiary.core;

import de.feuerwehrwetter.operationsdiary.core.model.Operation;
import de.feuerwehrwetter.operationsdiary.persistence.file.FilePersitenceService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@Service
public class OperationsDiaryService {

    private final FilePersitenceService filePersitenceService;

    @PostConstruct
    void loadFromFile() throws IOException {
        filePersitenceService.loadFromFile();
    }

    public void create(final Operation operation) throws IOException {
        OperationsDiaryHolder.getOperationsDiary().operations().add(operation);
        filePersitenceService.writeToFile();
    }
}
