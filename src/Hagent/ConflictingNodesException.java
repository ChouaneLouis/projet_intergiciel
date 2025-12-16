package Hagent;

public class ConflictingNodesException extends RuntimeException {
    public ConflictingNodesException() {
        super("Conflicting nodes detected");
    }

    public ConflictingNodesException(String message) {
        super(message);
    }
}