package park.model;

import java.util.Date;

public class Reviews {
	protected int reviewId;
	protected Date createdTime;
	protected String writtenReview;
	protected int rating;
	protected Users user;
	protected NationalParks park;
	
	public Reviews(int reviewId, Date createdTime, String writtenReview, int rating, Users user,
			NationalParks park) {
		super();
		this.reviewId = reviewId;
		this.createdTime = createdTime;
		this.writtenReview = writtenReview;
		this.rating = rating;
		this.user = user;
		this.park = park;
	}
	
	

	public Reviews(Date createdTime, String writtenReview, int rating, Users user, NationalParks park) {
		super();
		this.createdTime = createdTime;
		this.writtenReview = writtenReview;
		this.rating = rating;
		this.user = user;
		this.park = park;
	}


	public Reviews(int reviewId) {
		super();
		this.reviewId = reviewId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getWrittenReview() {
		return writtenReview;
	}

	public void setWrittenReview(String writtenReview) {
		this.writtenReview = writtenReview;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public NationalParks getPark() {
		return park;
	}

	public void setPark(NationalParks park) {
		this.park = park;
	}
	
	
}
