package de.feuerwehrwetter.operationsdiary.web.ui.model;

import java.util.List;

public record OperationsDiaryDto(List<DiaryEntryDto> entries) {
}
