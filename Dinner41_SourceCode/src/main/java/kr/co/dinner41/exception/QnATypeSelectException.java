package kr.co.dinner41.exception;

public class QnATypeSelectException extends QnATypeException{
    private static final String MESSAGE = "QnAType을 가져오지 못했습니다.";
    public QnATypeSelectException(){
        this(MESSAGE);
    }
    public QnATypeSelectException(String message) {
        super(message);
    }
}
