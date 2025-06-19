package de.feuerwehrwetter.operationsdiary.web.ui;

import de.feuerwehrwetter.operationsdiary.AbstractBaseTest;
import de.feuerwehrwetter.operationsdiary.core.OperationsDiaryHolder;
import de.feuerwehrwetter.operationsdiary.core.model.DiaryEntry;
import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import de.feuerwehrwetter.operationsdiary.web.ui.model.DiaryEntryDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationsDiaryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UiServiceTest extends AbstractBaseTest {

    private static final UUID UUID = java.util.UUID.fromString("c16fc0a0-8626-44c1-9a6d-1be1439f0eb9");
    private static final LocalDateTime CREATION_TIMESTAMP = LocalDateTime.of(2025, 2, 3, 13, 11);
    private static final String CREATION_TIMESTAMP_STRING = "2025-02-03T13:11:00";
    private static final LocalDateTime MESSAGE_TIMESTAMP = LocalDateTime.of(2025, 2, 3, 13, 2);
    private static final String MESSAGE_TIMESTAMP_STRING = "2025-02-03T13:02:00";
    private static final String MESSAGE = "my message";

    @Autowired
    private UiService uiService;

    @Test
    void operationsDiaryIsMappedAccordingly() {
        final var operationsDiary = createOperationsDiary();
        storeInHolder(operationsDiary);

        final OperationsDiaryDto dto = uiService.getDto();

        assertThat(dto.entries()).hasSize(1);

        final DiaryEntryDto entry = dto.entries().getFirst();
        assertThat(entry.uuid()).isEqualTo(UUID.toString());
        assertThat(entry.creationTimestamp()).isEqualTo(CREATION_TIMESTAMP_STRING);
        assertThat(entry.messageTimestamp()).isEqualTo(MESSAGE_TIMESTAMP_STRING);
        assertThat(entry.message()).isEqualTo(MESSAGE);
    }

    private void storeInHolder(final OperationsDiary operationsDiary) {
        OperationsDiaryHolder.setOperationsDiary(operationsDiary);
    }

    private OperationsDiary createOperationsDiary() {
        final List<DiaryEntry> diaryEntries = List.of(new DiaryEntry(UUID, CREATION_TIMESTAMP, MESSAGE_TIMESTAMP, MESSAGE));

        return new OperationsDiary(diaryEntries);
    }
}