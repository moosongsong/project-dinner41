package kr.co.dinner41.exception.store;

public abstract class StoreStateException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public StoreStateException(String message) {
		super(message);
	}
	
	public void doException() {
		//to do
	}
}
