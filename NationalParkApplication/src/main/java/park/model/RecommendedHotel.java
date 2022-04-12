package park.model;

public class RecommendedHotel {
	protected int hotelId;
	protected String hotelName;
	protected String phone;
	protected float rating;
	protected String parkId;
	
	public RecommendedHotel(int hotelId, String hotelName, String phone, float rating, String parkId) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.phone = phone;
		this.rating = rating;
		this.parkId = parkId;
	}
	
	public RecommendedHotel(String hotelName, String phone, float rating, String parkId) {
		super();
		this.hotelName = hotelName;
		this.phone = phone;
		this.rating = rating;
		this.parkId = parkId;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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
