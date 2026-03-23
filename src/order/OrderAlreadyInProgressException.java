package order;

public class OrderAlreadyInProgressException extends Exception {
    public OrderAlreadyInProgressException(String message) {
        super(message);
    }
}

