package com.he191197.orangehrm;

;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {


    @Test
    @Order(1)
    public void TC01_LoginSuccessfully() {
        login("Admin", "admin123");
        assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    @Order(2)
    public void TC02_LoginWrongPassword() {
        login("Admin", "wrong123");
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-alert-content"))
        );
        String errorMsg = alert.getText();

        assertTrue(errorMsg.contains("Invalid credentials"));
    }
}
