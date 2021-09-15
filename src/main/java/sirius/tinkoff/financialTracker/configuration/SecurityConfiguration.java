package sirius.tinkoff.financialTracker.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import sirius.tinkoff.financialTracker.repository.UserRepository;
import sirius.tinkoff.financialTracker.security.AuthenticationService;
import sirius.tinkoff.financialTracker.security.SecurityAdapter;
import sirius.tinkoff.financialTracker.security.TokenAuthenticationFilter;
import sirius.tinkoff.financialTracker.security.TokenAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityAdapter securityAdapter(TokenAuthenticationProvider tokenAuthenticationProvider) {
        return new SecurityAdapter(tokenAuthenticationProvider);
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(SecurityAdapter securityAdapter) throws Exception {
        return securityAdapter.restAuthenticationFilter();
    }

    @Bean
    public FilterRegistrationBean<TokenAuthenticationFilter> disableAutoRegistration(TokenAuthenticationFilter tokenAuthenticationFilter) {
        var registration = new FilterRegistrationBean<>(tokenAuthenticationFilter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public AuthenticationService authenticationService(UserRepository userRepository) {
        return new AuthenticationService(userRepository);
    }

    @Bean
    public TokenAuthenticationProvider tokenAuthenticationProvider(AuthenticationService authenticationService) {
        return new TokenAuthenticationProvider(authenticationService);
    }
}
