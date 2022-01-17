package DataAccess;

import java.sql.*;

public class ArenaSQL {

    private static final String DB_NAME = "mctg_db";
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?serverTimezone=UTC";


    public int addCardToArena(String warLobbyID, String username, String cardName, int cardHP, int cardDamage, int cardDefence) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO mctg_db.warlobby(warLobbyID,username,cardName,cardHP,cardDamage,cardDefence) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, warLobbyID);
            statement.setString(2, username);
            statement.setString(3, cardName);
            statement.setInt(4, cardHP);
            statement.setInt(5, cardDamage);
            statement.setInt(6, cardDefence);
            statement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public boolean getCardInfoToAddArena(String cardname, String username, String BattleID, String PackID) {
        String Package = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM champcard WHERE PackageID='" + PackID + "' AND CardPosition='" + cardname + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {

                Integer ChampCardID = result.getInt("ChampCardID");
                String ChampName = result.getString("ChampName");
                Integer ChampHP = result.getInt("ChampHP");
                Integer CampDamage = result.getInt("CampDamage");
                Integer ChampDefence = result.getInt("ChampDefence");
                String PackageName = result.getString("PackageName");
                String output = "| %s | %s | %s | %s  |";
                String Space = "";
                String stringChampHP = String.valueOf(ChampHP);
                String stringChampDamage = String.valueOf(CampDamage);
                String stringChampDefence = String.valueOf(ChampDefence);
                addCardToArena(BattleID, username, ChampName, ChampHP, CampDamage, ChampDefence);
            }
            System.out.println(Package);
            System.out.println("\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public int roundStarter(String BattleID) throws SQLException {
        int counter = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM warlobby WHERE warLobbyID='" + BattleID + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String warLobbyID = result.getString("warLobbyID");
                String username = result.getString("username");
                String cardName = result.getString("cardName");
                int cardHP = result.getInt("cardHP");
                int cardDamage = result.getInt("cardDamage");
                int cardDefence = result.getInt("cardDefence");
                counter++;
            }
            return counter;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }
    //-----------------------------------------------------Fight Logic-------------------------------------------------

    public int fight(String BattleID, String Username) throws SQLException {
        int counter = 0;
        int myID = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM warlobby WHERE warLobbyID='" + BattleID + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int[][] a = new int[4][4];
            int[][] b = new int[4][4];
            int[][] c = new int[4][4];
            int[][] d = new int[4][4];
            String[][] CardName = new String[4][2];
            while (result.next()) {
                String warLobbyID = result.getString("warLobbyID");
                String username = result.getString("username");
                String cardName = result.getString("cardName");
                int cardHP = result.getInt("cardHP");
                int cardDamage = result.getInt("cardDamage");
                int cardDefence = result.getInt("cardDefence");
                String output = "User %d: %s - %s - %s - %d - %d - %d";
                counter++;
                a[counter - 1][0] = cardHP;
                b[counter - 1][0] = cardHP;
                a[counter - 1][1] = cardDamage;
                b[counter - 1][1] = cardDamage;
                a[counter - 1][2] = cardDefence;
                b[counter - 1][2] = cardDefence;
                CardName[counter - 1][0] = username;
                CardName[counter - 1][1] = cardName;
                if (Username.equals(username)) {
                    myID = counter;
                }
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(a[i][j] + " - ");
                }
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    System.out.print(CardName[i][j] + " - ");
                }
            }
            //-------Here will be start the fight, I will be dead......
            while (a[0][0] > 0 && a[1][0] > 0) {
                int myHpAfterFirstAttack = a[0][0] - (a[1][1] - a[0][2]);
                int enemyHpAfterFirstAttack = a[1][0] - (a[0][1] - a[1][2]);
                a[0][0] = myHpAfterFirstAttack;
                a[1][0] = enemyHpAfterFirstAttack;
            }
            while (a[2][0] > 0 && a[3][0] > 0) {
                int myHpAfterFirstAttack = a[2][0] - (a[3][1] - a[2][2]);
                int enemyHpAfterFirstAttack = a[3][0] - (a[2][1] - a[3][2]);
                a[2][0] = myHpAfterFirstAttack;
                a[3][0] = enemyHpAfterFirstAttack;
            }

            if (a[0][0] > a[1][0]) {
                a[0][3] = 1;
                a[1][3] = 0;
            } else {
                a[1][3] = 1;
                a[0][3] = 0;
            }
            if (a[2][0] > a[3][0]) {
                a[2][3] = 1;
                a[3][3] = 0;
            } else {
                a[3][3] = 1;
                a[2][3] = 0;
            }
            for (int i = 0; i < 4; i++) {
                a[i][0] = b[i][0];
            }
            int z = 0;
            int p = 0;
            int u[] = new int[4];
            int l[] = new int[4];
            for (int i = 0; i < 4; i++) {
                if (a[i][3] == 1) {
                    c[p][0] = a[i][0];
                    c[p][1] = a[i][1];
                    c[p][2] = a[i][2];
                    c[p][3] = a[i][3];
                    p++;
                    u[p] = i;
                } else {
                    d[z][0] = a[i][0];
                    d[z][1] = a[i][1];
                    d[z][2] = a[i][2];
                    d[z][3] = a[i][3];
                    z++;
                    l[z] = i;
                }
            }
            while (c[0][0] > 0 && c[1][0] > 0) {
                int myHpAfterFirstAttack = c[0][0] - (c[1][1] - c[0][2]);
                int enemyHpAfterFirstAttack = c[1][0] - (c[0][1] - c[1][2]);
                c[0][0] = myHpAfterFirstAttack;
                c[1][0] = enemyHpAfterFirstAttack;
            }
            while (d[0][0] > 0 && d[1][0] > 0) {
                int myHpAfterFirstAttack = d[0][0] - (d[1][1] - d[0][2]);
                int enemyHpAfterFirstAttack = d[1][0] - (d[0][1] - d[1][2]);
                d[0][0] = myHpAfterFirstAttack;
                d[1][0] = enemyHpAfterFirstAttack;
            }
            if (c[0][0] > c[1][0]) {
                int temp = u[1];
                int temp2 = u[2];
                a[temp][3] += 4;
                a[temp2][3] += 2;
            } else {
                int temp = u[1];
                int temp2 = u[2];
                a[temp][3] += 2;
                a[temp2][3] += 4;
            }
            if (d[0][0] > d[1][0]) {
                int temp = l[1];
                int temp2 = l[2];
                a[temp][3] -= 1;
                a[temp2][3] -= 3;
            } else {
                int temp = l[1];
                int temp2 = l[2];
                a[temp][3] -= 3;
                a[temp2][3] -= 1;
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(a[i][j] + " - ");
                }
            }
            return a[myID - 1][3];
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }

    //-----------------------------------------------------Arena Cleaner-------------------------------------------------
    public String clearArena(String GameLobbyID) throws SQLException {
        String operation = "false";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM warlobby WHERE warLobbyID=?";
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
