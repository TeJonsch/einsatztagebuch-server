package de.feuerwehrwetter.operationsdiary;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = MockConfiguration.class)
public abstract class AbstractBaseTest {
}
