package park.dal;
import park.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrailsDao {

  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static TrailsDao instance = null;
  protected TrailsDao() {
    connectionManager = new ConnectionManager();
  }
  public static TrailsDao getInstance() {
    if(instance == null) {
      instance = new TrailsDao();
    }
    return instance;
  }

  public Trails create(Trails trails) throws SQLException{
    String inserTrail = "INSERT INTO Trail(TrailName, ParkId, GeoLocation, Popularity, Length,"
        + "Evaluation, Difficulty, RouteType, Features, Activity) "
        + "VALUES(?,?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;

    try{
      connection = connectionManager.getConnection();

      insertStmt = connection.prepareStatement(inserTrail, Statement.RETURN_GENERATED_KEYS);

      insertStmt.setString(1, trails.getTrailName());
      insertStmt.setString(2, trails.getParkId());
      insertStmt.setString(3, trails.getGeoLocation());
      insertStmt.setString(4, trails.getPopularity());
      insertStmt.setFloat(5, trails.getLength());
      insertStmt.setFloat(6, trails.getEvaluation());
      insertStmt.setInt(7, trails.getDifficulty());
      insertStmt.setString(8, trails.getRouteType());
      insertStmt.setString(9, trails.getFeatures());
      insertStmt.setString(10, trails.getActivity());

      insertStmt.executeUpdate();
      resultKey = insertStmt.getGeneratedKeys();
      int trailId = -1;
      if(resultKey.next()) {
        trailId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      trails.setTrailId(trailId);

      return trails;
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
      if(resultKey != null) {
        resultKey.close();
      }
    }
  }

  public Trails delete(Trails trails) throws SQLException {
    String deleteReview = "DELETE FROM Trail WHERE TrailId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReview);
      deleteStmt.setInt(1, trails.getTrailId());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the BlogPosts instance.
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

  public Trails getTrailsFromTrailID(Integer trailId) throws SQLException {
    String selectTrails = "SELECT TrailId, TrailName, ParkId, GeoLocation, Popularity,Length, "
        + "Evaluation, Difficulty, RouteType, Features, Activity FROM Trail WHERE TrailId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectTrails);
      selectStmt.setInt(1, trailId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        int resultTrailId = results.getInt("TrailId");
        String resultTrailName = results.getString("TrailName");
        String resultParkId = results.getString("ParkId");
        String resultgLocation = results.getString("GeoLocation");
        String resultPopularity = results.getString("Popularity");
        Float resultLength = results.getFloat("Length");
        Float resultEvaluation = results.getFloat("Evaluation");
        int resultDifficulty = results.getInt("Difficulty");
        String resultRouteType = results.getString("RouteType");
        String resultFeatures = results.getString("Features");
        String resultActivity = results.getString("Activity");


        Trails trails = new Trails(resultTrailId, resultTrailName,resultParkId, resultgLocation,resultPopularity,resultLength,resultEvaluation,resultDifficulty
        ,resultRouteType,resultFeatures,resultActivity);


        return trails;
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

  public List<Trails> getTrailsFromParkID(String parkID) throws SQLException {
    List<Trails> trails = new ArrayList<>();
    String selectTrails = "SELECT TrailId, TrailName, ParkId, GeoLocation, Popularity,Length, "
        + "Evaluation, Difficulty, RouteType, Features, Activity FROM Trail WHERE ParkId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectTrails);
      selectStmt.setString(1, parkID);
      results = selectStmt.executeQuery();

      while (results.next()) {
        Integer resultTrailId = results.getInt("TrailId");
        String resultTrailName = results.getString("TrailName");
        String resultParkId = results.getString("ParkId");
        String resultgLocation = results.getString("GeoLocation");
        String resultPopularity = results.getString("Popularity");
        Float resultLength = results.getFloat("Length");
        Float resultEvaluation = results.getFloat("Evaluation");
        Integer resultDifficulty = results.getInt("Difficulty");
        String resultRouteType = results.getString("RouteType");
        String resultFeatures = results.getString("Features");
        String resultActivity = results.getString("Activity");

        Trails trail = new Trails(resultTrailId, resultTrailName,resultParkId, resultgLocation,resultPopularity,resultLength,resultEvaluation,resultDifficulty
                ,resultRouteType,resultFeatures,resultActivity);
        trails.add(trail);
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
    return trails;
  }








}
