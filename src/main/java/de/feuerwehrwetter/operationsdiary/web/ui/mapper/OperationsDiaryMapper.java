package de.feuerwehrwetter.operationsdiary.web.ui.mapper;

import de.feuerwehrwetter.operationsdiary.core.model.Operation;
import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationsDiaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    @Mapping(source = "operationsDiary.operations", target = "operations", qualifiedByName = "mapToList")
    OperationsDiaryDto toDto(OperationsDiary operationsDiary);

    OperationDto toDto(Operation operation);

    @Named("mapToList")
    default List<OperationDto> mapToList(Map<UUID, Operation> operationMap) {
        return operationMap.values().stream()
                .map(this::toDto)
                .toList();
    }
}
