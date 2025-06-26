package de.feuerwehrwetter.operationsdiary.web.ui.mapper;

import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationsDiaryDto;
import org.mapstruct.Mapper;

/**
 * Mapstruct {@link Mapper} to map the {@link de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary}
 */
@Mapper(uses = LocalDateTimeMapper.class)
public interface OperationsDiaryMapper {

    /**
     * From {@link OperationsDiary} to {@link de.feuerwehrwetter.operationsdiary.web.ui.model.DiaryEntryDto}
     *
     * @param operationsDiary the {@link OperationsDiary}
     * @return the {@link OperationsDiaryDto}
     */
    OperationsDiaryDto toDto(OperationsDiary operationsDiary);

}
