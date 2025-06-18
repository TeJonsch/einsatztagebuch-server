package de.feuerwehrwetter.operationsdiary.core;

import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import lombok.Getter;
import lombok.Setter;

public class OperationsDiaryHolder {

    @Getter
    @Setter
    private static OperationsDiary operationsDiary;

}
