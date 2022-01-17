package View;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import Client.Callable;

public class View {

    public void firstMenu(String username, String password, int LobbyID, int loginOpt, int packID) throws SQLException, IOException {
        System.out.println("Welcome to Legends Arena");
        System.out.println("-----------MENU---------");
        System.out.println("1.Login");
        System.out.println("2.Register");
        System.out.println("3.Exit");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the option number:");
        int command = 1;
        switch (command) {
            case 1:
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                String URL = "http://localhost:8001/v1?GET=Login?&username=" + username + "?&password=" + password;
                String aut = Callable.call(URL);
                System.out.println(aut);
                if (aut == "True") {
                    secondMenu(username, password, LobbyID, loginOpt, packID);
                } else {
                    firstMenu(username, password, LobbyID, loginOpt, packID);
                }
                break;
            case 2:
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                URL = "http://localhost:8001/v1?GET=Register?&username=" + username + "?&password=" + password;
                aut = Callable.call(URL);
                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(aut);
                if (aut == "True") {
                    secondMenu(aut, password, LobbyID, loginOpt, packID);
                } else {
                    System.out.println("There is an error on register.");
                    firstMenu(username, password, LobbyID, loginOpt, packID);
                }
                break;

            case 3:
                break;
        }
    }

    private void secondMenu(String username, String password, int LobbyID, int loginOpt, int packID) throws IOException, SQLException {
        //Join Game & Account Settings Menu
        Scanner scan = new Scanner(System.in);
        System.out.println("-----------MENU---------");
        System.out.println("1.Join Game");
        System.out.println("2.Account Settings");
        System.out.println("3.Show Elo List");
        System.out.println("4.Manage Cards");
        System.out.println("5.Logout");
        System.out.println("Enter the option number:");
        Integer command = 1;

        switch (command) {
            case 1:
                //Done.
                GameLobby(username, password, LobbyID, loginOpt, packID);
                break;
            case 2:
                //Done.
                AccountSettings(username, password, LobbyID, loginOpt, packID);
                break;
            case 3:
                String URL = "http://localhost:8001/v1?GET=EloList";
                Callable.call(URL);
                break;
            case 4:
                ManageCard(username, password, LobbyID, 1, packID);
                break;
            case 5:
                URL = "http://localhost:8001/v1?PUT=statusLogout?&username=" + username;
                String statusOff = Callable.call(URL);
                if (statusOff == "True") {
                    System.out.println("See you again.");
                }
                firstMenu(username, password, LobbyID, loginOpt, packID);
                break;
        }
    }

    private void AccountSettings(String username, String password, int LobbyID, int loginOpt, int packID) throws SQLException, IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("-----------MENU---------");
        System.out.println("1.Change Password");
        System.out.println("2.Change Username");
        System.out.println("3.Delete Account");
        System.out.println("4.Back");
        System.out.println("Enter the option number:");
        Integer command = scan.nextInt();


