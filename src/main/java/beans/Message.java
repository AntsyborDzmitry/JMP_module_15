package beans;

import java.time.LocalDate;

public class Message {

    private String message ;
    private LocalDate timestamp ;

    public Message(String message) {
        this.message = message;
        this.timestamp = LocalDate.now();
    }

    public Message(String message, LocalDate timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
