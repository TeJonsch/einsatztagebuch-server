package de.feuerwehrwetter.operationsdiary.web.ui.mapper;

import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public class LocalDateTimeMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    String toDto(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }

}
