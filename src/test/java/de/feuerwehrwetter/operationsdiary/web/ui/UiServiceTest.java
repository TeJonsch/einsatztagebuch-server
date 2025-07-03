package de.feuerwehrwetter.operationsdiary.web.ui;

import de.feuerwehrwetter.operationsdiary.AbstractBaseTest;
import de.feuerwehrwetter.operationsdiary.core.OperationsDiaryService;
import de.feuerwehrwetter.operationsdiary.core.model.DiaryEntry;
import de.feuerwehrwetter.operationsdiary.core.model.Operation;
import de.feuerwehrwetter.operationsdiary.core.model.OperationsDiary;
import de.feuerwehrwetter.operationsdiary.web.ui.model.DiaryEntryDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationDto;
import de.feuerwehrwetter.operationsdiary.web.ui.model.OperationsDiaryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UiServiceTest extends AbstractBaseTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private static final UUID OPERATION_UUID = UUID.fromString("c16fc0a0-8626-44c1-9a6d-1be1439f0eb9");
    private static final UUID DIARY_UUID = UUID.fromString("e52fc7a0-8626-44c1-9a6d-1be1439f0eb9");

    private static final String OPERATION_START_TIMESTAMP_STRING = "01.02.2025 13:11:02";
    private static final LocalDateTime OPERATION_START_TIMESTAMP = LocalDateTime.parse(OPERATION_START_TIMESTAMP_STRING, FORMATTER);
    private static final String ALARM_KEYWORD = "ALARMKEYWORD";
    public static final String CONTROL_CENTER_ID = "12345";

    private static final String CREATION_TIMESTAMP_STRING = "05.11.2026 01:23:27";
    private static final LocalDateTime CREATION_TIMESTAMP = LocalDateTime.parse(CREATION_TIMESTAMP_STRING, FORMATTER);
    private static final String MESSAGE_TIMESTAMP_STRING = "11.12.2027 23:59:59";
    private static final LocalDateTime MESSAGE_TIMESTAMP = LocalDateTime.parse(MESSAGE_TIMESTAMP_STRING, FORMATTER);
    private static final String MESSAGE = "my message";

    @Autowired
    private UiService uiService;

    @MockitoBean
    private OperationsDiaryService operationsDiaryService;

    @Test
    void operationsDiaryIsMappedAccordingly() {
        final var operationsDiary = createOperationsDiary();
        when(operationsDiaryService.getOperationsDiary()).thenReturn(operationsDiary);

        final OperationsDiaryDto operationsDiaryDto = uiService.getDto();

        assertThat(operationsDiaryDto.operations()).hasSize(1);

        final OperationDto operationDto = operationsDiaryDto.operations().getFirst();
        assertThat(operationDto.uuid()).isEqualTo(OPERATION_UUID.toString());
        assertThat(operationDto.alarmKeyword()).isEqualTo(ALARM_KEYWORD);
        assertThat(operationDto.operationStartTimestamp()).isEqualTo(OPERATION_START_TIMESTAMP_STRING);
        assertThat(operationDto.controlCenterId()).isEqualTo(CONTROL_CENTER_ID);

        assertThat(operationDto.diaryEntries()).hasSize(1);

        final DiaryEntryDto diaryEntry = operationDto.diaryEntries().getFirst();
        assertThat(diaryEntry.uuid()).isEqualTo(DIARY_UUID.toString());
        assertThat(diaryEntry.creationTimestamp()).isEqualTo(CREATION_TIMESTAMP_STRING);
        assertThat(diaryEntry.messageTimestamp()).isEqualTo(MESSAGE_TIMESTAMP_STRING);
        assertThat(diaryEntry.message()).isEqualTo(MESSAGE);
    }

    private OperationsDiary createOperationsDiary() {
        final List<DiaryEntry> diaryEntries = List.of(new DiaryEntry(DIARY_UUID, CREATION_TIMESTAMP, MESSAGE_TIMESTAMP, MESSAGE));
        final Operation operation = new Operation(OPERATION_UUID, CONTROL_CENTER_ID, OPERATION_START_TIMESTAMP, ALARM_KEYWORD, diaryEntries);
        return new OperationsDiary(Map.of(operation.uuid(), operation));
    }
}