package kr.co.dinner41.exception.login;

public class QuitUserException extends LoginException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="탈퇴한 회원입니다";
	public QuitUserException() {
		this(MESSAGE);
	}
	public QuitUserException(String message) {
		super(message);
	}

}
