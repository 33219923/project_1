package za.ac.nwu.as.translator.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.ac.nwu.as.repository.config.RepositoryConfig;

@Import({RepositoryConfig.class})
@ComponentScan(basePackages = {"za.ac.nwu.as.translator.services"})
@Configuration
public class TranslatorConfig {
}
