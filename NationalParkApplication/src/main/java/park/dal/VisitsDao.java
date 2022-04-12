package park.dal;


import park.model.*;

import java.sql.*;
import java.util.Date;

public class VisitsDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static VisitsDao instance = null;
    protected VisitsDao() {
        connectionManager = new ConnectionManager();
    }
    public static VisitsDao getInstance() {
        if(instance == null) {
            instance = new VisitsDao();
        }
        return instance;
    }

    /**
     * Save the Visits instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Visits create(Visits visit) throws SQLException {
        String insertVisit = "INSERT INTO Visits(VisitsId,VisitTime,TravelPeriod,UserName,ParkId) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        // not sure if add a result key here...
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertVisit, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setInt(1, visit.getVisitsId());
            insertStmt.setTimestamp(2, new Timestamp(visit.getVisitTime().getTime()));
            insertStmt.setInt(3, visit.getTravelPeriod());
            insertStmt.setString(4, visit.getUserName());
            insertStmt.setString(5, visit.getParkId());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int visitsId = -1;
            if(resultKey.next()) {
                visitsId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            visit.setVisitsId(visitsId);

            // Note 1: if this was an UPDATE statement, then the person fields should be
            // updated before returning to the caller.
            // Note 2: there are no auto-generated keys, so no update to perform on the
            // input param person.
            return visit;
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

    /**
     * Get the Visits instance by VisitsID.
     */
    public Visits getVisitById(int visitsId) throws SQLException {
        String selectReservation =
                "SELECT VisitsId,VisitTime,TravelPeriod,UserName,ParkId " +
                        "FROM Visits " +
                        "WHERE VisitsId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReservation);
            selectStmt.setInt(1, visitsId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            NationalParksDao nationalParksDao = NationalParksDao.getInstance();

            if(results.next()) {
                int resultVisitsId = results.getInt("VisitsId");
                Date visitTime = results.getDate("VisitTime");
                int travelPeriod = results.getInt("TravelPeriod");
                String userName = results.getString("UserName");
                String parkId = results.getString("ParkId");

                Users user = usersDao.getUserFromUserName(userName);
                NationalParks nationalParks = nationalParksDao.getParkById(parkId);
                Visits visit = new Visits(resultVisitsId, visitTime, travelPeriod, userName, parkId);
                return visit;
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

    /**
     * Delete the Visits instance.
     * This runs a DELETE statement.
     */
    public Visits delete(Visits visit) throws SQLException {
        String deleteVisit = "DELETE FROM Visits WHERE VisitsId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteVisit);
            deleteStmt.setInt(1, visit.getVisitsId());
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




