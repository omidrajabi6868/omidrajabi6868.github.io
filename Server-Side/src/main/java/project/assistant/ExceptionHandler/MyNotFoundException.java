package project.assistant.ExceptionHandler;

public class MyNotFoundException extends RuntimeException {

    public MyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
