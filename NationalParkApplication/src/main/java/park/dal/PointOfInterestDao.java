package park.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import park.model.NationalParks;
import park.model.PointOfInterest;

public class PointOfInterestDao {
    protected ConnectionManager connectionManager;

    private static PointOfInterestDao instance = null;

    protected PointOfInterestDao() {
        connectionManager = new ConnectionManager();
    }

    public static PointOfInterestDao getInstance() {
        if (instance == null) {
            instance = new PointOfInterestDao();
        }
        return instance;
    }

    public PointOfInterest create(PointOfInterest pointOfInterest) throws SQLException {
        String insertPointOfInterest =
            "INSERT INTO PointOfInterest(PointName,MapLable,POIType,ParkId,ParkName,Seasonal,SeaDescription,Maintainer) " +
                "VALUES(?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPointOfInterest,
                Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, pointOfInterest.getPointName());
            insertStmt.setString(2, pointOfInterest.getMapLable());
            insertStmt.setString(3, pointOfInterest.getPOIType());
            insertStmt.setString(4, pointOfInterest.getPark().getParkId());
            insertStmt.setString(5, pointOfInterest.getPark().getParkName());
            insertStmt.setBoolean(6, pointOfInterest.isSeasonal());
            insertStmt.setString(7, pointOfInterest.getSeaDescription());
            insertStmt.setString(8, pointOfInterest.getMaintainer());

            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int objectId = -1;
            if (resultKey.next()) {
                objectId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            pointOfInterest.setObjectId(objectId);
            return pointOfInterest;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public PointOfInterest getPointOfInterestById(int objectId) throws SQLException {
        String selectPointOfInterest =
            "SELECT ObjectId,PointName,MapLable,POIType,ParkId,ParkName,Seasonal,SeaDescription,Maintainer " +
            "FROM PointOfInterest " +
            "WHERE ObjectId=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPointOfInterest);
            selectStmt.setInt(1, objectId);
            results = selectStmt.executeQuery();
            NationalParksDao nationalParksDao = NationalParksDao.getInstance();

            if (results.next()) {
                int resultObjectId = results.getInt("ObjectId");
                String pointName = results.getString("PointName");
                String mapLable = results.getString("MapLable");
                String POIType = results.getString("POIType");
                String parkId = results.getString("ParkId");
                String parkName = results.getString("ParkName");
                boolean seasonal = results.getBoolean("Seasonal");
                String seaDescription = results.getString("SeaDescription");
                String maintainer = results.getString("Maintainer");

                NationalParks park = nationalParksDao.getParkById(parkId);
                PointOfInterest pointOfInterest = new PointOfInterest(resultObjectId, pointName, mapLable,
                    POIType, park, seasonal, seaDescription, maintainer);
                return pointOfInterest;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }

    public List<PointOfInterest> getPointOfInterestByParkId(String parkId) throws SQLException {
        List<PointOfInterest> resultPointOfInterest = new ArrayList<PointOfInterest>();
        String selectPointOfInterest =
            "SELECT ObjectId,PointName,MapLable,POIType,ParkId,ParkName,Seasonal,SeaDescription,Maintainer " +
            "FROM PointOfInterest " +
            "WHERE ParkId=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPointOfInterest);
            selectStmt.setString(1, parkId);
            results = selectStmt.executeQuery();
            NationalParksDao nationalParksDao = NationalParksDao.getInstance();
            NationalParks park = nationalParksDao.getParkById(parkId);

            while (results.next()) {
                int resultObjectId = results.getInt("ObjectId");
                String pointName = results.getString("PointName");
                String mapLable = results.getString("MapLable");
                String POIType = results.getString("POIType");
                String parkName = results.getString("ParkName");
                boolean seasonal = results.getBoolean("Seasonal");
                String seaDescription = results.getString("SeaDescription");
                String maintainer = results.getString("Maintainer");

                PointOfInterest pointOfInterest = new PointOfInterest(resultObjectId, pointName, mapLable,
                    POIType, park, seasonal, seaDescription, maintainer);
                resultPointOfInterest.add(pointOfInterest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return resultPointOfInterest;
    }

    public PointOfInterest delete(PointOfInterest pointOfInterest) throws SQLException {
        String deletePointOfInterest = "DELETE FROM PointOfInterest WHERE ObjectId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePointOfInterest);
            deleteStmt.setInt(1, pointOfInterest.getObjectId());
            deleteStmt.executeUpdate();

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}
