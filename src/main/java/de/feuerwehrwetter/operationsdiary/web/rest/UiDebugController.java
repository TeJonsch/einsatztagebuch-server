package de.feuerwehrwetter.operationsdiary.web.rest;

import de.feuerwehrwetter.operationsdiary.core.OperationsDiaryService;
import de.feuerwehrwetter.operationsdiary.core.model.MessageType;
import de.feuerwehrwetter.operationsdiary.core.model.Operation;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateDiaryEntryDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateOperationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static de.feuerwehrwetter.operationsdiary.SpringProfile.DEBUG;

@Profile(DEBUG)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UiDebugController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final OperationsDiaryService operationsDiaryService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/operations-diary/debug")
    void createDebugEntries() {
        final Operation operation = operationsDiaryService.create(new CreateOperationDto("1234542", getFormattedTimestamp(), "F0"));
        operationsDiaryService.create(operation.uuid(), newCreateDiaryEntryDto("Doch Schlimmm 7!"));
        operationsDiaryService.create(operation.uuid(), newCreateDiaryEntryDto("Alles paletti"));
        operationsDiaryService.create(operation.uuid(), newCreateDiaryEntryDto("Huuup"));
    }

    private static CreateDiaryEntryDto newCreateDiaryEntryDto(final String message) {
        return new CreateDiaryEntryDto(
                message,
                MessageType.TASK,
                "reporter",
                null,
                getFormattedTimestamp(),
                "author");
    }

    private static String getFormattedTimestamp() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
