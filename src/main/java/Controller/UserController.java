package Controller;

import DataAccess.UserSQL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserController {

    //-----------------------------------------------------LOGIN----------------------------------------------------------

    public String login(String userName, String password) throws SQLException {
        UserSQL userSQL = new UserSQL();
        String aut = userSQL.login(userName, password);
        if (aut == "true") {
            userSQL.statusOnline(userName);
        } else if (aut == "false") {
            System.out.println("Authentication is not successful");
        }
        return aut;
    }

    //-----------------------------------------------------Register-----------------------------------------------------

    public String register(String userName, String password) throws SQLException {
        UserSQL userSQL = new UserSQL();
        String aut = userSQL.create(userName, password);
        if (aut == "true") {
            userSQL.statusOnline(userName);
            System.out.println("New user is created successfully\n");
        } else {
            System.out.println("The chosen username already exists.");
        }
        return aut;
    }

    //-------------------------------------------------------Update Password--------------------------------------------

    public String updatePassword(String userName, String newPassword) throws SQLException {
        UserSQL userSQL = new UserSQL();
        Scanner scan = new Scanner(System.in);

        String operation = "false";
        boolean update = userSQL.updatePassword(userName, newPassword);
        if (update == true) {
            System.out.println("User password is updated successfully");
            operation = "true";
        } else {
            System.out.println("There is a problem on the server, Please try again. ");
        }
        return operation;
    }

    //-------------------------------------------------------Update Username--------------------------------------------

    public String updateUsername(String userName, String newUsername) throws SQLException {

        UserSQL userSQL = new UserSQL();
        String operation = "false";
        boolean update = userSQL.updateUsername(userName, newUsername);

        if (update == true) {
            userSQL.statusOnline(userName);
            System.out.println("Username is updated successfully\n");
            operation = "true";
        } else {
            System.out.println("There is a problem on the server, Please try again. ");
        }
        return operation;
    }

    //----------------------------------------------------Delete Account------------------------------------------------

    public String delete(String userName) throws SQLException {
        UserSQL userSQL = new UserSQL();
        String del = userSQL.delete(userName);
        if (del == "true") {
            System.out.println("The existing user is deleted successfully\n");
        } else {
            System.out.println("There is an problem on the server, Please try again. ");
        }
        return "true";
    }

    //----------------------------------------------------UPDATE USER ELO POINT------------------------------------------------

    public String updateEloPoint(String userName, String point) throws SQLException {
        UserSQL userSQL = new UserSQL();
        boolean del = false;
        try {
            del = userSQL.updateEloPoint(userName, point);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (del == true) {
            System.out.println("The existing user elopoint is updated successfully\n");
        } else {
            System.out.println("There is an problem on the server, Please try again. ");
        }
        return "true";
    }
}




