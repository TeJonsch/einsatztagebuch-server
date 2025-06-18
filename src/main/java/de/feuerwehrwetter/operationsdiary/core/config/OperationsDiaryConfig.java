package de.feuerwehrwetter.operationsdiary.core.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.feuerwehrwetter.operationsdiary.persistence.file.gson.LocalDateTimeTypeAdapter;
import de.feuerwehrwetter.operationsdiary.persistence.file.gson.LocalDateTypeAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@EnableConfigurationProperties(OperationsDiaryProperties.class)
public class OperationsDiaryConfig {

    @Bean
    Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();
    }
}
