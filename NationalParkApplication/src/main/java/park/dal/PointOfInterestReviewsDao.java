package park.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import park.model.NationalParks;
import park.model.PointOfInterest;
import park.model.PointOfInterestReviews;
import park.model.Users;

public class PointOfInterestReviewsDao {
    protected ConnectionManager connectionManager;
    
    private static PointOfInterestReviewsDao instance = null;
    
    protected PointOfInterestReviewsDao() {
        connectionManager = new ConnectionManager();
    }

    public static PointOfInterestReviewsDao getInstance() {
        if (instance == null) {
            instance = new PointOfInterestReviewsDao();
        }
        return instance;
    }

    public PointOfInterestReviews create(PointOfInterestReviews pointOfInterestReviews) throws SQLException {
        String insertPointOfInterestReviews =
            "INSERT INTO PointOfInterestReviews(Name,CreatedTime,WrittenReview,Rating,ParkId,UserName,ObjectId) " +
            "VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPointOfInterestReviews,
            		Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, pointOfInterestReviews.getName());
            insertStmt.setTimestamp(2, new Timestamp(pointOfInterestReviews.getCreatedTime().getTime()));
            insertStmt.setString(3, pointOfInterestReviews.getWrittenReview());
            insertStmt.setInt(4, pointOfInterestReviews.getRating());
            insertStmt.setString(5, pointOfInterestReviews.getPark().getParkId());
            insertStmt.setString(6, pointOfInterestReviews.getUser().getUserName());
            insertStmt.setInt(7, pointOfInterestReviews.getPointOfInterest().getObjectId());
            
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int pointOfInterestReviewId = -1;
            if (resultKey.next()) {
                pointOfInterestReviewId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            pointOfInterestReviews.setPointOfInterestReviewId(pointOfInterestReviewId);
            return pointOfInterestReviews;
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
            if (resultKey != null) {
                resultKey.close();
            }
        }
    }

    public PointOfInterestReviews getPointOfInterestReviewsById(int pointOfInterestReviewId) throws SQLException {
        String selectPointOfInterestReviews =
            "SELECT PointOfInterestReviewId,Name,CreatedTime,WrittenReview,Rating,ParkId,UserName,ObjectId) " +
            "FROM PointOfInterestReview " +
            "WHERE PointOfInterestReviewId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPointOfInterestReviews);
            selectStmt.setInt(1, pointOfInterestReviewId);
            results = selectStmt.executeQuery();
            NationalParksDao nationalParksDao = NationalParksDao.getInstance();
            UsersDao usersDao = UsersDao.getInstance();
            PointOfInterestDao pointOfInterestDao = PointOfInterestDao.getInstance();

            if (results.next()) {
                int resultPointOfInterestReviewId = results.getInt("PointOfInterestReviewId");
                String name = results.getString("Name");
                Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
                String writtenReview = results.getString("WrittenReview");
                int rating = results.getInt("Rating");
                String parkId = results.getString("ParkId");
                String userName = results.getString("UserName");
                int objectId = results.getInt("ObjectId");

                NationalParks park = nationalParksDao.getParkById(parkId);
                Users user = usersDao.getUserFromUserName(userName);
                PointOfInterest pointOfInterest = pointOfInterestDao.getPointOfInterestById(objectId);
                PointOfInterestReviews pointOfInterestReviews = new PointOfInterestReviews(resultPointOfInterestReviewId,
                    name, createdTime, writtenReview, rating, park, user, pointOfInterest);
                return pointOfInterestReviews;
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

    public List<PointOfInterestReviews> getPointOfInterestReviewsByParkId(String parkId) throws SQLException {
        List<PointOfInterestReviews> pointOfInterestReviews = new ArrayList<PointOfInterestReviews>();
        String selectPointOfInterestReviews =
            "SELECT PointOfInterestReviewId,Name,CreatedTime,WrittenReview,Rating,ParkId,UserName,ObjectId) " +
            "FROM PointOfInterestReview " +
            "WHERE ParkId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPointOfInterestReviews);
            selectStmt.setString(1, parkId);
            results = selectStmt.executeQuery();
            NationalParksDao nationalParksDao = NationalParksDao.getInstance();
            UsersDao usersDao = UsersDao.getInstance();
            PointOfInterestDao pointOfInterestDao = PointOfInterestDao.getInstance();

            while (results.next()) {
                int resultPointOfInterestReviewId = results.getInt("PointOfInterestReviewId");
                String name = results.getString("Name");
                Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
                String writtenReview = results.getString("WrittenReview");
                int rating = results.getInt("Rating");
                String userName = results.getString("UserName");
                int objectId = results.getInt("ObjectId");

                NationalParks park = nationalParksDao.getParkById(parkId);
                Users user = usersDao.getUserFromUserName(userName);
                PointOfInterest pointOfInterest = pointOfInterestDao.getPointOfInterestById(objectId);

                PointOfInterestReviews review = new PointOfInterestReviews(resultPointOfInterestReviewId,
                    name, createdTime, writtenReview, rating, park, user, pointOfInterest);
                pointOfInterestReviews.add(review);
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
        return pointOfInterestReviews;
    }

    public List<PointOfInterestReviews> getPointOfInterestReviewByUserName(String userName) throws SQLException {
        List<PointOfInterestReviews> pointOfInterestReviews = new ArrayList<PointOfInterestReviews>();
        String selectPointOfInterestReviews =
            "SELECT PointOfInterestReviewId,Name,CreatedTime,WrittenReview,Rating,ParkId,UserName,ObjectId) " +
            "FROM PointOfInterestReview " +
            "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPointOfInterestReviews);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            NationalParksDao nationalParksDao = NationalParksDao.getInstance();
            UsersDao usersDao = UsersDao.getInstance();
            PointOfInterestDao pointOfInterestDao = PointOfInterestDao.getInstance();

            while (results.next()) {
                int pointOfInterestReviewId = results.getInt("PointOfInterestReviewId");
                String name = results.getString("Name");
                Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
                String writtenReview = results.getString("WrittenReview");
                int rating = results.getInt("Rating");
                String parkId = results.getString("ParkId");
                int objectId = results.getInt("ObjectId");

                NationalParks park = nationalParksDao.getParkById(parkId);
                Users user = usersDao.getUserFromUserName(userName);
                PointOfInterest pointOfInterest = pointOfInterestDao.getPointOfInterestById(objectId);

                PointOfInterestReviews review = new PointOfInterestReviews(pointOfInterestReviewId,
                    name, createdTime, writtenReview, rating, park, user, pointOfInterest);
                pointOfInterestReviews.add(review);
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
        return pointOfInterestReviews;
    }

    public List<PointOfInterestReviews> getPointOfInterestReviewsByObjectId(int objectId) throws SQLException {
        List<PointOfInterestReviews> pointOfInterestReviews = new ArrayList<PointOfInterestReviews>();
        String selectPointOfInterestReviews =
            "SELECT PointOfInterestReviewId,Name,CreatedTime,WrittenReview,Rating,ParkId,UserName,ObjectId) " +
            "FROM PointOfInterestReview " +
            "WHERE ObjectId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPointOfInterestReviews);
            selectStmt.setInt(1, objectId);
            results = selectStmt.executeQuery();
            NationalParksDao nationalParksDao = NationalParksDao.getInstance();
            UsersDao usersDao = UsersDao.getInstance();
            PointOfInterestDao pointOfInterestDao = PointOfInterestDao.getInstance();

            while (results.next()) {
                int pointOfInterestReviewId = results.getInt("PointOfInterestReviewId");
                String name = results.getString("Name");
                Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
                String writtenReview = results.getString("WrittenReview");
                int rating = results.getInt("Rating");
                String parkId = results.getString("ParkId");
                String userName = results.getString("UserName");

                NationalParks park = nationalParksDao.getParkById(parkId);
                Users user = usersDao.getUserFromUserName(userName);
                PointOfInterest pointOfInterest = pointOfInterestDao.getPointOfInterestById(objectId);

                PointOfInterestReviews review = new PointOfInterestReviews(pointOfInterestReviewId,
                    name, createdTime, writtenReview, rating, park, user, pointOfInterest);
                pointOfInterestReviews.add(review);
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
        return pointOfInterestReviews;
    }

    public PointOfInterestReviews delete(PointOfInterestReviews pointOfInterestReviews) throws SQLException {
        String deletePointOfInterestReview = "DELETE FROM PointOfInterestReview WHERE PointOfInterestReviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePointOfInterestReview);
            deleteStmt.setInt(1, pointOfInterestReviews.getPointOfInterestReviewId());
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
