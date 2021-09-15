package sirius.tinkoff.financialTracker.exceptions;

public class WalletNotFoundException extends EntityNotFoundException {
    public WalletNotFoundException(String message) {
        super(message);
    }
}
