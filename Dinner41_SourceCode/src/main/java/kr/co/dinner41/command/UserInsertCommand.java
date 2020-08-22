package kr.co.dinner41.command;

public class UserInsertCommand {
	private String email;
	private String password;
	private String passwordConfirm;
	private String name;
	private String address;
	private String subAddress;
	private String latitude;
	private String longitude;
	private String phone1;
	private String phone2;
	private String phone3;
	private String type;

	public UserInsertCommand() {}

	@Override
	public String toString() {
		return "UserInsertCommand [email=" + email + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ ", name=" + name + ", address=" + address + ", subAddress=" + subAddress + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", phone1=" + phone1 + ", phone2=" + phone2 + ", phone3=" + phone3
				+ ", type=" + type + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public UserInsertCommand(String email, String password, String passwordConfirm, String name, String address,
			String subAddress, String latitude, String longitude, String phone1, String phone2, String phone3,
			String type) {
		super();
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.name = name;
		this.address = address;
		this.subAddress = subAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
