package sirius.tinkoff.financialTracker.converters;

import org.springframework.stereotype.Component;
import sirius.tinkoff.financialTracker.models.Currency;
import sirius.tinkoff.financialTracker.models.dto.CurrencyDTO;

@Component
public class CurrencyToCurrencyDTOConverter {
    CurrencyDTO convert(Currency currency) {
        return new CurrencyDTO()
                .setLongStr(currency.getLongStr())
                .setShortStr(currency.getShortStr())
                .setSymbol(currency.getSymbol());
    }
}
