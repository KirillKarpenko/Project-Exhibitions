package model.exception;

public class PageAccessException extends RuntimeException {
    public PageAccessException(String message) {
        super(message);
    }
}
