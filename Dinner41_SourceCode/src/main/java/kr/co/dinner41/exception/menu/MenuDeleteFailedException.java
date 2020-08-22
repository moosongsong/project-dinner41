package kr.co.dinner41.exception.menu;

public class MenuDeleteFailedException extends MenuException {
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE ="MenuDelete Failed";
	
	public MenuDeleteFailedException() {
		this(MESSAGE);
	}
	public MenuDeleteFailedException(String message) {
		super(message);
	}
}
