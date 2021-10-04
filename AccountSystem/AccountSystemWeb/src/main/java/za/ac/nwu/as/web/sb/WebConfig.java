package za.ac.nwu.as.web.sb;

import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan(basePackages = {
        "za.ac.nwu.ac.web.sb.controller",
        "za.ac.nwu.ac.web.sb.exception"
})
public class WebConfig {
}