package de.feuerwehrwetter.operationsdiary.core.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "operations-diary")
public record OperationsDiaryProperties(

        @NotNull
        Resource path

) {
}
