package de.feuerwehrwetter.operationsdiary.core.model;

import java.util.List;

/**
 * Top level model for the operations diary
 *
 * @param entries the diary entry list
 */
public record OperationsDiary(List<DiaryEntry> entries) {
}
