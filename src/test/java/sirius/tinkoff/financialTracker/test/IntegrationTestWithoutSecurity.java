package sirius.tinkoff.financialTracker.test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import sirius.tinkoff.financialTracker.configuration.IntegrationTestConfiguration;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
@Inherited
public @interface IntegrationTestWithoutSecurity {
}
