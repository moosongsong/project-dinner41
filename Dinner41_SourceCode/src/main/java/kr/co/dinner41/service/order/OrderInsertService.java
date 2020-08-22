package kr.co.dinner41.service.order;

public interface OrderInsertService {
	public String[] execute(String[] arrForOrder, String[] arrForOrder2, int userId, int getTime, int payTotal);
}
