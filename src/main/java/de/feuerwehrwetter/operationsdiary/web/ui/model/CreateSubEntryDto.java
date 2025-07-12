package de.feuerwehrwetter.operationsdiary.web.ui.model;

import de.feuerwehrwetter.operationsdiary.core.model.RelationType;

public record CreateSubEntryDto(RelationType relationType,
                                DiaryEntryDto sunEntryDto) {
}
