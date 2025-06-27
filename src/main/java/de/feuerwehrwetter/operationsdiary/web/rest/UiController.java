package de.feuerwehrwetter.operationsdiary.web.rest;

import de.feuerwehrwetter.operationsdiary.core.model.DiaryEntry;
import de.feuerwehrwetter.operationsdiary.core.model.Operation;
import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import de.feuerwehrwetter.operationsdiary.persistence.file.FilePersitenceService;
import de.feuerwehrwetter.operationsdiary.web.ui.UiService;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateOperationDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationsDiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UiController {

    private final UiService uiService;
    private final FilePersitenceService filePersitenceService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/operations-diary")
    OperationsDiaryDto getOperationsDiaryDto() {
        return uiService.getDto();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/operations-diary")
    void createOperation(@RequestBody CreateOperationDto createOperationDto) throws IOException {
        uiService.createOperation(createOperationDto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/operations-diary/debug")
    void createDebugEntries() throws IOException {
        final List<DiaryEntry> diaryEntries = List.of(
                newEntry("Hallo Herr EL....", 9, 2),
                newEntry("LtS sagt was!", 24, 24),
                newEntry("Noch was", 26, 26)
        );

        final OperationsDiary operationsDiary = new OperationsDiary(List.of(new Operation(UUID.randomUUID(), "ABC", LocalDateTime.now(), "F0 - Kleinbrand", diaryEntries)));
        filePersitenceService.writeToFile(operationsDiary);
    }

    private static DiaryEntry newEntry(final String message, final int creationTsMinute, final int messageTsMinute) {
        return new DiaryEntry(UUID.randomUUID(), getTimestampWith(creationTsMinute), getTimestampWith(messageTsMinute), message);
    }

    private static LocalDateTime getTimestampWith(final int minute) {
        return LocalDateTime.of(2025, 1, 23, 10, minute);
    }
}
