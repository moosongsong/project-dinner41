package kr.co.dinner41.command;

import org.springframework.web.multipart.MultipartFile;

public class MenuInsertCommand {
	
	private String type;
	private String name;
	private int price;
	private String tag;
	private MultipartFile photo;
	private int amount;
	private String introduction;
	private String notice;
	
	
	public MenuInsertCommand(String type, String name, int price, String tag, MultipartFile photo, int amount,
			String introduction, String notice) {
		super();
		this.type = type;
		this.name = name;
		this.price = price;
		this.tag = tag;
		this.photo = photo;
		this.amount = amount;
		this.introduction = introduction;
		this.notice = notice;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public MultipartFile getPhoto() {
		return photo;
	}


	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}


	public String getNotice() {
		return notice;
	}


	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	
	
	

}
