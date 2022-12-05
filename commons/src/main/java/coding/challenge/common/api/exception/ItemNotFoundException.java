package coding.challenge.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <code>ItemNotFoundException</code> Тип ошибки для случаев,
 * когда из хранилища невозможно найти запрашиваемую сущность
 *
 * @author Tigran Ellarian
 * @since 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Указанный элемент не существует")
public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4437533439322946020L;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
}
