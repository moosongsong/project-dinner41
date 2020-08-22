package kr.co.dinner41.exception.menu;

public class MenuUpdateFailedException extends MenuException{
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE ="MenuDelete Failed";
	
	public MenuUpdateFailedException() {
		this(MESSAGE);
	}
	public MenuUpdateFailedException(String message) {
		super(message);
	}

}
