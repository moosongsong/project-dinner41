package kr.co.dinner41.exception.order;

public class OrderInsertException extends OrderException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "주문 등록에 실패했습니다.";
    public OrderInsertException(){
        this(MESSAGE);
    }
    public OrderInsertException(String message) {
        super(message);
    }
}
