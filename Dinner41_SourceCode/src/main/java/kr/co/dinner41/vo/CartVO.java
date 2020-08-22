package kr.co.dinner41.vo;

import java.io.Serializable;

public class CartVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int storeId;
	private int menuId;
	private String storeName;
	private String menuName;
	private int maxAmount;
	private int price;

	public CartVO() {}
	public CartVO(int storeId, int menuId, String storeName, String menuName, int maxAmount, int price) {
		this.storeId = storeId;
		this.menuId = menuId;
		this.storeName = storeName;
		this.menuName = menuName;
		this.maxAmount = maxAmount;
		this.price = price;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
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
	public int getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
