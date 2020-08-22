package kr.co.dinner41.vo;

public class StoreVO {
	private int id;
	private UserVO user;
	private StoreCategoryVO category;
	private StoreStateVO state;
	private String businessNumber;
	private String name;
	private String address;
	private String subAddress;
	private double latitude;
	private double longitude;
	private String phone;
	private String operateTime;
	private String photo;
	private String introduction;
	private OpenState openState;
	private String payNumber;
	
	
	public StoreVO() {}


	public StoreVO(int id, UserVO user, StoreCategoryVO category, StoreStateVO state, String businessNumber,
			String name, String address, String subAddress, double latitude, double longitude, String phone,
			String operateTime, String photo, String introduction, OpenState openState, String payNumber) {
		super();
		this.id = id;
		this.user = user;
		this.category = category;
		this.state = state;
		this.businessNumber = businessNumber;
		this.name = name;
		this.address = address;
		this.subAddress = subAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phone = phone;
		this.operateTime = operateTime;
		this.photo = photo;
		this.introduction = introduction;
		this.openState = openState;
		this.payNumber = payNumber;
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


	public StoreCategoryVO getCategory() {
		return category;
	}


	public void setCategory(StoreCategoryVO category) {
		this.category = category;
	}


	public StoreStateVO getState() {
		return state;
	}


	public void setState(StoreStateVO state) {
		this.state = state;
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


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
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


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}


	public OpenState getOpenState() {
		return openState;
	}


	public void setOpenState(OpenState openState) {
		this.openState = openState;
	}


	public String getPayNumber() {
		return payNumber;
	}


	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}
	
}
