package park.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import park.model.RecommendedHotel;

public class RecommendedHotelDao {
	protected ConnectionManager connectionManager;
	private static RecommendedHotelDao instance = null;
	protected RecommendedHotelDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendedHotelDao getInstance() {
		if(instance == null) {
			instance = new RecommendedHotelDao();
		}
		return instance;
	}
	public RecommendedHotel create(RecommendedHotel recommendedHotel)throws SQLException {
		String insertRecommendedHotel =
				"INSERT INTO RecommendedHotel(HotelName,Phone,Rating,ParkId) VALUES (?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendedHotel,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, recommendedHotel.getHotelName());
			insertStmt.setString(2, recommendedHotel.getPhone());
			insertStmt.setFloat(3, recommendedHotel.getRating());
			insertStmt.setString(4, recommendedHotel.getParkId());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int recommendedHotelId = -1;
			if(resultKey.next()) {
				recommendedHotelId = resultKey.getInt(1);
			}else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendedHotel.setHotelId(recommendedHotelId);
			return recommendedHotel;
		}catch (SQLException e) {
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
	
	public List<RecommendedHotel> getHotelByParkId(String parkId) throws SQLException{
		List<RecommendedHotel> recommendedHotels = new ArrayList<RecommendedHotel>();
		String selectHotels =
				"SELECT HotelId,HotelName,Phone,Rating,ParkId " +
				"FROM RecommendedHotel " +
				"WHERE parkId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHotels);
			selectStmt.setString(1, parkId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int hotelId = results.getInt("HotelId");
				String hotelName = results.getString("HotelName");
				String phone = results.getString("Phone");
				float rating = results.getFloat("Rating");
				String resultParkId = results.getString("ParkId");
		
				RecommendedHotel recommendedHotel = new RecommendedHotel(hotelId,hotelName,phone,rating,resultParkId);
				recommendedHotels.add(recommendedHotel);
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
		return recommendedHotels;
	}
	
	public List<RecommendedHotel> getHotelByRating(float rating) throws SQLException{
		List<RecommendedHotel> recommendedHotels = new ArrayList<RecommendedHotel>();
		String selectHotels =
				"SELECT HotelId,HotelName,Phone,Rating,ParkId " +
				"FROM RecommendedHotel " +
				"WHERE rating=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHotels);
			selectStmt.setFloat(1, rating);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int hotelId = results.getInt("HotelId");
				String hotelName = results.getString("HotelName");
				String phone = results.getString("Phone");
				float resultRating = results.getFloat("Rating");
				String resultParkId = results.getString("ParkId");
		
				RecommendedHotel recommendedHotel = new RecommendedHotel(hotelId,hotelName,phone,resultRating,resultParkId);
				recommendedHotels.add(recommendedHotel);
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
		return recommendedHotels;
	}
	
	public RecommendedHotel delete (RecommendedHotel hotel) throws SQLException {
		String deleteHotel = "DELETE FROM RecommendedHotel WHERE HotelId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHotel);
			deleteStmt.setInt(1, hotel.getHotelId());
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

	

