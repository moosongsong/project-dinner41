package kr.co.dinner41.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderViewVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int orderId;
	private int storeId;
	private int userId;
	private Timestamp order_order_date;
	private Timestamp order_reserve_date;
	private Timestamp order_pickup_date;
	private int price;
	private String storeName;
	private String userName;
	
	public OrderViewVO() {}
	public OrderViewVO(int orderId, int storeId, int userId, Timestamp order_order_date, Timestamp order_reserve_date,
			Timestamp order_pickup_date, int price, String storeName, String userName) {
		this.orderId = orderId;
		this.storeId = storeId;
		this.userId = userId;
		this.order_order_date = order_order_date;
		this.order_reserve_date = order_reserve_date;
		this.order_pickup_date = order_pickup_date;
		this.price = price;
		this.storeName = storeName;
		this.userName = userName;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getOrder_order_date() {
		return order_order_date;
	}
	public void setOrder_order_date(Timestamp order_order_date) {
		this.order_order_date = order_order_date;
	}
	public Timestamp getOrder_reserve_date() {
		return order_reserve_date;
	}
	public void setOrder_reserve_date(Timestamp order_reserve_date) {
		this.order_reserve_date = order_reserve_date;
	}
	public Timestamp getOrder_pickup_date() {
		return order_pickup_date;
	}
	public void setOrder_pickup_date(Timestamp order_pickup_date) {
		this.order_pickup_date = order_pickup_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}