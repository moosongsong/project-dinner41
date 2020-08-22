package kr.co.dinner41.exception.store;

public class StoreCategoryInsertFailedException extends StoreCategoryException {
	private static final long serialVersionUID =1L;
	public static final String MESSAGE = "매장카테고리 등록에 실패하였습니다.";
	
	public StoreCategoryInsertFailedException() {
		this(MESSAGE);
	}
	public StoreCategoryInsertFailedException(String message) {
		super(message);
	}
}
