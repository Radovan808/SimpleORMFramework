package cz.radovan.simpleormframework.vyjimky;

public class AnnotationMissingException extends RuntimeException {
    public AnnotationMissingException(String message) {
        super(message);
    }
}
