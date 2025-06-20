package de.feuerwehrwetter.operationsdiary.web.ui.model;

import de.feuerwehrwetter.operationsdiary.core.model.DiaryEntry;

import java.util.List;

public record OperationDto(
        String uuid,
        String controlCenterId,
        String operationStartTimestamp,
        String alarmKeyword,
        List<DiaryEntry> diaryEntries) {
}
