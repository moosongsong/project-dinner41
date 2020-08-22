package kr.co.dinner41.vo;

public class LocationVO {
	//private String address;
	//private String subAddress;
	private double latitude;
	private double longitude;
	
	public LocationVO() {}
	
	
	public LocationVO(double latitude,double longitude) {
		this.latitude=latitude;
		this.longitude=longitude;
	}

	/*
	public LocationVO(String address, String subAddress, double latitude, double longitude) {
		super();
		this.address = address;
		this.subAddress = subAddress;
		this.latitude = latitude;
		this.longitude = longitude;
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

	*/

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
	
}
