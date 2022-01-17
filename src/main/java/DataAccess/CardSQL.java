package DataAccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CardSQL {

    private static final String DB_NAME = "mctg_db";
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?serverTimezone=UTC";


    public String listCardPackages(int PackageID) throws SQLException {
        String Package = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM champcard WHERE PackageID='" + PackageID + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int counter = 0;
            Package = "                Package: " + PackageID + "\n------------------------------------------------\n| Champ Name " +
                    "    | HP     | Attack | Defence |\n------------------------------------------------\n";

            while (result.next()) {

                Integer ChampCardID = result.getInt("ChampCardID");
                String ChampName = result.getString("ChampName");
                Integer ChampHP = result.getInt("ChampHP");
                Integer CampDamage = result.getInt("CampDamage");
                Integer ChampDefence = result.getInt("ChampDefence");
                String PackageName = result.getString("PackageName");
                String output = "| %s | %s | %s | %s  |";
                PackageID = result.getInt("PackageID");
                String Space = "";
                String stringChampHP = String.valueOf(ChampHP);
                String stringChampDamage = String.valueOf(CampDamage);
                String stringChampDefence = String.valueOf(ChampDefence);
                Package = String.format(stringBuilder(Package) + "| " + stringBuilder(ChampName) + " | " + stringBuilder2(stringChampHP) + " |" + stringBuilder2(stringChampDamage) + " | " + stringBuilder2(stringChampDefence) + "   |\n------------------------------------------------\n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Package;
    }

    public String listHoldingCards(int PackageID, int uglyCard) throws SQLException {
        String Package = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM champcard WHERE PackageID='" + PackageID + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int counter = 0;
            Package = "                Package: " + PackageID + "\n------------------------------------------------\n| Champ Name " +
                    "    | HP     | Attack | Defence |\n------------------------------------------------\n";
            while (result.next()) {
                Integer ChampCardID = result.getInt("ChampCardID");
                String ChampName = result.getString("ChampName");
                Integer ChampHP = result.getInt("ChampHP");
                Integer CampDamage = result.getInt("CampDamage");
                Integer ChampDefence = result.getInt("ChampDefence");
                String PackageName = result.getString("PackageName");
                Integer CardPosition = result.getInt("CardPosition");
                String output = "| %s | %s | %s | %s  |";
                PackageID = result.getInt("PackageID");
                String Space = "";
                String stringChampHP = String.valueOf(ChampHP);
                String stringChampDamage = String.valueOf(CampDamage);
                String stringChampDefence = String.valueOf(ChampDefence);
                if (CardPosition.equals(uglyCard)) {
                    Package = Package;
                } else {
                    Package = String.format(stringBuilder(Package) + "| " + stringBuilder(ChampName) + " | " + stringBuilder2(stringChampHP) + " |" + stringBuilder2(stringChampDamage) + " | " + stringBuilder2(stringChampDefence) + "   |\n------------------------------------------------\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Package;
    }


    public int addCardPackage(String username, int userCoin, int packageID) {
        String id = username + "-" + packageID;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO mctg_db.usercardpackages(id,username,packid) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            userCoin = userCoin - 5;
            statement.setString(1, id);
            statement.setString(2, username);
            statement.setInt(3, packageID);
            statement.executeUpdate();
            return userCoin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userCoin;
    }

    public String showUserCardPackages(String username) {
        int packID = 0;
        String allPackages = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM usercardpackages WHERE username='" + username + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                packID = result.getInt("PackID");
                allPackages = allPackages + listCardPackages(packID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPackages;
    }

    public String selectedPackage(String username, int packID) {
        int packIDs = packID;
        String Package = null;
        try {
            Package = listCardPackages(packIDs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Package;
    }

    public String holdingCards(int uglyCard, int packID) {
        int packIDs = packID;
        String Package = null;
        try {
            Package = listHoldingCards(packIDs, uglyCard);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Package;
    }


    public boolean deleteCardPackage(String username, int packID) {
        String id = username + "-" + packID;
        boolean operation = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM usercardpackages WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
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

    public String stringBuilder(String string) {

        String s = string;
        int countString = s.length();
        for (int i = 0; i <= 13 - countString; i++) {
            s = s + " ";
        }
        return s;
    }

    public String stringBuilder2(String string) {

        String s = string;
        int countString = s.length();
        for (int i = 0; i <= 5 - countString; i++) {
            s += new String(" ");
        }
        return s;
    }
}


