package de.feuerwehrwetter.operationsdiary.web.ui.model;

public record CreateOperationDto(
        String controlCenterId,
        String operationStartTimestamp,
        String alarmKeyword) {
}
