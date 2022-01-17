package Test.Controller;

import Controller.ArenaController;
import Controller.LobbyController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LobbyControllerTest {


    @Test
    void testGameStarter() throws SQLException {
        assertEquals(0, new LobbyController().gameStarter(111));
    }

    @Test
    void testClearLobby() throws SQLException {
        assertEquals(true, new LobbyController().clearLobby("111"));

    }
}