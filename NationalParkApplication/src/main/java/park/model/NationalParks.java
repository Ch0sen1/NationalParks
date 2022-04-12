package park.model;

import java.math.BigDecimal;

public class NationalParks {
    protected String parkId;
    protected int ranking;
    protected String parkName;
    protected long acres;
    protected BigDecimal latitude;
    protected BigDecimal longitude;
    protected boolean active;
    protected String city;
    protected String state;
    protected String zip;
    protected String description;

    public NationalParks(String parkId, int ranking, String parkName, long acres, BigDecimal latitude, BigDecimal longitude, boolean active, String city, String state, String zip, String description) {
        this.parkId = parkId;
        this.ranking = ranking;
        this.parkName = parkName;
        this.acres = acres;
        this.latitude = latitude;
        this.longitude = longitude;
        this.active = active;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.description = description;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public long getAcres() {
        return acres;
    }

    public void setAcres(long acres) {
        this.acres = acres;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
