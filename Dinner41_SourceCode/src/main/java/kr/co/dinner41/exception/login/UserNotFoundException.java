package kr.co.dinner41.exception.login;

public class UserNotFoundException extends LoginException {
	private static final long serialVersionUID = 1L;
	private  static final String MESSAGE="회원이 존재하지 않습니다";
	public UserNotFoundException() {
		this(MESSAGE);
	}
	public UserNotFoundException(String message) {
		super(message);
	}

}
