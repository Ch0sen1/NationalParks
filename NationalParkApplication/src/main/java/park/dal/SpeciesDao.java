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
import park.model.Species;
import park.model.Species;
import park.model.Species;
import park.model.Species;
import park.model.Users;

public class SpeciesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static SpeciesDao instance = null;
	protected SpeciesDao() {
		connectionManager = new ConnectionManager();
	}
	public static SpeciesDao getInstance() {
		if(instance == null) {
			instance = new SpeciesDao();
		}
		return instance;
	}
	
	public Species create(Species species) throws SQLException {
		String insertSpecies =
				"INSERT INTO Species(SpeciesId,ParkId,CommonName,Category,Family,Occurence,Nativeness) " +
				"VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSpecies);
			insertStmt.setString(1, species.getSpeciesId());
			insertStmt.setString(2, species.getPark().getParkId());
			insertStmt.setString(3, species.getCommonName());
			insertStmt.setString(4, species.getCategory());
			insertStmt.setString(5, species.getFamily());
			insertStmt.setString(6, species.getOccurence());
			insertStmt.setString(7, species.getNativeness());
			insertStmt.executeUpdate();

			return species;
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
	
	public Species getSpeciesById(String speciesId) throws SQLException {
		String selectSpecies =
				"SELECT SpeciesId,ParkId,CommonName,Category,Family,Occurence,Nativeness " +
				"FROM Species " +
				"WHERE SpeciesId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSpecies);
			selectStmt.setString(1, speciesId);
			results = selectStmt.executeQuery();
			NationalParksDao nationalParksDao = NationalParksDao.getInstance();
			if(results.next()) {
				String resultSpeciesId = results.getString("SpeciesId");
				String parkId =  results.getString("ParkId");
				String commonName = results.getString("CommonName");
				String category = results.getString("Category");
				String family = results.getString("Family");
				String occurence = results.getString("Occurence");
				String nativeness = results.getString("Nativeness");
				
				NationalParks park = nationalParksDao.getParkById(parkId);
				Species species = new Species(resultSpeciesId, park, commonName, category, family, occurence, nativeness);
				return species;
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
	
	public List<Species> getSpeciesByParkId(String parkId) throws SQLException {
		List<Species> resultSpecies = new ArrayList<Species>();
		String selectSpecies =
			"SELECT SpeciesId,ParkId,CommonName,Category,Family,Occurence,Nativeness " +
			"FROM Species " +
			"WHERE ParkId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSpecies);
			selectStmt.setString(1, parkId);
			results = selectStmt.executeQuery();
			NationalParksDao nationalParksDao = NationalParksDao.getInstance();
			NationalParks park = nationalParksDao.getParkById(parkId);
			while(results.next()) {
				String resultSpeciesId = results.getString("SpeciesId");
				String commonName = results.getString("CommonName");
				String category = results.getString("Category");
				String family = results.getString("Family");
				String occurence = results.getString("Occurence");
				String nativeness = results.getString("Nativeness");
				
				Species species = new Species(resultSpeciesId, park, commonName, category, family, occurence, nativeness);
				resultSpecies.add(species);
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
		return resultSpecies;
	}	
	
	public Species delete(Species species) throws SQLException {
		String deleteSpecies = "DELETE FROM Species WHERE SpeciesId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSpecies);
			deleteStmt.setString(1, species.getSpeciesId());
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


