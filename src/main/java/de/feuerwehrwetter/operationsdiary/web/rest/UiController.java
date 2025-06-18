package de.feuerwehrwetter.operationsdiary.web.rest;

import de.feuerwehrwetter.operationsdiary.web.ui.UiService;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationsDiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UiController {

    private final UiService uiService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/timesheet")
    OperationsDiaryDto getOperationsDiaryDto() {
        return uiService.getDto();
    }

}
