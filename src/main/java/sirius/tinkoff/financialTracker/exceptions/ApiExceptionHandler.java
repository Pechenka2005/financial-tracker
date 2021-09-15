package sirius.tinkoff.financialTracker.exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sirius.tinkoff.financialTracker.models.ErrorResponse;

import java.util.Objects;

@EnableWebMvc
@ControllerAdvice(basePackages = {"sirius.tinkoff.financialTracker.controllers"})
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Value("${reflectoring.trace:false}")
    private boolean printStackTrace;

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotFoundException(
            Exception exception,
            WebRequest request
    ) {
        logger.error("Failed to find the requested element", exception);
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({AuthorizationException.class, MissingAuthorizationHeaderException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object> handleAuthorizationException(
            Exception exception,
            WebRequest request
    ) {
        logger.error("Authorization exception", exception);
        return buildErrorResponse(exception, HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({ValidationException.class, InvalidEmailException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleValidationException(
            Exception exception,
            WebRequest request
    ) {
        logger.error("Validation exception", exception);
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ExistingUserException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleExistingResource(
            Exception exception,
            WebRequest request
    ) {
        logger.error("Existing resource", exception);
        return buildErrorResponse(exception, HttpStatus.CONFLICT, request);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Ошибка валидации. Проверьте поле 'errors' для более детальной информации."
        );

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        return buildErrorResponse(
                exception,
                exception.getMessage(),
                httpStatus,
                request
        );
    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            String message,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                httpStatus.value(),
                exception.getMessage()
        );

        if (printStackTrace && isTraceOn(request))
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private boolean isTraceOn(WebRequest request) {
        String [] value = request.getParameterValues("trace");
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }
}
