package kr.co.dinner41.exception.order;

public class OrderSelectException extends OrderException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "주문 가져오기에 실패했습니다.";
    public OrderSelectException(){
        this(MESSAGE);
    }
    public OrderSelectException(String message) {
        super(message);
    }
}
