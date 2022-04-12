package park.model;

public class Trails {

  protected Integer TrailId;
  protected String TrailName;
  protected String ParkId;
  protected String GeoLocation;
  protected String Popularity;
  protected Float Length;
  protected Float Evaluation;
  protected Integer Difficulty;
  protected String RouteType;
  protected String Features;
  protected String Activity;

  public Trails(Integer trailId, String trailName, String parkId, String geoLocation,
      String popularity, Float length, Float evaluation, Integer difficulty,
      String routeType, String features, String activity) {
    this.TrailId = trailId;
    this.TrailName = trailName;
    this.ParkId = parkId;
    this.GeoLocation = geoLocation;
    this.Popularity = popularity;
    this.Length = length;
    this.Evaluation = evaluation;
    this.Difficulty = difficulty;
    this.RouteType = routeType;
    this.Features = features;
    this.Activity = activity;


  }

  public Trails(String trailName, String parkId, String geoLocation,
      String popularity, Float length, Float evaluation, Integer difficulty,
      String routeType, String features, String activity) {
	  this.TrailName = trailName;
	  this.ParkId = parkId;
	  this.GeoLocation = geoLocation;
	  this.Popularity = popularity;
	  this.Length = length;
	  this.Evaluation = evaluation;
	  this.Difficulty = difficulty;
	  this.RouteType = routeType;
	  this. Features = features;
	  this.Activity = activity;

  }

//  public Trails(Integer resultTrailId, String resultTrailName, String resultParkId,
//      String resultgLocation, String resultPopularity, String resultLength,
//      Float resultEvaluation, Integer trailId,
//      String resultRouteType, String resultFeatures, String activity,
//      String resultActivity) {
//	  this.TrailId = trailId;
//	  this.ParkId = park
//  }

  public Integer getTrailId() {
    return TrailId;
  }

  public void setTrailId(Integer trailId) {
    TrailId = trailId;
  }

  public String getTrailName() {
    return TrailName;
  }

  public void setTrailName(String trailName) {
    TrailName = trailName;
  }

  public String getParkId() {
    return ParkId;
  }

  public void setParkId(String parkId) {
    ParkId = parkId;
  }

  public String getGeoLocation() {
    return GeoLocation;
  }

  public void setGeoLocation(String geoLocation) {
    GeoLocation = geoLocation;
  }

  public String getPopularity() {
    return Popularity;
  }

  public void setPopularity(String popularity) {
    Popularity = popularity;
  }

  public Float getLength() {
    return Length;
  }

  public void setLength(Float length) {
    Length = length;
  }

  public Float getEvaluation() {
    return Evaluation;
  }

  public void setEvaluation(Float evaluation) {
    Evaluation = evaluation;
  }

  public Integer getDifficulty() {
    return Difficulty;
  }

  public void setDifficulty(Integer difficulty) {
    Difficulty = difficulty;
  }

  public String getRouteType() {
    return RouteType;
  }

  public void setRouteType(String routeType) {
    RouteType = routeType;
  }

  public String getFeatures() {
    return Features;
  }

  public void setFeatures(String features) {
    Features = features;
  }

  public String getActivity() {
    return Activity;
  }

  public void setActivity(String activity) {
    Activity = activity;
  }



  @Override
  public String toString() {
    return "Trails{" +
        "TrailId=" + TrailId +
        ", TrailName='" + TrailName + '\'' +
        ", ParkId='" + ParkId + '\'' +
        ", GeoLocation='" + GeoLocation + '\'' +
        ", Popularity='" + Popularity + '\'' +
        ", Length='" + Length + '\'' +
        ", Evaluation=" + Evaluation +
        ", Difficulty=" + Difficulty +
        ", RouteType='" + RouteType + '\'' +
        ", Features='" + Features + '\'' +
        ", Activity='" + Activity + '\'' +
        '}';
  }
}
