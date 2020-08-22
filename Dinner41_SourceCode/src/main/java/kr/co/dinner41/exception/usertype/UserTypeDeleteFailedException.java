package kr.co.dinner41.exception.usertype;

public class UserTypeDeleteFailedException extends UserTypeException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="회원 유형을 삭제하지 못했습니다.";
	public UserTypeDeleteFailedException() {
		this(MESSAGE);
	}
 
	public UserTypeDeleteFailedException(String message) {
		super(message);
	}


}
