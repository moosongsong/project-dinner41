package kr.co.dinner41.exception.user;

public class WrongPasswordConfirmException extends UserException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="비밀번호와 비밀번호 확인이 일치하지 않습니다";
	
	public WrongPasswordConfirmException() {
		this(MESSAGE);
	}
	
	public WrongPasswordConfirmException(String message) {
		super(message);
	}

}
