package de.feuerwehrwetter.operationsdiary.core.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.nio.file.Path;

@Validated
@ConfigurationProperties(prefix = "operations-diary")
public record OperationsDiaryProperties(

        @NotNull
        Path dir,

        @NotNull
        String filenameTemplate
) {
}
