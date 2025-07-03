package de.feuerwehrwetter.operationsdiary.web.ui.mapper;

import de.feuerwehrwetter.operationsdiary.core.model.Operation;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationMapper {

    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    OperationDto mapToDto(Operation operation);

}
