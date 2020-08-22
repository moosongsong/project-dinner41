package kr.co.dinner41.exception.menu;

public abstract class MenuException extends Exception{

	private static final long serialVersionUID = 1L;

	public MenuException(String message) {
		super(message);
	}
}
