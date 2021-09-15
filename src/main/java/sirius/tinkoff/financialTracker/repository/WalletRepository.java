package sirius.tinkoff.financialTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.tinkoff.financialTracker.models.Wallet;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findById(Long id);
    List<Wallet> findAllByOrderByIdDesc();
    List<Wallet> findAllByUserId(Long userId);
}
