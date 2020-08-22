package kr.co.dinner41.exception.menu;

public class MenuSelectFailedException extends MenuException{
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE ="MenuSelect Failed";
	
	public MenuSelectFailedException() {
		this(MESSAGE);
	}
	public MenuSelectFailedException(String message) {
		super(message);
	}

}
