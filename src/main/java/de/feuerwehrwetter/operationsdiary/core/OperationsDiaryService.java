package de.feuerwehrwetter.operationsdiary.core;

import de.feuerwehrwetter.operationsdiary.core.model.DiaryEntry;
import de.feuerwehrwetter.operationsdiary.core.model.Operation;
import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import de.feuerwehrwetter.operationsdiary.persistence.file.FilePersitenceService;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateDiaryEntryDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateOperationDto;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
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

    public Operation create(final CreateOperationDto createOperationDto) {
        final Operation operation = createOperationFrom(createOperationDto);

        operationsDiary.operations().put(operation.uuid(), operation);
        filePersitenceService.writeToFile(operation);

        return operation;
    }

    public void create(final UUID operationUuid, final CreateDiaryEntryDto createDiaryEntryDto) {
        final Operation operation = operationsDiary.operations().get(operationUuid);
        Objects.requireNonNull(operation); // TODO: improve error handling

        operation.diaryEntries().add(createDiaryEntryFrom(createDiaryEntryDto));
        filePersitenceService.writeToFile(operation);
    }

    private static Operation createOperationFrom(final CreateOperationDto createOperationDto) {
        return new Operation(
                UUID.randomUUID(),
                createOperationDto.controlCenterId(),
                LocalDateTime.parse(createOperationDto.operationStartTimestamp()),
                createOperationDto.alarmKeyword(),
                new ArrayList<>());
    }

    private static DiaryEntry createDiaryEntryFrom(final CreateDiaryEntryDto createDiaryEntryDto) {
        return new DiaryEntry(
                UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.parse(createDiaryEntryDto.messageTimestamp()),
                createDiaryEntryDto.message());
    }
}
