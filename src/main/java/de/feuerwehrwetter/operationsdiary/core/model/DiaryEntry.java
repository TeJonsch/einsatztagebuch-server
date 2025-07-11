package de.feuerwehrwetter.operationsdiary.core.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * An entry of the operations diary
 *
 * @param uuid              the unique identifier - created by this service
 * @param creationTimestamp creatino timestamp of the entry - created by this service
 * @param messageTimestamp  timestamp when the message occurred - must be in the past
 * @param message           the message
 */
public record DiaryEntry(
        UUID uuid,
        LocalDateTime creationTimestamp,
        LocalDateTime messageTimestamp,
        String message,
        MessageType messageType,
        String reporter,
        String receiver,
        String author) {
}
