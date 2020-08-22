package kr.co.dinner41.exception.menu;

public class OfferTypeSelectException extends OfferTypeException{
 
	private static final String MESSAGE = "OfferType을 가져오지 못했습니다.";
    public OfferTypeSelectException(){
        this(MESSAGE);
    }
    public OfferTypeSelectException(String message) {
 		super(message);
 		
 	}
}
