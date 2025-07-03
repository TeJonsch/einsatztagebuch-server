package de.feuerwehrwetter.operationsdiary.web.rest;

import de.feuerwehrwetter.operationsdiary.web.ui.UiService;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateDiaryEntryDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateOperationDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationsDiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UiController {

    private final UiService uiService;

    @GetMapping("/operations-diary")
    OperationsDiaryDto getOperationsDiaryDto() {
        return uiService.getDto();
    }

    @PostMapping("/operations-diary")
    void createOperation(@RequestBody CreateOperationDto createOperationDto) throws IOException {
        uiService.createOperation(createOperationDto);
    }

    @PostMapping("/operations-diary/{operationUuid}")
    void createOperation(@PathVariable UUID operationUuid, @RequestBody CreateDiaryEntryDto createDiaryEntryDto) throws IOException {
        uiService.createDiaryEntry(operationUuid, createDiaryEntryDto);
    }
}
