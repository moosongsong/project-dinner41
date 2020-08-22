package kr.co.dinner41.exception.store;

public abstract class StoreException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public StoreException(String message) {
		super(message);
	}
	
	public void doException() {
		//to do
	}
}
