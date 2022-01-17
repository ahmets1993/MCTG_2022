package Test.Controller;

import Controller.ArenaController;
import Controller.UserController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest {


    @Test
    void testLogin() throws SQLException {
        assertEquals("true", new UserController().login("user1", "1234"));
    }

    @Test
    void testLogin2() throws SQLException {
        assertEquals("false", new UserController().login("user1", "12345"));
    }

    @Test
    void testDeleteUser() throws SQLException {
        assertEquals("true", new UserController().delete("user11111"));
    }
}