package kr.co.dinner41.exception.user;

public class NotLoginStateException extends UserException {
	private static final long serialVersionUID = 1L;
	private final static String MESSAGE="현재 로그인이 안되어있는 상태입니다. 로그인을 해주세요";
	
	public NotLoginStateException() {
		this(MESSAGE);
	}
	
	public NotLoginStateException(String message) {
		super(message);
	}

}
