package sirius.tinkoff.financialTracker.converters;

import org.springframework.stereotype.Component;
import sirius.tinkoff.financialTracker.models.Wallet;
import sirius.tinkoff.financialTracker.models.dto.CreateWalletDTO;
import sirius.tinkoff.financialTracker.repository.CurrencyRepository;

import java.util.Optional;

@Component
public class CreateWalletDTOToWalletConverter {
    private final CurrencyRepository currencyRepository;

    public CreateWalletDTOToWalletConverter(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Optional<Wallet> convert(CreateWalletDTO dto) {
        var foundCurrency = currencyRepository
                .findByShortStrIgnoreCase(dto.getCurrencyShortStr());
        return foundCurrency
                .map(currency -> new Wallet()
                    .setLimit(dto.getLimit())
                    .setName(dto.getName())
                    .setCurrency(currency)
                    .setHidden(dto.isHidden()));
    }
}
