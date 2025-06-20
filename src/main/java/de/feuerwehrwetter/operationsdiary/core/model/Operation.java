package de.feuerwehrwetter.operationsdiary.core.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The fire department operation in which the documented events occure.
 *
 * @param uuid                    the unique identifier - created by this service
 * @param controlCenterId         the operation id of the fire department control center
 * @param operationStartTimestamp the start timestamp of the operation
 * @param alarmKeyword            the alarm keyword (e.g. F0 - Kleinbrand)
 * @param diaryEntries            the diary operationDtos
 */
public record Operation(
        UUID uuid,
        String controlCenterId,
        LocalDateTime operationStartTimestamp,
        String alarmKeyword,
        List<DiaryEntry> diaryEntries) {
}
