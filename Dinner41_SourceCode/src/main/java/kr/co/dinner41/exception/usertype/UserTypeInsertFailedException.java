package kr.co.dinner41.exception.usertype;

public class UserTypeInsertFailedException extends UserTypeException {
	private static final long serialVersionUID = 1L;
	private static String MESSAGE="회원유형을 추가하지 못했습니다.";
	
	public UserTypeInsertFailedException() {
		this(MESSAGE);
	}
	public UserTypeInsertFailedException(String message) {
		super(MESSAGE);
	}
}
