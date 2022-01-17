package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LobbySQL {

    private static final String DB_NAME = "mctg_db";
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?serverTimezone=UTC";


    public boolean createGame(Integer gameLobbyID, String username, Integer userScore) throws SQLException {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO mctg_db.gamelobby(gamelobbyid,username,userscore) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, gameLobbyID);
            statement.setString(2, username);
            statement.setInt(3, userScore);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String userScore(String username) throws SQLException {

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

    public int gameStarter(int gameID) throws SQLException {
        int counter = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM gamelobby WHERE GameLobbyID='" + gameID + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                String username = result.getString("GameLobbyID");
                String elopoint = result.getString("UserName");
                counter++;
            }
            return counter;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }


    public String clearLobby(String GameLobbyID) throws SQLException {
        String operation = "false";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM gamelobby WHERE GameLobbyID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, GameLobbyID);
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

}



