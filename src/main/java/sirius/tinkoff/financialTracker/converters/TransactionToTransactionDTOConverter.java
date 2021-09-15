package sirius.tinkoff.financialTracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sirius.tinkoff.financialTracker.models.Transaction;
import sirius.tinkoff.financialTracker.models.dto.TransactionDTO;

@Component
public class TransactionToTransactionDTOConverter {
    private final CategoryToCategoryDTOConverter categoryConverter = new CategoryToCategoryDTOConverter();
    private final CurrencyToCurrencyDTOConverter currencyConverter = new CurrencyToCurrencyDTOConverter();

    public TransactionDTO convert(Transaction transaction) {
        return new TransactionDTO()
                .setIsIncome(transaction.getIsIncome())
                .setCategory(categoryConverter.convert(transaction.getCategory()))
                .setCurrency(currencyConverter.convert(transaction.getCurrency()))
                .setId(transaction.getId())
                .setTs(transaction.getTs())
                .setValue(transaction.getValue());
    }
}
