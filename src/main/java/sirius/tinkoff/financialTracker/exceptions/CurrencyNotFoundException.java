package sirius.tinkoff.financialTracker.exceptions;

public class CurrencyNotFoundException extends EntityNotFoundException {
    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
