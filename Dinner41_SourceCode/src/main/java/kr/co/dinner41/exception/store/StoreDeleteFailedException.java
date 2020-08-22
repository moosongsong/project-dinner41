package kr.co.dinner41.exception.store;

public class StoreDeleteFailedException extends StoreException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "매장 삭제에 실패하였습니다.";
	
	public StoreDeleteFailedException() {
		this(MESSAGE);
	}
	
	public StoreDeleteFailedException(String message) {
		super(message);
	}
}
