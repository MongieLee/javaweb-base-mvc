package cn.mgl.exception;

public class ApplicationException extends Exception {
    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
}
