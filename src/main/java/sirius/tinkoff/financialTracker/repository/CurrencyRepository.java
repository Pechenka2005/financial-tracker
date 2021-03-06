package sirius.tinkoff.financialTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.tinkoff.financialTracker.models.Currency;

import java.util.Optional;
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findById(Long id);
    Optional<Currency> findByShortStrIgnoreCase(String shortStr);
}
