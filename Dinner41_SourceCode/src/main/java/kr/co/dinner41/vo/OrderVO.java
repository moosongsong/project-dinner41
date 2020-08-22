package kr.co.dinner41.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private UserVO user;
	private Timestamp orderDate;
	private Timestamp reserveDate;
	private Timestamp pickupDate;
	private int price;
	
	public OrderVO() {}
	public OrderVO(int id, UserVO user, Timestamp orderDate, Timestamp reserveDate, Timestamp pickupDate, int price) {
		this.id = id;
		this.user = user;
		this.orderDate = orderDate;
		this.reserveDate = reserveDate;
		this.pickupDate = pickupDate;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Timestamp getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Timestamp reserveDate) {
		this.reserveDate = reserveDate;
	}
	public Timestamp getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(Timestamp pickupDate) {
		this.pickupDate = pickupDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
