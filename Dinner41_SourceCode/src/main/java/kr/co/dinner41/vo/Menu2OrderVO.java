package kr.co.dinner41.vo;

import java.io.Serializable;

public class Menu2OrderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private OrderVO order;
	private StoreVO store;
	private MenuVO menu;
	private int amount;
	
	public Menu2OrderVO() {}
	public Menu2OrderVO(OrderVO order, StoreVO store, MenuVO menu, int amount) {
		super();
		this.order = order;
		this.store = store;
		this.menu = menu;
		this.amount = amount;
	}
	public OrderVO getOrder() {
		return order;
	}
	public void setOrder(OrderVO order) {
		this.order = order;
	}
	public StoreVO getStore() {
		return store;
	}
	public void setStore(StoreVO store) {
		this.store = store;
	}
	public MenuVO getMenu() {
		return menu;
	}
	public void setMenu(MenuVO menu) {
		this.menu = menu;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
