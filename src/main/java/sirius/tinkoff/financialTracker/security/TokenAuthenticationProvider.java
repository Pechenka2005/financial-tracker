package sirius.tinkoff.financialTracker.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import sirius.tinkoff.financialTracker.exceptions.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public final class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final AuthenticationService authenticationService;

    @Override
    protected void additionalAuthenticationChecks(final UserDetails d, final UsernamePasswordAuthenticationToken auth) {

    }

    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken auth) {
        final Object token = auth.getCredentials();
        return Optional
                .ofNullable(token)
                .map(String::valueOf)
                .flatMap(authenticationService::findByToken)
                .orElseThrow(() -> new UsernameNotFoundException("Не удалось найти пользователя с email " + token));
    }
}
