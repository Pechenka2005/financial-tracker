package sirius.tinkoff.financialTracker.security;

import sirius.tinkoff.financialTracker.models.User;
import sirius.tinkoff.financialTracker.repository.UserRepository;

import java.util.Optional;

public class AuthenticationService implements UserAuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<String> login(String username, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByToken(String token) {
        return userRepository.findByUsername(token);
    }
}
