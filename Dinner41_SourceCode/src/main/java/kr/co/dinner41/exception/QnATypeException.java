package kr.co.dinner41.exception;

public abstract class QnATypeException extends Exception{
    private static final long serialVersionUID = 1L;

    public QnATypeException(String message) {
        super(message);
    }
}
