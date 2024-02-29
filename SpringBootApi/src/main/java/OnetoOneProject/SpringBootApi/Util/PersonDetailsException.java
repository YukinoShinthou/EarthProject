package OnetoOneProject.SpringBootApi.Util;

import java.security.Timestamp;


public class PersonDetailsException extends RuntimeException{
    private String message;
    private Timestamp timestamp;
    public PersonDetailsException(StringBuilder message) {
        this.message = String.valueOf(message);
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
