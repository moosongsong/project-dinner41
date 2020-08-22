package kr.co.dinner41.exception.store;

public abstract class StoreCategoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public StoreCategoryException(String message) {
		super(message);
	}
	
	public void doException() {
		//to do
	}
}
