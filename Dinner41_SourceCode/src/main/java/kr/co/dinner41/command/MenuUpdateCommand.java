package kr.co.dinner41.command;

import org.springframework.web.multipart.MultipartFile;

public class MenuUpdateCommand {
	
	private String type;
	private String name;
	private int price;
	private MultipartFile photo;
	private int amount;
	private String description;
	private String notice;
	private String tag;
	
	public MenuUpdateCommand(String type, String name, int price, MultipartFile photo, int amount, String description,
			String notice, String tag) {
		super();
		this.type = type;
		this.name = name;
		this.price = price;
		this.photo = photo;
		this.amount = amount;
		this.description = description;
		this.notice = notice;
		this.tag = tag;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
