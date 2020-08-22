package kr.co.dinner41.exception.menu;

public abstract class OfferTypeException extends Exception {
	private static final long serialVersionUID = 1L;

	public OfferTypeException(String message) {
		super(message);
	}
}

