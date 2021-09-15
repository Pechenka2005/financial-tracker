package sirius.tinkoff.financialTracker.converters;

import org.springframework.stereotype.Component;
import sirius.tinkoff.financialTracker.models.Category;
import sirius.tinkoff.financialTracker.models.Currency;
import sirius.tinkoff.financialTracker.models.Transaction;
import sirius.tinkoff.financialTracker.models.Wallet;
import sirius.tinkoff.financialTracker.models.dto.CreateTransactionDTO;
import sirius.tinkoff.financialTracker.repository.CategoryRepository;
import sirius.tinkoff.financialTracker.repository.CurrencyRepository;
import sirius.tinkoff.financialTracker.repository.WalletRepository;

import java.util.Optional;

@Component
public class CreateTransactionDTOToTransactionConverter {
    private final WalletRepository walletRepository;
    private final CurrencyRepository currencyRepository;
    private final CategoryRepository categoryRepository;

    public CreateTransactionDTOToTransactionConverter(
            WalletRepository walletRepository,
            CurrencyRepository currencyRepository,
            CategoryRepository categoryRepository
    ) {
        this.currencyRepository = currencyRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
    }

    public Optional<Transaction> convert(CreateTransactionDTO dto) {
        Optional<Currency> foundCurrency = currencyRepository.findByShortStrIgnoreCase(dto.getCurrencyShortStr());
        Optional<Category> foundCategory = categoryRepository.findById(dto.getCategoryId());
        Optional<Wallet> foundWallet = walletRepository.findById(dto.getWalletId());

        if (foundCategory.isPresent() && foundCurrency.isPresent() && foundWallet.isPresent())
            return Optional.of(new Transaction()
                    .setCurrency(foundCurrency.get())
                    .setValue(dto.getValue())
                    .setIsIncome(dto.getIsIncome())
                    .setCategory(foundCategory.get())
                    .setWalletId(foundWallet.get().getId())
                    .setTs(dto.getTs()));

        return Optional.empty();
    }
}
