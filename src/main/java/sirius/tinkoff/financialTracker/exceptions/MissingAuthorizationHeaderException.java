package sirius.tinkoff.financialTracker.exceptions;

public class MissingAuthorizationHeaderException extends AuthorizationException {
    public MissingAuthorizationHeaderException(String message) {
        super(message);
    }
}
