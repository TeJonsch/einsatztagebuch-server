package de.feuerwehrwetter.operationsdiary;

import de.feuerwehrwetter.operationsdiary.persistence.file.FilePersitenceService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class MockConfiguration {

    @Primary
    @Bean
    FilePersitenceService filePersitenceService() {
        return mock(FilePersitenceService.class);
    }

}
