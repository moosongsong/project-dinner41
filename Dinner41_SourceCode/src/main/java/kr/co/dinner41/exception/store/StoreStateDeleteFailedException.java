package kr.co.dinner41.exception.store;

public class StoreStateDeleteFailedException extends StoreStateException {
	private static final long serialVersionUID =1L;
	public static final String MESSAGE = "매장상태 삭제에 실패하였습니다.";
	
	public StoreStateDeleteFailedException() {
		this(MESSAGE);
	}
	
	public StoreStateDeleteFailedException(String message) {
		super(message);
	}
}
