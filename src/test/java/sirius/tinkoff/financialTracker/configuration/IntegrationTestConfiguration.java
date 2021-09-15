package sirius.tinkoff.financialTracker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RepositoryConfiguration.class,
        EmbeddedPgConfiguration.class,
        ServiceConfiguration.class,
        ControllerConfiguration.class,
        JsonMappingConfiguration.class
})
public class IntegrationTestConfiguration {
}
