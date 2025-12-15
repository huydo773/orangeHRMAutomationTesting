package com.he191197.orangehrm;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void login(String username, String password) {
        WebElement usernameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("username"))
        );
        usernameInput.sendKeys(username);

        WebElement passwordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("password"))
        );
        passwordInput.sendKeys(password);

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))
        );
        submitButton.click();
    }
}