        switch (command) {
            case 1:
                //Done.
                System.out.println("New Password");
                String newPassword = scan.next();
                String URL = "http://localhost:8001/v1?PUT=newPass?&username=" + username + "?&newPassword=" + newPassword;
                Callable.call(URL);
                URL = "http://localhost:8001/v1?PUT=statusLogout?&username=" + username;
                String statusOff = Callable.call(URL);
                if (statusOff == "True") {
                    System.out.println("See you again.");
                }

                break;
            case 2:
                System.out.println("New Username:");
                String newUsername = scan.next();
                URL = "http://localhost:8001/v1?PUT=newUserName?&username=" + username + "?&newPassword=" + newUsername;
                Callable.call(URL);
                URL = "http://localhost:8001/v1?PUT=statusLogout?&username=" + username;
                statusOff = Callable.call(URL);
                if (statusOff == "True") {
                    System.out.println("See you again.");
                }

                break;
            case 3:
                URL = "http://localhost:8001/v1?DELETE=deleteUser?&username=" + username;
                Callable.call(URL);

                break;
            case 4:
                secondMenu(username, password, LobbyID, loginOpt, packID);
                break;
        }
    }

    public void GameLobby(String username, String password, int gameLobbyID1, int loginOpt, int packID) throws SQLException, IOException {
        System.out.println(ANSI_BLUE + "Please Enter the option number that you want to do " + username + "." +
                "\n1.Create Game" +
                "\n2.Join Game" +
                "\n3.Back to Card Store" +
                "\n4.Back" +
                "\n5.Exit " + ANSI_RESET);
        Scanner scan = new Scanner(System.in);

        switch (loginOpt) {
            case 1:
                Random randomObj = new Random();
                int gameLobbyID = gameLobbyID1;
                System.out.println(gameLobbyID);
                String URL = "http://localhost:8001/v1?POST=createGame?&username=" + username + "?&gameLobbyID=" + gameLobbyID;
                Callable.call(URL);
                while (1 != 0) {
                    URL = "http://localhost:8001/v1?GET=startGame?&username=" + username + "?&gameLobbyID=" + gameLobbyID;
                    String PlayerCounter = Callable.call(URL);
                    System.out.println(PlayerCounter);
                    if (PlayerCounter == "True") {
                        break;
                    }
                }
                Arena(username, gameLobbyID, password, loginOpt, packID);
                break;
            case 2:
                System.out.print("Please Enter Room Key: ");
                gameLobbyID = gameLobbyID1;
                URL = "http://localhost:8001/v1?POST=joinGame?&username=" + username + "?&gameLobbyID=" + gameLobbyID;
                String PlayerCounter = Callable.call(URL);
                System.out.println(PlayerCounter);
                while (1 != 0) {
                    URL = "http://localhost:8001/v1?GET=startGame?&username=" + username + "?&gameLobbyID=" + gameLobbyID;
                    PlayerCounter = Callable.call(URL);
                    System.out.println(PlayerCounter);
                    if (PlayerCounter == "True") {
                        break;
                    }
                }
                Arena(username, gameLobbyID, password, loginOpt, packID);
                break;
            case 3:
                ManageCard(username, password, gameLobbyID1, loginOpt, packID);
                break;
            case 4:
                secondMenu(username, password, gameLobbyID1, loginOpt, packID);
                break;
        }
    }

    private void ManageCard(String userName, String password, int LobbyID, int loginOpt, int packID) throws IOException, SQLException {


        System.out.println(ANSI_BLUE + "Please Enter the option number that you want to do." +
                "\n1.Show All Card Packages" +
                "\n2.Show My Card Packages" +
                "\n3.Buy New Card Package" +
                "\n4.Sell Card Package" +
                "\n5.Back " + ANSI_RESET);

        Scanner scan = new Scanner(System.in);
        Integer BlitzcrankOption = loginOpt;
        switch (BlitzcrankOption) {
            case 1:
                System.out.println("Loading....");
                waitMethod(10000);
                String URL = "http://localhost:8001/v1?GET=allCardPackages?&username=" + userName;
                String PlayerCounter = Callable.call(URL);
                System.out.println(PlayerCounter);
                ManageCard(userName, password, LobbyID, 2, packID);
                break;
            case 2:
                System.out.println("Loading....");
                waitMethod(10000);
                URL = "http://localhost:8001/v1?GET=myCardPackages?&username=" + userName;
                Callable.call(URL);
                ManageCard(userName, password, LobbyID, 3, packID);
                break;
            case 3:
                System.out.println("Loading....");
                waitMethod(10000);
                URL = "http://localhost:8001/v1?GET=allCardPackages?&username=" + userName;
                Callable.call(URL);
                waitMethod(10000);
                System.out.print("Please Enter the Package ID to buy: ");
                packID = 14;
                URL = "http://localhost:8001/v1?POST=packageToBuy?&username=" + userName + "?&selectedPack=" + packID;
                Callable.call(URL);
                ManageCard(userName, password, LobbyID, 4, packID);
                break;
            case 4:
                System.out.println("Loading....");
                waitMethod(10000);
                URL = "http://localhost:8001/v1?GET=myCardPackages?&username=" + userName;
                Callable.call(URL);
                System.out.print("Please Enter the Package ID to sell: ");
                packID = 1;
                waitMethod(10000);
                URL = "http://localhost:8001/v1?DELETE=deleteMyCardPackages?&username=" + userName + "?&selectedPack=" + packID;
                Callable.call(URL);
                waitMethod(10000);
                URL = "http://localhost:8001/v1?PUT=updateUserCoinForSoled?&username=" + userName + "?&selectedPack=" + packID;
                Callable.call(URL);
                ManageCard(userName, password, LobbyID, 5, packID);
                break;
            case 5:
                secondMenu(userName, password, LobbyID, loginOpt, packID);
                break;
        }
    }


    private void Arena(String userName, int gameLobbyID, String password, int loginOpt, int packID) throws IOException, SQLException {

        String URL = "http://localhost:8001/v1?GET=showCardPackages?&username=" + userName;
        Callable.call(URL);
        System.out.println("Choose the card package for fight:");
        Scanner scan = new Scanner(System.in);

        URL = "http://localhost:8001/v1?GET=selectedPackage?&username=" + userName + "?&selectedPack=" + packID;
        Callable.call(URL);
        System.out.println("You need to draw one card from your desk. ");
        System.out.println("Choose the Card for remove from your desk: ");

        URL = "http://localhost:8001/v1?GET=holdingCards?&uglyCard=" + 5 + "?&selectedPack=" + packID;
        Callable.call(URL);
        System.out.println("You are ready for fight, fight will started after everyone is ready.");
        System.out.println("Pick a Card to fight, and Enter the Champ name:");

        String userPoint = "0";
        for (int i = 1; i < 5; i++) {

            URL = "http://localhost:8001/v1?POST=pickedCard?&pickedCard=" + i + "?&selectedPack=" + userName + "?&gameLobbyID=" + gameLobbyID + "-" + i + "?&selectedPack=" + packID;
            String BattleCardStatus = Callable.call(URL);
            System.out.println(BattleCardStatus);
            System.out.println(i + ".Raund oynandi.");
            while (1 != 0) {
                URL = "http://localhost:8001/v1?GET=startRound?&gameLobbyID=" + gameLobbyID + "-" + i;
                String PlayerCounter = Callable.call(URL);
                System.out.println(PlayerCounter);
                if (PlayerCounter == "True") {
                    break;
                }
            }
            System.out.println(userPoint);
            URL = "http://localhost:8001/v1?GET=fight?&gameLobbyID=" + gameLobbyID + "-" + i + "?&pickedCard=" + userPoint + "?&username=" + userName;
            System.out.println(URL);
            userPoint = Callable.call(URL);
        }

        int userPointInt = Integer.parseInt(userPoint);

        if (userPointInt <= 0) {
            System.out.println("You lose " + userPoint + " point.");
        } else {
            System.out.println("You earn " + userPoint + " point.");
        }
        URL = "http://localhost:8001/v1?PUT=updateELO?&userName=" + userName + "?&userPoint=" + userPoint;
        Callable.call(URL);
        URL = "http://localhost:8001/v1?DELETE=clearLobby?&lobbyID=" + gameLobbyID;
        Callable.call(URL);

        if (loginOpt == 1) {
            for (int i = 1; i < 5; i++) {
                URL = "http://localhost:8001/v1?DELETE=clearArena?&lobbyID=" + gameLobbyID + "-" + i;
                Callable.call(URL);
            }
        }
        // Callable.call(URL);
        if (packID == 12) {
            secondMenu(userName, password, gameLobbyID, 3, packID);
        } else if (packID == 15) {
            AccountSettings(userName, password, gameLobbyID, 1, packID);
        } else if (packID == 18) {
            AccountSettings(userName, password, gameLobbyID, 2, packID);
        } else if (packID == 10) {
            AccountSettings(userName, password, gameLobbyID, 3, packID);
        }

        // ----------------------------------Developer pointer-------------------------------------------------

    }

    public void waitMethod(int second) {
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
}





