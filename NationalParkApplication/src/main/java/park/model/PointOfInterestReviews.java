package park.model;

import java.util.Date;

public class PointOfInterestReviews {
    protected int pointOfInterestReviewId;
    protected String name;
    protected Date createdTime;
    protected String writtenReview;
    protected int Rating;
    protected NationalParks park;
    protected Users user;
    protected PointOfInterest pointOfInterest;

    //constructor with id
    public PointOfInterestReviews(int pointOfInterestReviewId, String name, Date createdTime,
        String writtenReview, int rating, NationalParks park, Users user,
        PointOfInterest pointOfInterest) {
        super();
        this.pointOfInterestReviewId = pointOfInterestReviewId;
        this.name = name;
        this.createdTime = createdTime;
        this.writtenReview = writtenReview;
        this.Rating = rating;
        this.park = park;
        this.user = user;
        this.pointOfInterest = pointOfInterest;
    }

    //constructor without id
    public PointOfInterestReviews(String name, Date createdTime, String writtenReview, int rating,
        NationalParks park, Users user, PointOfInterest pointOfInterest) {
        this.name = name;
        this.createdTime = createdTime;
        this.writtenReview = writtenReview;
        this.Rating = rating;
        this.park = park;
        this.user = user;
        this.pointOfInterest = pointOfInterest;
    }

    public int getPointOfInterestReviewId() {
        return pointOfInterestReviewId;
    }

    public void setPointOfInterestReviewId(int pointOfInterestReviewId) {
        this.pointOfInterestReviewId = pointOfInterestReviewId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public NationalParks getPark() {
        return park;
    }

    public void setPark(NationalParks park) {
        this.park = park;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public PointOfInterest getPointOfInterest() {
        return pointOfInterest;
    }

    public void setPointOfInterest(PointOfInterest pointOfInterest) {
        this.pointOfInterest = pointOfInterest;
    }
}
