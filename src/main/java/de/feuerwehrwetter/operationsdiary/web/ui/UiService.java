package de.feuerwehrwetter.operationsdiary.web.ui;

import de.feuerwehrwetter.operationsdiary.core.OperationsDiaryService;
import de.feuerwehrwetter.operationsdiary.web.ui.mapper.OperationsDiaryMapper;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateDiaryEntryDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateOperationDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationsDiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UiService {

    private final OperationsDiaryService operationsDiaryService;
    private final OperationsDiaryMapper operationsDiaryMapper;

    public OperationsDiaryDto getDto() {
        return operationsDiaryMapper.toDto(operationsDiaryService.getOperationsDiary());
    }

    public void createOperation(final CreateOperationDto createOperationDto) {
        operationsDiaryService.create(createOperationDto);
    }

    public void createDiaryEntry(final UUID operationUuid, final CreateDiaryEntryDto createDiaryEntryDto) {
        operationsDiaryService.create(operationUuid, createDiaryEntryDto);
    }
}
