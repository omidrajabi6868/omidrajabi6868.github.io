package project.assistant.ExceptionHandler;

public class MyIdMismatchException extends RuntimeException {

    public MyIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
