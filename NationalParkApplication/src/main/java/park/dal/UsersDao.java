package park.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import park.model.Users;


public class UsersDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static UsersDao instance = null;
    protected UsersDao() {
        connectionManager = new ConnectionManager();
    }
    public static UsersDao getInstance() {
        if(instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }

    /**
     * Save the Persons instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Users create(Users user) throws SQLException {
        String insertUser = "INSERT INTO Users(UserName,Password,FirstName,LastName,Email,PhoneNumber,UserType) VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser);

            insertStmt.setString(1, user.getUserName());
            insertStmt.setString(2, user.getPassWord());
            insertStmt.setString(3, user.getFirstName());
            insertStmt.setString(4, user.getLastName());
            insertStmt.setString(5, user.getEmail());
            insertStmt.setString(6, user.getPhoneNumber());
            insertStmt.setString(7,user.getUserType().name());

            insertStmt.executeUpdate();

            // Note 1: if this was an UPDATE statement, then the person fields should be
            // updated before returning to the caller.
            // Note 2: there are no auto-generated keys, so no update to perform on the
            // input param person.
            return user;
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
     * Delete the User instance.
     * This runs a DELETE statement.
     */
    public Users delete(Users user) throws SQLException {
        String deleteUser = "DELETE FROM Users WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setString(1, user.getUserName());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Persons instance.
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


    public Users getUserFromUserName(String userName) throws SQLException {
        String selectPerson = "SELECT UserName,PassWord,FirstName,LastName,Email,PhoneNumber,UserType FROM Users WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPerson);
            selectStmt.setString(1, userName);

            results = selectStmt.executeQuery();

            if(results.next()) {
                String resultUserName = results.getString("UserName");
                String passWord = results.getString("Password");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String email = results.getString("Email");
                String phone = results.getString("PhoneNumber");
                Users.UserType userType = Users.UserType.valueOf(results.getString("UserType"));
                

                Users user = new Users(resultUserName, passWord, firstName, lastName, email, phone, userType);
                return user;
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
}
