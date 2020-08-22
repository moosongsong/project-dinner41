package kr.co.dinner41.vo;

import java.sql.Timestamp;

public class LogVO {
	private int id;
	private StoreVO store;
	private StoreStateVO state;
	private Timestamp logDate;
	
	public LogVO() {}

	public LogVO(int id, StoreVO store, StoreStateVO state, Timestamp logDate) {
		this.id = id;
		this.store = store;
		this.state = state;
		this.logDate = logDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StoreVO getStore() {
		return store;
	}

	public void setStore(StoreVO store) {
		this.store = store;
	}

	public StoreStateVO getState() {
		return state;
	}

	public void setState(StoreStateVO state) {
		this.state = state;
	}

	public Timestamp getLogDate() {
		return logDate;
	}

	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}
	
	
}
