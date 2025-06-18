package de.feuerwehrwetter.operationsdiary.web.ui;

import de.feuerwehrwetter.operationsdiary.core.model.DiaryEntry;
import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class UiServiceTest {

    @Autowired
    private UiService uiService;

    @Test
    void operationsDiaryIsMappedAccordingly() {
        final var operationsDiary = createOperationsDiary();
        storeInHolder(operationsDiary);

        uiService.getDto();
    }

    private void storeInHolder(final OperationsDiary operationsDiary) {

    }

    private OperationsDiary createOperationsDiary() {
        final List<DiaryEntry> diaryEntries = List.of(new DiaryEntry(null, null, null, null));

        return new OperationsDiary(diaryEntries);
    }

    @Test
    void test() {
        System.out.println(UUID.randomUUID());
    }

}