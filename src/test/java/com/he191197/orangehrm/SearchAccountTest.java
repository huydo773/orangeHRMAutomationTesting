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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchAccountTest extends BaseTest {
    @Test
    @Order(7)
    public void TC07_SearchAccountWithUsername() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Username']/following::input[1]")
        ));
        usernameInput.clear();
        usernameInput.sendKeys("John123");

        WebElement searchButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Search ']")));
        searchButton.click();

        WebElement usernameCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-v-6c07a142='' and text()='John123']")
        ));

        assertEquals("John123", usernameCell.getText());
    }

    @Test
    @Order(8)
    public void TC08_SearchAccountWithEmpNameExisted() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();
        WebElement managerInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Type for hints...']")
        ));
        managerInput.clear();
        managerInput.sendKeys("Ranga");

        By managerLocator = By.xpath("//div[@role='option' and contains(normalize-space(.), 'Ranga Akunuri')]");
        WebElement managerOption = wait.until(ExpectedConditions.elementToBeClickable(managerLocator));
        managerOption.click();

        WebElement searchButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Search ']")));
        searchButton.click();

        WebElement employeeName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-table-cell') and normalize-space()='Ranga Akunuri']")
        ));

        assertEquals("Ranga Akunuri", employeeName.getText().trim());
    }

    @Test
    @Order(9)
    public void TC09_SearchAccountWithEmpNameNotExisted() {
        login("Admin", "admin123");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();
        WebElement managerInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Type for hints...']")
        ));
        managerInput.clear();
        managerInput.sendKeys("John Tester");

        List<WebElement> managerOptions = driver.findElements(
                By.xpath("//div[@role='option' and contains(normalize-space(.), 'John Tester')]")
        );

        if (!managerOptions.isEmpty()) {
            managerOptions.get(0).click();
        }
        WebElement searchButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Search ']")));
        searchButton.click();

        WebElement errorLabel = driver.findElement(
                By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")
        );
        assertTrue(errorLabel.getText().contains("Invalid"));
    }

}
