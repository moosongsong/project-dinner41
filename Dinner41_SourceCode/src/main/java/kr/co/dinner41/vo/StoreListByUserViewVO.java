package kr.co.dinner41.vo;

public class StoreListByUserViewVO {
	//private String storeCategoryName;
	private int storeId;
	private String storeName;
	private String storePhoto;
	private int distance;
	//private String menu_tag;
	private double reviewScore;
	
	
	public StoreListByUserViewVO() {}


	public StoreListByUserViewVO(int storeId, String storeName, String storePhoto, int distance, double reviewScore) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.storePhoto = storePhoto;
		this.distance = distance;
		this.reviewScore = reviewScore;
	}


	public int getStoreId() {
		return storeId;
	}


	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getStorePhoto() {
		return storePhoto;
	}


	public void setStorePhoto(String storePhoto) {
		this.storePhoto = storePhoto;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public double getReviewScore() {
		return reviewScore;
	}


	public void setReviewScore(double reviewScore) {
		this.reviewScore = reviewScore;
	}

	
	
	
	
}
