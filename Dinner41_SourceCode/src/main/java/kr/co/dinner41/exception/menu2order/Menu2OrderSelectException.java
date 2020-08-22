package kr.co.dinner41.exception.menu2order;

public class Menu2OrderSelectException extends Menu2OrderException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "주문 목록 가져오기에 실패했습니다.";
    public Menu2OrderSelectException(){
        this(MESSAGE);
    }
    public Menu2OrderSelectException(String message) {
        super(message);
    }
}
