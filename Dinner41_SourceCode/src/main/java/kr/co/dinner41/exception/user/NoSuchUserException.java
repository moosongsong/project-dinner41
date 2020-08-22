package kr.co.dinner41.exception.user;

public class NoSuchUserException extends UserException {
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE="해당 정보를 가진 회원이 존재하지 않습니다.";
	
	public NoSuchUserException() {
		this(MESSAGE);
	}
	
	public NoSuchUserException(String message) {
		super(message);
	}
}
