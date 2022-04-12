package park.model;

public class PointOfInterest {

    protected int objectId;
    protected String pointName;
    protected String mapLable;
    protected String POIType;
    protected NationalParks park;
    protected boolean seasonal;
    protected String seaDescription;
    protected String maintainer;

    //constructor with id
    public PointOfInterest(int objectId, String pointName, String mapLable, String POIType, NationalParks park,
        boolean seasonal, String seaDescription, String maintainer) {
        super();
        this.objectId = objectId;
        this.pointName = pointName;
        this.mapLable = mapLable;
        this.POIType = POIType;
        this.park = park;
        this.seasonal = seasonal;
        this.seaDescription = seaDescription;
        this.maintainer = maintainer;
    }

    //constructor without id
    public PointOfInterest(String pointName, String mapLable, String POIType,
        NationalParks park, boolean seasonal, String seaDescription, String maintainer) {
        this.pointName = pointName;
        this.mapLable = mapLable;
        this.POIType = POIType;
        this.park = park;
        this.seasonal = seasonal;
        this.seaDescription = seaDescription;
        this.maintainer = maintainer;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getMapLable() {
        return mapLable;
    }

    public void setMapLable(String mapLable) {
        this.mapLable = mapLable;
    }

    public String getPOIType() {
        return POIType;
    }

    public void setPOIType(String POIType) {
        this.POIType = POIType;
    }

    public boolean isSeasonal() {
        return seasonal;
    }

    public void setSeasonal(boolean seasonal) {
        this.seasonal = seasonal;
    }

    public String getSeaDescription() {
        return seaDescription;
    }

    public void setSeaDescription(String seaDescription) {
        this.seaDescription = seaDescription;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public NationalParks getPark() {
        return park;
    }

    public void setPark(NationalParks park) {
        this.park = park;
    }
}