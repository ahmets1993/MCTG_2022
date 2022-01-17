package Test.Controller;

import Controller.ArenaController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ArenaControllerTest {


    @Test
    void testRoundStarter() throws SQLException {
        assertEquals(0, new ArenaController().roundStarter("1000"));
    }

    @Test
    void testClearArena() {
        assertEquals(true, new ArenaController().clearArena("1000"));
    }


}

