package DataAccess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserSQL {

    private final String DB_NAME = "mctg_db";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "root";
    private final String DB_URL = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?serverTimezone=UTC";

    
    //-----------------------------------------------------------LOGIN--------------------------------------------------

    public String login(String username, String password
    ) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM users";
            ResultSet r = connection.createStatement().executeQuery(sql);
            while (r.next()) {
                if (username.equals(r.getString("username"))
                        && password.equals(r.getString("password"))) {
                    return "true";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "false";
    }


    //---------------------------------------------------CREATE---------------------------------------------------------


    public String create(String username, String password) throws SQLException {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO mctg_db.users(idusers,username,password,elopoint,usercash) VALUES (?,?,?,?,?)";
            String elopoint = "100";
            String usercash = "20";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 1);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, elopoint);
            statement.setString(5, usercash);
            statement.executeUpdate();
            return "true";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "false";
    }

    //---------------------------------------------------UPDATE USERNAME------------------------------------------------

    public boolean updateUsername(String username, String newUsername) throws SQLException {
        boolean operation = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE users SET username=? WHERE username='" + username + "'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newUsername);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                statusOffline(newUsername);
                operation = true;

            } else {
                System.out.println("There is an error.!");
                operation = false;
            }
            return operation;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operation;
    }


    //---------------------------------------------------UPDATE PASSWORD------------------------------------------------


    public boolean updatePassword(String username, String newPassword) throws SQLException {
        boolean operation = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE users SET password=? WHERE username='" + username + "'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                operation = true;
            } else {
                System.out.println("There is an error.!");
                operation = false;
            }
            return operation;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operation;
    }


    //---------------------------------------------------DELETE USER----------------------------------------------------


    public String delete(String username) throws SQLException {
        String operation = "false";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Users WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                operation = "true";
            } else {
                System.out.println("There is an error.!");
                operation = "false";
            }
            return operation;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operation;
    }


    //---------------------------------------------------Print Elo List-------------------------------------------------

    public String EloList() throws IOException {
        String data = "";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM users ORDER BY elopoint DESC";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int counter = 0;
            int i = 0;
            while (result.next()) {
                String username = result.getString("username");
                String elopoint = result.getString("elopoint");
                data = data + "User " + i + ": " + username + " - " + elopoint + "\n";
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //---------------------------------------------------Print Elo List-------------------------------------------------

    public String getUserEloPoint(String username) throws IOException {
        String elopoint = "";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            String sql = "SELECT * FROM users WHERE username='" + username + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int counter = 0;
            while (result.next()) {
                username = result.getString("username");
                elopoint = result.getString("elopoint");
                counter++;
            }
            return elopoint;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elopoint;
    }


    //---------------------------------------------------Print Elo List-------------------------------------------------

    public boolean updateEloPoint(String username, String point) throws IOException {
        UserSQL userSQL = new UserSQL();
        String eloPointString = userSQL.getUserEloPoint(username);
        String extraPointString = point;
        int eloPoint = Integer.parseInt(eloPointString);
        int extraEloPoint = Integer.parseInt(extraPointString);
        int newEloPoint = eloPoint + extraEloPoint;

        boolean operation = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE users SET elopoint=? WHERE username='" + username + "'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newEloPoint);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                operation = true;
            } else {
                System.out.println("There is an error.!");
                operation = false;
            }
            return operation;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operation;
    }

    //--------------------------------------------------Get User LeagueCoin-----------------------------------------

    public int listUserCoin(String username) throws SQLException {
        int usercash = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM users WHERE username='" + username + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int counter = 0;
            while (result.next()) {
                username = result.getString("username");
                usercash = result.getInt("usercash");

                return usercash;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usercash;
    }

    //--------------------------------------------------Update User LeagueCoin-----------------------------------------

    public boolean updateUserCoin(String username, int newCoinValue) throws SQLException {
        boolean operation = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE users SET usercash=? WHERE username='" + username + "'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newCoinValue);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                operation = true;
            } else {
                System.out.println("There is an error.!");
                operation = false;
            }
            return operation;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operation;
    }


    //--------------------------------------------------Make User Status Online-----------------------------------------

    public String statusOnline(String username) {


        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE users SET status=? WHERE username='" + username + "'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "1");
            int rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;

    }

    //--------------------------------------------------Make User Status Offline----------------------------------------

    public String statusOffline(String username) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE users SET status=? WHERE username='" + username + "'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "0");
            int rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "True";
    }
}