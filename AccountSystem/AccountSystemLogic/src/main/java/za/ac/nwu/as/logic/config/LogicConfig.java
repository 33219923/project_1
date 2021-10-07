package za.ac.nwu.as.logic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.ac.nwu.as.translator.config.TranslatorConfig;

@Import({TranslatorConfig.class})
@ComponentScan({"za.ac.nwu.as.logic.services"})
@Configuration
public class LogicConfig {
}
