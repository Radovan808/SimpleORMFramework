package cz.radovan.simpleormframework.vyjimky;

public class MissingIdException extends RuntimeException {
    public MissingIdException(String message) {
        super(message);
    }
}
