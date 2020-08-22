package kr.co.dinner41.command;

public class UserUpdateCommand {
	private String name;
	private String phone1;
	private String phone2;
	private String phone3;
	private String address;
	private String subAddress;
	private String latitude;
	private String longitude;
	private String newPassword;
	private String newPasswordConfirm;
	
	public UserUpdateCommand() {}

	public UserUpdateCommand(String name, String phone1, String phone2, String phone3, String address,
			String subAddress, String latitude, String longitude, String newPassword, String newPasswordConfirm) {
		super();
		this.name = name;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.address = address;
		this.subAddress = subAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.newPassword = newPassword;
		this.newPasswordConfirm = newPasswordConfirm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

}
