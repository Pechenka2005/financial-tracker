package sirius.tinkoff.financialTracker.exceptions;

public class InvalidEmailException extends AuthorizationException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
