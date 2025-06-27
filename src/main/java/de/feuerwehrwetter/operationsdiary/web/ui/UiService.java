package de.feuerwehrwetter.operationsdiary.web.ui;

import de.feuerwehrwetter.operationsdiary.core.OperationsDiaryService;
import de.feuerwehrwetter.operationsdiary.core.model.Operation;
import de.feuerwehrwetter.operationsdiary.web.ui.mapper.OperationsDiaryMapper;
import de.feuerwehrwetter.operationsdiary.web.ui.model.CreateOperationDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationsDiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UiService {

    private final OperationsDiaryService operationsDiaryService;
    private final OperationsDiaryMapper operationsDiaryMapper;

    public OperationsDiaryDto getDto() {
        return operationsDiaryMapper.toDto(operationsDiaryService.getOperationsDiary());
    }

    public void createOperation(final CreateOperationDto createOperationDto) throws IOException {
        operationsDiaryService.create(createOperationDto);
    }
}
