package sirius.tinkoff.financialTracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sirius.tinkoff.financialTracker.models.Wallet;
import sirius.tinkoff.financialTracker.models.dto.WalletDTO;

@Component
public class WalletToWalletDTOConverter {
    private final CurrencyToCurrencyDTOConverter currencyConverter = new CurrencyToCurrencyDTOConverter();

    public WalletDTO convert(Wallet wallet) {
        return new WalletDTO()
                .setId(wallet.getId())
                .setName(wallet.getName())
                .setLimit(wallet.getLimit())
                .setCurrency(currencyConverter.convert(wallet.getCurrency()))
                .setHidden(wallet.isHidden());
    }
}
