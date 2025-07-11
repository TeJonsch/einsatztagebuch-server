package de.feuerwehrwetter.operationsdiary.web.ui.model;

import de.feuerwehrwetter.operationsdiary.core.model.MessageType;

public record DiaryEntryDto(
        String uuid,
        String creationTimestamp,
        String messageTimestamp,
        String message,
        MessageType messageType,
        String reporter,
        String receiver,
        String author) {
}
