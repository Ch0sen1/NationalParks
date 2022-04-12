package park.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import park.model.RecommendedRestaurant;

public class RecommendedRestaurantDao {
	protected ConnectionManager connectionManager;
	private static RecommendedRestaurantDao instance = null;
	protected RecommendedRestaurantDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendedRestaurantDao getInstance() {
		if(instance == null)  {
			instance = new RecommendedRestaurantDao();
		}
		return instance;
	}
	public RecommendedRestaurant create(RecommendedRestaurant recommendedRestaurant)throws SQLException {
		String insertRecommendedRestaurant = 
				"INSERT INTO RecommendedRestaurant(RestaurantName,Phone,Rating,ParkId) VALUES (?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendedRestaurant,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, recommendedRestaurant.getRestaurantName());
			insertStmt.setString(2, recommendedRestaurant.getPhone());
			insertStmt.setFloat(3, recommendedRestaurant.getRating());
			insertStmt.setString(4, recommendedRestaurant.getParkId());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int recommendedRestaurantId = -1;
			if(resultKey.next()) {
				recommendedRestaurantId = resultKey.getInt(1);
			}else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendedRestaurant.setRestaurantId(recommendedRestaurantId);
			return recommendedRestaurant;
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
	
	public List<RecommendedRestaurant> getRestaurantByParkId(String parkId) throws SQLException{
		List<RecommendedRestaurant> recommendedRestaurants = new ArrayList<RecommendedRestaurant>();
		String selectRestaurants = 
				"SELECT RestaurantId,RestaurantName,Phone,Rating,ParkId " +
				"FROM RecommendedRestaurant " +
				"WHERE ParkId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, parkId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("RestaurantName");
				String phone = results.getString("Phone");
				float rating = results.getFloat("Rating");
				String resultParkId = results.getString("ParkId");
				RecommendedRestaurant recommendedRestaurant = new RecommendedRestaurant(restaurantId,restaurantName,phone,rating,resultParkId);
				recommendedRestaurants.add(recommendedRestaurant);
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
		return recommendedRestaurants;

	}
	
	public List<RecommendedRestaurant> getRestaurantByRating(float rating) throws SQLException{
		List<RecommendedRestaurant> recommendedRestaurants = new ArrayList<RecommendedRestaurant>();
		String selectRestaurants = 
				"SELECT RestaurantId,RestaurantName,Phone,Rating,ParkId " +
				"FROM RecommendedRestaurant " +
				"WHERE Rating=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setFloat(1, rating);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("RestaurantName");
				String phone = results.getString("Phone");
				float resultRating = results.getFloat("Rating");
				String resultParkId = results.getString("ParkId");
				RecommendedRestaurant recommendedRestaurant = new RecommendedRestaurant(restaurantId,restaurantName,phone,resultRating,resultParkId);
				recommendedRestaurants.add(recommendedRestaurant);
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
		return recommendedRestaurants;

	}
	
	public RecommendedRestaurant delete (RecommendedRestaurant restaurant) throws SQLException {
		String deleteRestaurant = "DELETE FROM RecommendedRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, restaurant.getRestaurantId());
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
