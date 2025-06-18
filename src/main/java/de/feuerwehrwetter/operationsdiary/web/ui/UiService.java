package de.feuerwehrwetter.operationsdiary.web.ui;

import de.feuerwehrwetter.operationsdiary.core.OperationsDiaryHolder;
import de.feuerwehrwetter.operationsdiary.web.ui.mapper.OperationsDiaryMapper;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationsDiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class UiService {

    private final OperationsDiaryMapper operationsDiaryMapper;

    public OperationsDiaryDto getDto() {
        return operationsDiaryMapper.toDto(OperationsDiaryHolder.getOperationsDiary());
    }

}
