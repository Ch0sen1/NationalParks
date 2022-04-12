package park.dal;

import park.model.NationalParks;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NationalParksDao {
    protected ConnectionManager connectionManager;

    private static NationalParksDao instance = null;

    protected NationalParksDao() {
        connectionManager = new ConnectionManager();
    }

    public static NationalParksDao getInstance() {
        if (instance == null) {
            instance = new NationalParksDao();
        }
        return instance;
    }

    public NationalParks create(NationalParks nationalPark) throws SQLException{
        String insertParks =
                "INSERT INTO NationalParks(ParkId,Ranking,ParkName,Acres,Latitude,Longitude,Active,City,State,Zip,Description) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertParks);

            insertStmt.setString(1, nationalPark.getParkId());
            insertStmt.setInt(2, nationalPark.getRanking());
            insertStmt.setString(3, nationalPark.getParkName());
            insertStmt.setLong(4, nationalPark.getAcres());
            insertStmt.setBigDecimal(5, nationalPark.getLatitude());
            insertStmt.setBigDecimal(6, nationalPark.getLongitude());
            insertStmt.setBoolean(7, nationalPark.isActive());
            insertStmt.setString(8, nationalPark.getCity());
            insertStmt.setString(9, nationalPark.getState());
            insertStmt.setString(10, nationalPark.getZip());
            insertStmt.setString(11, nationalPark.getDescription());

            insertStmt.executeUpdate();

            return nationalPark;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public NationalParks getParkById(String parkId) throws SQLException{
        String selectPark =
                "SELECT ParkId, Ranking, ParkName, Acres, Latitude, Longitude, Active, City, State, Zip, Description " +
                        "FROM NationalParks " +
                        "WHERE ParkId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPark);
            selectStmt.setString(1, parkId);
            results = selectStmt.executeQuery();

            if(results.next()) {
//                String parkId = results.getString("ParkId");
                int ranking = results.getInt("Ranking");
                String parkName = results.getString("ParkName");
                long acres = results.getLong("Acres");
                BigDecimal latitude = results.getBigDecimal("Latitude");
                BigDecimal longitude = results.getBigDecimal("Longitude");
                boolean active = results.getBoolean("Active");
                String city = results.getString("City");
                String state = results.getString("State");
                String zip = results.getString("Zip");
                String description = results.getString("description");

                NationalParks park =  new NationalParks(parkId,ranking,parkName,acres,latitude,longitude,active,city,state,zip,description);

                return park;
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

    public NationalParks updateDescription(NationalParks nationalPark, String newDescription) throws SQLException{
        String updateDescription = "UPDATE NationalParks SET Description=? WHERE ParkId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateDescription);
            updateStmt.setString(1, newDescription);
            updateStmt.setString(2, nationalPark.getParkId());
            updateStmt.executeUpdate();

            // Update the blogPost param before returning to the caller.
            nationalPark.setDescription(newDescription);
            return nationalPark;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(updateStmt != null) {
                updateStmt.close();
            }
        }
    }


    public List<NationalParks> getParksByState(String state) throws SQLException {
        List<NationalParks> nationalParks = new ArrayList<NationalParks>();
        String selectParks =
                "SELECT ParkId, Ranking, ParkName, Acres, Latitude, Longitude, Active, City, State, Zip, Description " +
                        "FROM nationalparks " +
                        "WHERE State=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectParks);
            selectStmt.setString(1, state);
            results = selectStmt.executeQuery();
            while(results.next()) {
                String parkId = results.getString("ParkId");
                int ranking = results.getInt("Ranking");
                String parkName = results.getString("ParkName");
                long acres = results.getLong("Acres");
                BigDecimal latitude = results.getBigDecimal("Latitude");
                BigDecimal longitude = results.getBigDecimal("Longitude");
                boolean active = results.getBoolean("Active");
                String city = results.getString("City");
//                String state = results.getString("State");
                String zip = results.getString("Zip");
                String description = results.getString("Description");
                
                NationalParks park =  new NationalParks(parkId,ranking,parkName,acres,latitude,longitude,active,city,state,zip,description);

                nationalParks.add(park);
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
        return nationalParks;
    }
    public NationalParks delete(NationalParks park) throws SQLException{
        String deletePark = "DELETE FROM NationalParks WHERE ParkId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePark);
            deleteStmt.setString(1, park.getParkId());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the BlogPosts instance.
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


