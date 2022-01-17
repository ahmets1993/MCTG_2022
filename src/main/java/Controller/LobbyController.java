package Controller;

import DataAccess.LobbySQL;

import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class LobbyController {


    //-----------------------------------------------------Create a Game -----------------------------------------------

    public Integer createGame(String username, Integer gameLobbyID) throws SQLException {
        LobbySQL lobbySQL = new LobbySQL();
        String score = lobbySQL.userScore(username);
        lobbySQL.createGame(gameLobbyID, username, parseInt(score));
        return gameLobbyID;
    }

    //-----------------------------------------------------Game Starter-------------------------------------------------

    public int gameStarter(int gameID) throws SQLException {
        LobbySQL lobbySQL = new LobbySQL();
        int PlayerCount = 0;
        PlayerCount = lobbySQL.gameStarter(gameID);
        return PlayerCount;
    }

    //-----------------------------------------------------Lobby Cleaner------------------------------------------------
    public boolean clearLobby(String GameLobbyID) {
        LobbySQL lobbySQL = new LobbySQL();
        try {
            lobbySQL.clearLobby(GameLobbyID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}


















