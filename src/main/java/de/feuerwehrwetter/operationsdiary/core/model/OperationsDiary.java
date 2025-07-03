package de.feuerwehrwetter.operationsdiary.core.model;

import java.util.Map;
import java.util.UUID;

/**
 * Top level model for the operations diary
 *
 * @param operations the fire department operations which are documented
 */
public record OperationsDiary(Map<UUID, Operation> operations) {
}
