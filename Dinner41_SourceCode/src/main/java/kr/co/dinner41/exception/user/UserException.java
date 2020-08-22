package kr.co.dinner41.exception.user;

public class UserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public UserException(String message) {
		super(message);
	}

	public UserException(String message,Throwable e) {
		super(message,e);
	}
	
	
}
