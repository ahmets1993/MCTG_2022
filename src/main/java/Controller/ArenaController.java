package Controller;

import DataAccess.ArenaSQL;

import java.sql.SQLException;

public class ArenaController {

    //-----------------------------------------------------Round Starter-------------------------------------------------
    public int roundStarter(String BattleID) throws SQLException {
        ArenaSQL arenaSQL = new ArenaSQL();
        int PlayerCount = 0;
        PlayerCount = arenaSQL.roundStarter(BattleID);
        return PlayerCount;
    }

    //-----------------------------------------------------Fight-------------------------------------------------
    public int fight(String BattleID, String Username) {
        ArenaSQL arenaSQL = new ArenaSQL();
        int fight = 0;
        try {
            fight = arenaSQL.fight(BattleID, Username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fight;
    }

    //-----------------------------------------------------Arena Cleaner-------------------------------------------------
    public boolean clearArena(String GameLobbyID) {
        ArenaSQL arenaSQL = new ArenaSQL();
        try {
            arenaSQL.clearArena(GameLobbyID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
