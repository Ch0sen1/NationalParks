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

import park.model.*;

public class ReviewsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
				"INSERT INTO Reviews(CreatedTime,WrittenReview,Rating,UserName,ParkId) " +
				"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setTimestamp(1, new Timestamp(review.getCreatedTime().getTime()));
			insertStmt.setString(2, review.getWrittenReview());
			insertStmt.setInt(3, review.getRating());
			insertStmt.setString(4, review.getUser().getUserName());
			insertStmt.setString(5, review.getPark().getParkId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectReview =
				"SELECT ReviewId, CreatedTime,WrittenReview,Rating,UserName,ParkId " +
				"FROM Reviews " +
				"WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			NationalParksDao nationalParksDao = NationalParksDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			if(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());
				String writtenReview = results.getString("WrittenReview");
				int rating = results.getInt("Rating");
				String userName = results.getString("UserName");
				String parkId = results.getString("ParkId");
				
				NationalParks park = nationalParksDao.getParkById(parkId);
				Users user = usersDao.getUserFromUserName(userName);
				Reviews review = new Reviews(resultReviewId, createdTime, writtenReview, rating, user, park);
				return review;
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
	
	public List<Reviews> getReviewsByUserName(String userName) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT ReviewId, CreatedTime,WrittenReview,Rating,UserName,ParkId " +
			"FROM Reviews " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			NationalParksDao nationalParksDao = NationalParksDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			Users user = usersDao.getUserFromUserName(userName);
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());
				String writtenReview = results.getString("WrittenReview");
				int rating = results.getInt("Rating");
				String parkId = results.getString("ParkId");
				
				NationalParks park = nationalParksDao.getParkById(parkId);
				Reviews review = new Reviews(reviewId, createdTime, writtenReview, rating, user, park);
				reviews.add(review);
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
		return reviews;
	}
	
	public List<Reviews> getReviewsByParkId(String parkId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT ReviewId, CreatedTime,WrittenReview,Rating,UserName,ParkId " +
			"FROM Reviews " +
			"WHERE ParkId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, parkId);
			results = selectStmt.executeQuery();
			NationalParksDao nationalParksDao = NationalParksDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			NationalParks park = nationalParksDao.getParkById(parkId);
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());
				String writtenReview = results.getString("WrittenReview");
				int rating = results.getInt("Rating");
				String userName = results.getString("UserName");
				
				Users user = usersDao.getUserFromUserName(userName);
				
				Reviews review = new Reviews(reviewId, createdTime, writtenReview, rating, user, park);
				reviews.add(review);
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
		return reviews;
	}	

	
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
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
