package Client;

import View.View;

import java.io.IOException;
import java.sql.SQLException;

public class Client {

    public static void main(String[] args) throws InterruptedException, SQLException, IOException {

        Thread[] threads = new Thread[100];
        threads[0] = new Thread(new Callable());
        threads[0].start();
        threads[0].join();
        View view = new View();
        String username = "user5"; //user1-user2-user3-user4
        String password = "1234";
        int LobbyID = 122455;
        int loginOpt = 2; // 1-2-2-2
        int packID = 10;//18-10-12-15
        view.firstMenu(username, password, LobbyID, loginOpt, packID);

    }
    
}
