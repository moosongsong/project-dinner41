package kr.co.dinner41.exception.menu2order;

public class Menu2OrderUpdateException extends Menu2OrderException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "주문 목록 업데이트에 실패했습니다.";
    public Menu2OrderUpdateException(){
        this(MESSAGE);
    }
    public Menu2OrderUpdateException(String message) {
        super(message);
    }
}
