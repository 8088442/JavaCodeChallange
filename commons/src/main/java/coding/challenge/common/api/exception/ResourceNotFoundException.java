package coding.challenge.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Element does not exist")
public class ResourceNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 9033396227462205572L;

  public ResourceNotFoundException(String resourceName, String resourceId) {
    super("resource {" + resourceName + "} with id {" + resourceId + "} not found");
  }
}
