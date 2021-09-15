package sirius.tinkoff.financialTracker.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.ws.rs.HttpMethod;
import java.util.Objects;

public class SecurityAdapter extends WebSecurityConfigurerAdapter {
    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/users/", HttpMethod.POST),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/v3/api-docs/**")
    );
    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

    private final TokenAuthenticationProvider provider;
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    public SecurityAdapter(TokenAuthenticationProvider provider) {
        super();
        this.provider = Objects.requireNonNull(provider);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PUBLIC_URLS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                    .defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), PROTECTED_URLS)
                    .and()
                .authorizeRequests()
                    .requestMatchers(PUBLIC_URLS).permitAll()
                    .requestMatchers(PROTECTED_URLS).authenticated()
                .and()
                .authenticationProvider(provider)
                    .addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter.class)
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }

    public TokenAuthenticationFilter restAuthenticationFilter() throws Exception {
        if (tokenAuthenticationFilter == null) {
            var filter = new TokenAuthenticationFilter(PROTECTED_URLS);
            filter.setAuthenticationManager(authenticationManager());
            filter.setAuthenticationSuccessHandler(successHandler());
            tokenAuthenticationFilter = filter;
        }
        return tokenAuthenticationFilter;
    }

    public SimpleUrlAuthenticationSuccessHandler successHandler() {
        var successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setRedirectStrategy(new NoRedirectStrategy());
        return successHandler;
    }

    public AuthenticationEntryPoint forbiddenEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
    }


}
