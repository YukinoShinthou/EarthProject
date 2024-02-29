package OnetoOneProject.SpringBootApi.Util;

import java.sql.Time;
import java.sql.Timestamp;

public class UserNotFoundException extends RuntimeException{
    private String message;
    private Timestamp timestamp;

    public UserNotFoundException(String message, Timestamp timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
