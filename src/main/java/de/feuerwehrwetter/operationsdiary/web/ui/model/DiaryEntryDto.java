package de.feuerwehrwetter.operationsdiary.web.ui.model;

public record DiaryEntryDto(
        String uuid,
        String creationTimestamp,
        String messageTimestamp,
        String message) {
}
