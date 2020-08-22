package kr.co.dinner41.exception.usertype;

public class UserTypeSelectFailedException extends UserTypeException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="회원 유형 불러오기에 실패했습니다.";
	
	public UserTypeSelectFailedException() {
		this(MESSAGE);
	}

	public UserTypeSelectFailedException(String message) {
		super(message);
	}
}
