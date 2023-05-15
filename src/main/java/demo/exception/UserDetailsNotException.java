package demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class UserDetailsNotException extends RuntimeException {
    public UserDetailsNotException(String message) {
        super(message);
    }

}
