package park.model;

public class RecommendedRestaurant {
	protected int restaurantId;
	protected String restaurantName;
	protected String phone;
	protected float rating;
	protected String parkId;
	public RecommendedRestaurant(int restaurantId, String restaurantName, String phone, float rating, String parkId) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.phone = phone;
		this.rating = rating;
		this.parkId = parkId;
	}
	public RecommendedRestaurant(String restaurantName, String phone, float rating, String parkId) {
		super();
		this.restaurantName = restaurantName;
		this.phone = phone;
		this.rating = rating;
		this.parkId = parkId;
	}
	
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	

}
