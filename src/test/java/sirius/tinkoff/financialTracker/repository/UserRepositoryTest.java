package sirius.tinkoff.financialTracker.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sirius.tinkoff.financialTracker.models.User;

import static org.assertj.core.api.Assertions.assertThat;

@RepositoryTest
class UserRepositoryTest {


    @Autowired
    public UserRepository userRepository;

    @Test
    public void findById() {
        User user = userRepository.save(new User().setUsername("user"));
        User saved = userRepository.findById(user.getId()).get();
        assertThat(user).isEqualTo(saved);
    }

}