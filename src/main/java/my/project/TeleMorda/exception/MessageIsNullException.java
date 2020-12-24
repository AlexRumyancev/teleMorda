package my.project.TeleMorda.exception;

public class MessageIsNullException extends RuntimeException{

    public MessageIsNullException() {
        super();
    }

    public MessageIsNullException(String message) {
        super(message);
    }
}
