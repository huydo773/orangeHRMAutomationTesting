package com.he191197.orangehrm;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogoutTest extends BaseTest{

    @Test
    @Order(16)
    public void TC16_LogoutSuccessfully() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement userDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[@class='oxd-userdropdown-name']")
        ));
        userDropdown.click();

        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Logout']")
        ));
        logoutBtn.click();

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("username")
        ));

        assertTrue(usernameInput.isDisplayed());
    }
}
