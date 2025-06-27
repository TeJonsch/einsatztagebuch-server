package de.feuerwehrwetter.operationsdiary.web.ui.model;

import java.util.List;

public record OperationDto(
        String uuid,
        String controlCenterId,
        String operationStartTimestamp,
        String alarmKeyword,
        List<DiaryEntryDto> diaryEntries) {
}
