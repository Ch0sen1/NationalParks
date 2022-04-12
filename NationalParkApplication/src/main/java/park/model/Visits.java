package park.model;

import java.util.Date;

public class Visits {
    protected int visitsId;
    protected Date visitTime;
    protected int travelPeriod;
    protected String userName;
    protected String parkId;

    //Constructor for Visits.
    public Visits(int visitsId, Date visitTime, int travelPeriod, String userName, String parkId) {
        this.visitsId = visitsId;
        this.visitTime = visitTime;
        this.travelPeriod = travelPeriod;
        this.userName = userName;
        this.parkId = parkId;
    }

    //Constructor for Visits with only visitsID.
    public Visits(int visitsId) {
        this.visitsId = visitsId;
    }

    public int getVisitsId() {
        return visitsId;
    }

    public void setVisitsId(int visitsId) {
        this.visitsId = visitsId;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public int getTravelPeriod() {
        return travelPeriod;
    }

    public void setTravelPeriod(int travelPeriod) {
        this.travelPeriod = travelPeriod;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }
}
