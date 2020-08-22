package kr.co.dinner41.vo;

import java.sql.Timestamp;

public class MenuVO {
	
	private StoreVO store;
	private int id;
	private OfferTypeVO offerType;
	private String Tag;
	private String name;
	private int price;
	private int amount;
	private String description;
	private String notice;
	private String photo;
	private Timestamp removeDate;
	
	public MenuVO() {}

	
	public MenuVO(StoreVO store, int id,OfferTypeVO offerType, String tag, String name, int price, int amount,
			String description, String notice, String photo, Timestamp removeDate) {
		super();
		this.store = store;
		this.id = id;
		this.offerType = offerType;
		Tag = tag;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.description = description;
		this.notice = notice;
		this.photo = photo;
		this.removeDate = removeDate;
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


	public OfferTypeVO getOfferType() {
		return offerType;
	}


	public void setOfferType(OfferTypeVO offerType) {
		this.offerType = offerType;
	}


	public String getTag() {
		return Tag;
	}


	public void setTag(String tag) {
		Tag = tag;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getNotice() {
		return notice;
	}


	public void setNotice(String notice) {
		this.notice = notice;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Timestamp getRemoveDate() {
		return removeDate;
	}


	public void setRemoveDate(Timestamp removeDate) {
		this.removeDate = removeDate;
	}

}
