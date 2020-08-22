package kr.co.dinner41.exception.menu;

public class MenuInsertFailedException extends MenuException{

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE ="MenuInsert Failed";
	
	public MenuInsertFailedException() {
		this(MESSAGE);
	}
	public MenuInsertFailedException(String message) {
		super(message);
	}

}
