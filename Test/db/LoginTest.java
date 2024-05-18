package db;

import dbController.LoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {


        @Test
        public void test() {
            LoginController loginController = new LoginController();
            boolean result = loginController.login("linhsan", "123456");
            assertEquals(true, result);  // This assertion is failing
        }
    }

