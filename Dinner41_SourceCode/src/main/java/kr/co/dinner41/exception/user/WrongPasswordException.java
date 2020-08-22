package kr.co.dinner41.exception.user;

public class WrongPasswordException extends UserException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="비밀번호가 일치하지 않습니다.";
	public WrongPasswordException() {
		this(MESSAGE);
	}
	
	public WrongPasswordException(String message) {
		super(message);
	}

}
