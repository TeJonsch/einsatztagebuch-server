package de.feuerwehrwetter.operationsdiary.core.model;

import java.util.List;

/**
 * Top level model for the operations diary
 *
 * @param operations the fire department operations which are documented
 */
public record OperationsDiary(List<Operation> operations) {
}
