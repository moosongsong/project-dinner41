package kr.co.dinner41.exception.menu;

public class OfferTypeInsertFailedException extends OfferTypeException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE ="OfferTypeDao의 삽입 실패";
	
	public OfferTypeInsertFailedException() {
		this(MESSAGE);
	}
	
	public OfferTypeInsertFailedException(String message) {
		super(message);
	}

}
