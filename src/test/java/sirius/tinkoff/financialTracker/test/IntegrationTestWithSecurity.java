package sirius.tinkoff.financialTracker.test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import sirius.tinkoff.financialTracker.configuration.IntegrationTestConfiguration;
import sirius.tinkoff.financialTracker.configuration.SecurityConfiguration;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {IntegrationTestConfiguration.class, SecurityConfiguration.class})
@Inherited
public @interface IntegrationTestWithSecurity {
}
