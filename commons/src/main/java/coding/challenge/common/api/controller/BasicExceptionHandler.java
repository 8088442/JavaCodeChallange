package coding.challenge.common.api.controller;

import coding.challenge.common.api.exception.ResourceNotFoundException;
import coding.challenge.common.api.model.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorModel processException(AccessDeniedException e) {

        log.error(e.getLocalizedMessage(), e);

        return createErrorModel(
                HttpStatus.FORBIDDEN.value(),
                e,
                "Not enough rights to perform this operation"
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel processException(HttpMessageNotReadableException e) {

        log.error(e.getLocalizedMessage(), e);

        return createErrorModel(
                HttpStatus.BAD_REQUEST.value(),
                e,
                String.format("Invalid request body: %s", e.getLocalizedMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel processException(MethodArgumentNotValidException e) {

        log.error(e.getLocalizedMessage(), e);

        StringBuilder sb = new StringBuilder();
        for (FieldError item : e.getFieldErrors()) {
            sb
                    .append(String.format(
                            "Incorrect value [%s] at field [%s]",
                            item.getRejectedValue(),
                            item.getField()
                    ))
                    .append(System.lineSeparator());
        }
        String formattedErrorString = sb.toString();
        log.error(formattedErrorString);

        return ErrorModel.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(formattedErrorString)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel processException(ConstraintViolationException e) {
        log.error(e.getLocalizedMessage(), e);

        return ErrorModel.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(String.format("Constraint violation: %s", e.getLocalizedMessage()))
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel processException(DataIntegrityViolationException e) {
        log.error(e.getLocalizedMessage(), e);

        return ErrorModel.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(String.format("Integrity violation: %s", e.getLocalizedMessage()))
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel processException(ResourceNotFoundException e) {
        log.error(e.getLocalizedMessage());

        return ErrorModel.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(String.format("%s", e.getLocalizedMessage()))
                .build();
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel processException(WebExchangeBindException e) {

        log.error(e.getLocalizedMessage(), e);

        StringBuilder sb = new StringBuilder();
        for (FieldError item : e.getFieldErrors()) {
            sb
                    .append(String.format(
                            "Incorrect value [%s] at field [%s]",
                            item.getRejectedValue(),
                            item.getField()
                    ))
                    .append(System.lineSeparator());
        }
        String formattedErrorString = sb.toString();
        log.error(formattedErrorString);

        return ErrorModel.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(formattedErrorString)
                .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public ResponseEntity<ErrorModel> processException(ResponseStatusException e) {
        log.error(e.getLocalizedMessage(), e);

        return ResponseEntity.status(e.getRawStatusCode()).body(
                ErrorModel.builder()
                        .code(e.getRawStatusCode())
                        .message(String.format("Invalid request body: %s", e.getReason()))
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorModel processException(Exception e) {
        log.error(e.getLocalizedMessage(), e);

        return ErrorModel.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Internal server error")
                .build();
    }

    protected ErrorModel createErrorModel(int code, final Exception e, final String m) {
        StringBuilder sb = new StringBuilder();

        if (Objects.nonNull(e)) {
            sb.append("exception message: ")
                    .append(e.getLocalizedMessage())
                    .append(System.getProperty("line.separator"));
        }
        Optional.ofNullable(m).ifPresent(sb::append);

        return ErrorModel.builder()
                .code(code)
                .message(sb.toString())
                .build();
    }
}
