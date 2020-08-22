package kr.co.dinner41.exception.map;

public class SearchStoreFailedException extends MapException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="가게목록을 불러오는데 실패했습니다.";
	
	public SearchStoreFailedException() {
		this(MESSAGE);
	}
	
	public SearchStoreFailedException(String message) {
		super(message);
	}
}
