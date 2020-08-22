package kr.co.dinner41.exception.user;

public class UserInsertFailedException extends UserException {
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="회원을 추가하지 못했습니다.";
	
	public UserInsertFailedException() {
		this(MESSAGE);
	}

	public UserInsertFailedException(String message) {
		super(message);
	}
}