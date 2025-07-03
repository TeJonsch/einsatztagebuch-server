package de.feuerwehrwetter.operationsdiary.web.ui.model;

public record CreateDiaryEntryDto(
        String messageTimestamp,
        String message) {
}
