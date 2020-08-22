package kr.co.dinner41.command;

import org.springframework.web.multipart.MultipartFile;

public class StoreInsertCommand {
	private String businessNumber;
	private String name;
	private MultipartFile photo;
	private String category;
	private String address;
	private String subAddress;
	private String latitude;
	private String longitude;
	private String phone;
	private String operateTime;
	private String introduction;

	
	public StoreInsertCommand() {}


	public StoreInsertCommand(String businessNumber, String name, MultipartFile photo, String category, String address,
			String subAddress, String latitude, String longitude, String phone, String operateTime,
			String introduction) {
		super();
		this.businessNumber = businessNumber;
		this.name = name;
		this.photo = photo;
		this.category = category;
		this.address = address;
		this.subAddress = subAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phone = phone;
		this.operateTime = operateTime;
		this.introduction = introduction;
	}


	public String getBusinessNumber() {
		return businessNumber;
	}


	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public MultipartFile getPhoto() {
		return photo;
	}


	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getSubAddress() {
		return subAddress;
	}


	public void setSubAddress(String subAddress) {
		this.subAddress = subAddress;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getOperateTime() {
		return operateTime;
	}


	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}


	public String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	
	
}
