package kr.co.dinner41.exception.user;

public class UserUpdateFailedException extends UserException {
	private static final long serialVersionUID = 1L;
	private static Exception exception=new Exception("회원정보를 수정하지 못했습니다");
	private static final String MESSAGE="회원정보를 수정하지 못했습니다.";
	
	public UserUpdateFailedException() {
		this(exception);
	}
	
	public UserUpdateFailedException(Exception e) {
		this(e.getMessage(),e);
	}
	
	public UserUpdateFailedException(String message) {
		super(message);
	}
	public UserUpdateFailedException(String message,Exception e) {
		super(message,e);
	}

}
