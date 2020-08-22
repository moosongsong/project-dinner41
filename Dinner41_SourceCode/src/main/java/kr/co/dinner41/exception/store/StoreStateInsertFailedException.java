package kr.co.dinner41.exception.store;

public class StoreStateInsertFailedException extends StoreStateException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="매장상태 등록에 실패하였습니다.";
	
	public StoreStateInsertFailedException() {
		this(MESSAGE);
	}
	
	public StoreStateInsertFailedException(String message) {
		super(message);
	}
}
