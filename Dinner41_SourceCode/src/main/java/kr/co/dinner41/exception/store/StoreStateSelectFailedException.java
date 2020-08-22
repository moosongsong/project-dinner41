package kr.co.dinner41.exception.store;

public class StoreStateSelectFailedException extends StoreStateException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="매장상태 찾기에 실패하였습니다.";
	
	public StoreStateSelectFailedException() {
		this(MESSAGE);
	}
	
	public StoreStateSelectFailedException(String message) {
		super(message);
	}
}
