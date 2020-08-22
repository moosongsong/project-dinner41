package kr.co.dinner41.exception;

public abstract class ReviewException extends Exception{
    private static final long serialVersionUID = 1L;

    public ReviewException(String message) {
        super(message);
    }
}
