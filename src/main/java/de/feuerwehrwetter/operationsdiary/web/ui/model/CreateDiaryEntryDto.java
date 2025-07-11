package de.feuerwehrwetter.operationsdiary.web.ui.model;

import de.feuerwehrwetter.operationsdiary.core.model.MessageType;

public record CreateDiaryEntryDto(
        String message,
        MessageType messageType,
        String reporter,
        String receiver,
        String messageTimestamp,
        String author) {
}
