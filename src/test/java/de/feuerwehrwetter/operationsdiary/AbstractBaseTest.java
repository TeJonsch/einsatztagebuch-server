package de.feuerwehrwetter.operationsdiary;

import de.feuerwehrwetter.operationsdiary.persistence.file.FilePersitenceService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public abstract class AbstractBaseTest {

    @MockitoBean
    FilePersitenceService filePersitenceService;

}
