package kr.co.dinner41.exception.user;

public class UserDeleteFailedException extends UserException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="회원을 삭제하지 못했습니다.";
	
	public UserDeleteFailedException() {
		this(MESSAGE);
	}
	
	public UserDeleteFailedException(String message) {
		super(message);
	}

}
