package sirius.tinkoff.financialTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.tinkoff.financialTracker.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByIdDesc();

    Optional<User> findById(Long id);

    void deleteById(Long id);

    Optional<User> findByUsername(String username);

}
