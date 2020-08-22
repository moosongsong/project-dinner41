package kr.co.dinner41.exception;

public class CartException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public CartException(String message) {
		super(message);
	}
}
