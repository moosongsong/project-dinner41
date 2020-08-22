package kr.co.dinner41.exception;

public abstract class QnAException extends Exception{
    private static final long serialVersionUID = 1L;

    public QnAException(String message) {
        super(message);
    }
}
