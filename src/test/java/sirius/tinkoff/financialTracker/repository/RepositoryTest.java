package sirius.tinkoff.financialTracker.repository;

import org.springframework.boot.test.context.SpringBootTest;
import sirius.tinkoff.financialTracker.configuration.EmbeddedPgConfiguration;
import sirius.tinkoff.financialTracker.configuration.RepositoryConfiguration;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = {RepositoryConfiguration.class, EmbeddedPgConfiguration.class})
@Inherited
public @interface RepositoryTest {
}
