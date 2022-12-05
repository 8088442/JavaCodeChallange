package coding.challenge.common.api.controller;

import coding.challenge.common.api.model.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeType;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@ControllerAdvice
public class ServletExceptionHandler extends BasicExceptionHandler {

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorModel processException(HttpMediaTypeNotSupportedException e) {

        log.error(e.getLocalizedMessage(), e);

        return ErrorModel.builder()
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message(String.format(
                        "%s media type is not supported for this request. Available types are: %s",
                        e.getContentType(),
                        e.getSupportedMediaTypes().stream()
                                .map(MimeType::toString)
                                .collect(Collectors.joining(","))
                ))
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorModel processException(HttpRequestMethodNotSupportedException e) {

        log.error(e.getLocalizedMessage(), e);
        return ErrorModel.builder()
                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message(String.format(
                        "%s http type is not supported for this request. Available types are: %s",
                        e.getMethod(),
                        Optional.ofNullable(e.getSupportedMethods()).stream()
                                .flatMap(Stream::of)
                                .collect(Collectors.joining(","))
                ))
                .build();
    }
}
