package kr.co.dinner41.exception.store;

public class StoreSelectFailedException extends StoreException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "매장 찾기에 실패하였습니다.";
	
	public StoreSelectFailedException() {
		this(MESSAGE);
	}
	
	public StoreSelectFailedException(String message) {
		super(message);
	}
}
