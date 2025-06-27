package de.feuerwehrwetter.operationsdiary.core;

import de.feuerwehrwetter.operationsdiary.core.model.Operation;
import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import de.feuerwehrwetter.operationsdiary.persistence.file.FilePersitenceService;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateOperationDto;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class OperationsDiaryService {

    private final FilePersitenceService filePersitenceService;

    @Getter
    private OperationsDiary operationsDiary;

    @PostConstruct
    void loadFromFile() throws IOException {
        operationsDiary = filePersitenceService.loadFromFile();
    }

    public void create(final CreateOperationDto createOperationDto) throws IOException {
        final Operation operation = createOperationFrom(createOperationDto);

        operationsDiary.operations().add(operation);
        filePersitenceService.writeToFile(operationsDiary);
    }

    private static Operation createOperationFrom(final CreateOperationDto createOperationDto) {
        return new Operation(UUID.randomUUID(),
                createOperationDto.controlCenterId(),
                LocalDateTime.parse(createOperationDto.operationStartTimestamp()),
                createOperationDto.alarmKeyword(),
                List.of());
    }
}
