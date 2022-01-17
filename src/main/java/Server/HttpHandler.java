package Server;

import Controller.*;
import DataAccess.ArenaSQL;
import DataAccess.CardSQL;
import DataAccess.UserSQL;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;


public class HttpHandler implements com.sun.net.httpserver.HttpHandler {
    String EloDataSet;

    @Override

    public void handle(HttpExchange httpExchange) throws IOException {
        UserController userController = new UserController();
        UserSQL userSQL = new UserSQL();
        LobbyController lobbyController = new LobbyController();
        CardController cardController = new CardController();
        ArenaSQL arenaSQL = new ArenaSQL();
        ArenaController arenaController = new ArenaController();
        String requestParamValue = null;
        String aut = null;

//------------------------------------------------Handle Get Requests---------------------------------------------------

        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = getRequest(httpExchange);
            System.out.println("[Server]: Received Method: GET - " + requestParamValue);
            if ("Login".equals(requestParamValue)) {
                String Username = index1(httpExchange);
                String Password = index2(httpExchange);
                try {
                    aut = userController.login(Username, Password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (aut == "true") {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            } else if ("Register".equals(requestParamValue)) {
                String Username = index1(httpExchange);
                String Password = index2(httpExchange);
                System.out.println("[Server]: Received Method: GET - " + requestParamValue);
                try {
                    aut = userController.register(Username, Password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (aut == "true") {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            } else if ("EloList".equals(requestParamValue)) {
                EloDataSet = userSQL.EloList();
                handleResponse(httpExchange, requestParamValue);
                System.out.println("[Server]: Received Method: GET - " + requestParamValue);
            } else if ("startGame".equals(requestParamValue)) {
                String PlayerNumberString = index2(httpExchange);
                int GameLobbyPlayerNumber = Integer.parseInt(PlayerNumberString);
                System.out.println("[Server]: Received Method: GET - " + requestParamValue);
                int GameLobbyPlayerStatus = 0;
                try {
                    GameLobbyPlayerStatus = lobbyController.gameStarter(GameLobbyPlayerNumber);
                    waitMethod(5000);
                    handleResponse(httpExchange, requestParamValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if ("startRound".equals(requestParamValue)) {
                String gameLobbyID = index1(httpExchange);
                System.out.println("[Server]: Received Method: GET - " + requestParamValue);
                int WarLobbyPlayerStatus = 0;
                try {
                    WarLobbyPlayerStatus = arenaController.roundStarter(gameLobbyID);
                    waitMethod(5000);
                    handleResponse(httpExchange, requestParamValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if ("showCardPackages".equals(requestParamValue)) {
                String username = index1(httpExchange);
                System.out.println("[Server]: Received Method: GET - " + requestParamValue);
                cardController.showCardPackages(username);
                handleResponse(httpExchange, requestParamValue);
            } else if ("selectedPackage".equals(requestParamValue)) {
                handleResponse(httpExchange, requestParamValue);
                System.out.println("[Server]: Received Method: GET - " + requestParamValue);
            } else if ("holdingCards".equals(requestParamValue)) {
                handleResponse(httpExchange, requestParamValue);
                System.out.println("[Server]: Received Method: GET - " + requestParamValue);
            } else if ("fight".equals(requestParamValue)) {
                String userPoint = index2(httpExchange);
                System.out.println("[Server]: Received Method: GET - " + requestParamValue);
                handleResponse(httpExchange, requestParamValue);
            } else if ("allCardPackages".equals(requestParamValue)) {
                handleResponse(httpExchange, requestParamValue);
                System.out.println("[Server]: Received Method: GET - " + requestParamValue);
            } else if ("myCardPackages".equals(requestParamValue)) {
                handleResponse(httpExchange, requestParamValue);
                System.out.println("[Server]: Received Method: GET - " + requestParamValue);
            }
        }
//------------------------------------------------Handle POST Requests---------------------------------------------------

        else if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = getRequest(httpExchange);
            Integer LobbyNo = 0;
            if ("createGame".equals(requestParamValue)) {
                String Username = index1(httpExchange);
                String gameLobbyStringID = index2(httpExchange);
                int gameLobbyID = Integer.parseInt(gameLobbyStringID);
                System.out.println("[Server]: Received Method: POST - " + requestParamValue);
                try {
                    LobbyNo = lobbyController.createGame(Username, gameLobbyID);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (LobbyNo != 0) {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            }
            if ("joinGame".equals(requestParamValue)) {
                String Username = index1(httpExchange);
                System.out.println("[Server]: Received Method: POST - " + requestParamValue);
                String gameLobbyStringID = index2(httpExchange);
                int gameLobbyID = Integer.parseInt(gameLobbyStringID);
                try {
                    LobbyNo = lobbyController.createGame(Username, gameLobbyID);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (LobbyNo != 0) {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            }
            if ("pickedCard".equals(requestParamValue)) {
                String PickedCard = index1(httpExchange);
                String PackID = index4(httpExchange);
                System.out.println("[Server]: Received Method: POST - " + requestParamValue);
                String gameLobbyStringID = index2(httpExchange);
                String LobbyID = index3(httpExchange);
                boolean statusCard = arenaSQL.getCardInfoToAddArena(PickedCard, gameLobbyStringID, LobbyID, PackID);
                if (statusCard == true) {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            }
            if ("packageToBuy".equals(requestParamValue)) {
                String usernmame = index1(httpExchange);
                String PickedCardString = index2(httpExchange);
                int PickedCard = Integer.parseInt(PickedCardString);
                System.out.println("[Server]: Received Method: POST - " + requestParamValue);
                int userCoin = 0;
                try {
                    userCoin = userSQL.listUserCoin(usernmame);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                boolean statusCard = false;
                try {
                    userCoin = cardController.buyCardPackage(usernmame, userCoin, PickedCard);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    statusCard = userSQL.updateUserCoin(usernmame, userCoin);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (statusCard == true && userCoin > 0) {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            }
        }

//------------------------------------------------Handle DELETE Requests------------------------------------------------

        else if ("DELETE".equals(httpExchange.getRequestMethod())) {
            System.out.println("[Server]: Received Method: DELETE - " + requestParamValue);
            requestParamValue = getRequest(httpExchange);
            if ("deleteUser".equals(requestParamValue)) {
                String Username = index1(httpExchange);
                try {
                    aut = userController.delete(Username);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (aut == "true") {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            }
            if ("deleteMyCardPackages".equals(requestParamValue)) {
                String Username = index1(httpExchange);
                String packIDStr = index2(httpExchange);
                int userCoin = 0;
                boolean statusCard = false;
                CardSQL cardSQL = new CardSQL();
                int packID = Integer.parseInt(packIDStr);
                boolean status = cardSQL.deleteCardPackage(Username, packID);
                try {
                    userCoin = userSQL.listUserCoin(Username);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                userCoin = userCoin + 5;
                try {
                    statusCard = userSQL.updateUserCoin(Username, userCoin);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (statusCard == true && status == true) {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            }
            if ("clearLobby".equals(requestParamValue)) {
                String LobbyID = index1(httpExchange);
                waitMethod(5000);
                boolean optStatus = lobbyController.clearLobby(LobbyID);
                if (optStatus == true) {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            }
            if ("clearArena".equals(requestParamValue)) {
                String LobbyID = index1(httpExchange);
                boolean optStatus = arenaController.clearArena(LobbyID);
                waitMethod(5000);
                if (optStatus == true) {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            }
        }

//------------------------------------------------Handle PUT Requests---------------------------------------------------

        else if ("PUT".equals(httpExchange.getRequestMethod())) {
            System.out.println("[Server]: Received Method: PUT - " + requestParamValue);
            requestParamValue = getRequest(httpExchange);
            if ("newPass".equals(requestParamValue)) {
                String Username = index1(httpExchange);
                String Password = index2(httpExchange);
                try {
                    aut = userController.updatePassword(Username, Password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (aut == "true") {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            } else if ("newUserName".equals(requestParamValue)) {
                String Username = index1(httpExchange);
                String newUserName = index2(httpExchange);
                System.out.println("[Server]: Received Method: PUT - " + requestParamValue);
                try {
                    aut = userController.updateUsername(Username, newUserName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (aut == "true") {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            } else if ("updateELO".equals(requestParamValue)) {
                System.out.println("[Server]: Received Method: PUT - " + requestParamValue);
                String Username = index1(httpExchange);
                String EloPoint = index2(httpExchange);
                try {
                    aut = userController.updateEloPoint(Username, EloPoint);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (aut == "true") {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            } else if ("statusLogout".equals(requestParamValue)) {
                String Username = index1(httpExchange);
                System.out.println("[Server]: Received Method: PUT - " + requestParamValue);
                aut = userSQL.statusOffline(Username);
                if (aut == "True") {
                    handleResponse(httpExchange, requestParamValue);
                } else {
                    handleBadResponse(httpExchange, requestParamValue);
                }
            }
        }
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        if ("Login".equals(requestParamValue)) {
            String Username = index1(httpExchange);
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Welcome ")
                    .append(Username)
                    .append(" authentication is True.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("Register".equals(requestParamValue)) {
            String Username = index1(httpExchange);
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Welcome ")
                    .append(Username)
                    .append(" registration is successful.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("EloList".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Welcome ")
                    .append(EloDataSet)
                    .append(" registration is successful.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("newPass".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Hello ").
                    append(index1(httpExchange))
                    .append(" your password is updated successfully.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("newUserName".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Hello ").
                    append(index1(httpExchange))
                    .append(" your username is updated successfully.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("deleteUser".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Hello ").
                    append(index1(httpExchange))
                    .append(" your account  is deleted successfully.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("createGame".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Hello ")
                    .append(" your room  is created successfully.")
                    .append("The Room No:")
                    .append(index2(httpExchange))
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("startGame".equals(requestParamValue)) {
            String PlayerNumberString = index2(httpExchange);
            int GameLobbyPlayerNumber = Integer.parseInt(PlayerNumberString);
            int GameLobbyPlayerStatus = 0;
            LobbyController lobbyController = new LobbyController();
            try {
                GameLobbyPlayerStatus = lobbyController.gameStarter(GameLobbyPlayerNumber);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Hello ")
                    .append(" your room  is created successfully.")
                    .append("The Room No:")
                    .append("Waiting for other players ?:" + (4 - GameLobbyPlayerStatus) + "?")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("startRound".equals(requestParamValue)) {
            String gameLobbyID = index1(httpExchange);
            ArenaController arenaController = new ArenaController();
            int WarLobbyPlayerStatus = 0;

            try {
                WarLobbyPlayerStatus = arenaController.roundStarter(gameLobbyID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Hello ")
                    .append(" your room  is created successfully.")
                    .append("The Room No:")
                    .append("Fight has been started ?:" + (4 - WarLobbyPlayerStatus) + "?")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("showCardPackages".equals(requestParamValue)) {
            String username = index1(httpExchange);
            CardController cardController = new CardController();
            String UserCardPackages = cardController.showCardPackages(username);
            htmlBuilder.append("<html>").
                    append("<body>")
                    .append("<h1>")
                    .append("Hello there is your cards.")
                    .append(UserCardPackages)
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("selectedPackage".equals(requestParamValue)) {
            String username = index1(httpExchange);
            String PackIDString = index2(httpExchange);
            CardController cardController = new CardController();
            int packID = Integer.parseInt(PackIDString);
            String selectedPack = cardController.selectedPackage(username, packID);
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Hello there is your selected Package.\n")
                    .append(selectedPack)
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("holdingCards".equals(requestParamValue)) {
            String uglyCardString = index1(httpExchange);
            int uglyCard = Integer.parseInt(uglyCardString);
            String PackIDString = index2(httpExchange);
            CardController cardController = new CardController();
            int packID = Integer.parseInt(PackIDString);
            String selectedPack = cardController.HoldingCards(uglyCard, packID);
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Hello there is your selected Package.\n")
                    .append(selectedPack)
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("fight".equals(requestParamValue)) {
            String username = index3(httpExchange);
            String BattleID = index1(httpExchange);
            String userPointStr = index2(httpExchange);
            int userPoint = Integer.parseInt(userPointStr);
            ArenaController arenaController = new ArenaController();
            int point = arenaController.fight(BattleID, username);
            userPoint = userPoint + point;
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Fight is over. You receive?:")
                    .append(userPoint)
                    .append("? Point. ")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("updateELO".equals(requestParamValue)) {
            UserController userController = new UserController();
            String Username = index1(httpExchange);
            String EloPoint = index2(httpExchange);
            String aut = "false";
            try {
                aut = userController.updateEloPoint(Username, EloPoint);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Update Elo Status: ")
                    .append(aut)
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("allCardPackages".equals(requestParamValue)) {
            String allCardPackages = "";
            CardController cardController = new CardController();
            try {
                allCardPackages = cardController.listCardPackages();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("There is the all Card Packages ")
                    .append(allCardPackages)
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("myCardPackages".equals(requestParamValue)) {
            String username = index1(httpExchange);
            String myCardPackages = "";
            CardController cardController = new CardController();
            myCardPackages = cardController.showCardPackages(username);
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("There is the all Card Packages ")
                    .append(myCardPackages)
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("packageToBuy".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Card package has been added to your Bag.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("deleteMyCardPackages".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>")
                    .append("<h1>")
                    .append("Your Card package has been successfully soled.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("statusLogout".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>")
                    .append("<h1>")
                    .append("You are Logout.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("clearLobby".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>")
                    .append("<h1>")
                    .append("Lobby is clean.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("clearArena".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>")
                    .append("<h1>")
                    .append("Arena is clean.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        // encode HTML content
        String htmlResponse = htmlBuilder.toString();
        // this line is a must
        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private void handleBadResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        if ("Login".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("We are sorry, but authentication is False. Please try again.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("Register".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>")
                    .append("Your registration is not successful. Please try again.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }
        if ("EloList".equals(requestParamValue)) {
            htmlBuilder.append("<html>").
                    append("<body>")
                    .append("<h1>")
                    .append("Your registration is not successful. Please try again.")
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
        }

        // encode HTML content
        String htmlResponse = htmlBuilder.toString();
        // this line is a must
        httpExchange.sendResponseHeaders(400, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();

    }

//------------------------------------------------Parsing URL---------------------------------------------------

    private String getRequest(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI().toString().split("\\?")[1].split("=")[1];
    }

    private String index1(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI().toString().split("\\?")[2].split("=")[1];
    }

    private String index2(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI().toString().split("\\?")[3].split("=")[1];
    }

    private String index3(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI().toString().split("\\?")[4].split("=")[1];
    }

    private String index4(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI().toString().split("\\?")[5].split("=")[1];
    }

    //------------------------------------------------Wait Method---------------------------------------------------
    public void waitMethod(int second) {
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
        }
    }
}
