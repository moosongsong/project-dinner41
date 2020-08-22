package kr.co.dinner41.vo;

public class Menu2OrderViewVO {
	
	private int orderId;
	private String storeName;
	private String menuName;
	private int amount;
	
	public Menu2OrderViewVO() {}
	public Menu2OrderViewVO(int orderId, String storeName, String menuName, int amount) {
		this.orderId = orderId;
		this.storeName = storeName;
		this.menuName = menuName;
		this.amount = amount;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
