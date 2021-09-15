package sirius.tinkoff.financialTracker.security;

import sirius.tinkoff.financialTracker.models.User;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<String> login(String username, String password);
    Optional<User> findByToken(String token);
}
