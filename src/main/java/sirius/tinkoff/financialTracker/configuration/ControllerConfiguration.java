package sirius.tinkoff.financialTracker.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("sirius.tinkoff.financialTracker.controllers")
@ComponentScan("sirius.tinkoff.financialTracker.exceptions")
public class ControllerConfiguration {
}
