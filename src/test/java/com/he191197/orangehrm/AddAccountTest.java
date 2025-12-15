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
public class AddAccountTest extends BaseTest {
    @Test
    @Order(4)
    public void TC04_AddNewAccountSuccessfully() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(normalize-space(.), 'Add')]")
        ));
        addButton.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".oxd-select-text-input")));
        dropdown.click();

        By adminLocator = By.xpath("//div[@role='option' and contains(normalize-space(.), 'Admin')]");
        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(adminLocator));
        adminOption.click();

        driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("Ranga");
        By managerLocator = By.xpath("//div[@role='option' and contains(normalize-space(.), 'Ranga Akunuri')]");
        WebElement managerOption = wait.until(ExpectedConditions.elementToBeClickable(managerLocator));
        managerOption.click();

        WebElement statusDropdown = driver.findElement(
                By.xpath("//label[text()='Status']/following::div[@class='oxd-select-text-input'][1]")
        );
        statusDropdown.click();

        WebElement enabledOption = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='option' and normalize-space()='Enabled']")
                ));
        enabledOption.click();

        WebElement usernameInput = driver.findElement(By.xpath(
                "//label[text()='Username']/following::input[1]"
        ));
        usernameInput.clear();
        usernameInput.sendKeys("John123");

        WebElement passwordInput = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
        passwordInput.sendKeys("John123456");

        WebElement confirmPasswordInput = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
        confirmPasswordInput.sendKeys("John123456");

        WebElement saveButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.oxd-button--secondary")
                ));
        saveButton.click();

        WebElement usernameCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-v-6c07a142='' and text()='John123']")
        ));

        assertEquals("John123", usernameCell.getText());
    }

    @Test
    @Order(5)
    public void TC05_AddNewAccountWithNonExistentEmployee() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(normalize-space(.), 'Add')]")
        ));
        addButton.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".oxd-select-text-input")));
        dropdown.click();

        By adminLocator = By.xpath("//div[@role='option' and contains(normalize-space(.), 'Admin')]");
        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(adminLocator));
        adminOption.click();


        driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("John Tester");

        List<WebElement> managerOptions = driver.findElements(
                By.xpath("//div[@role='option' and contains(normalize-space(.), 'John Tester')]")
        );

        if (!managerOptions.isEmpty()) {
            managerOptions.get(0).click();
        }

        WebElement statusDropdown = driver.findElement(
                By.xpath("//label[text()='Status']/following::div[@class='oxd-select-text-input'][1]")
        );
        statusDropdown.click();

        WebElement enabledOption = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='option' and normalize-space()='Enabled']")
                ));
        enabledOption.click();

        WebElement usernameInput = driver.findElement(By.xpath(
                "//label[text()='Username']/following::input[1]"
        ));
        usernameInput.clear();
        usernameInput.sendKeys("John123");

        WebElement passwordInput = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
        passwordInput.sendKeys("John123456");

        WebElement confirmPasswordInput = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
        confirmPasswordInput.sendKeys("John123456");

        WebElement saveButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.oxd-button--secondary")
                ));
        saveButton.click();

        WebElement errorLabel = driver.findElement(
                By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")
        );
        assertTrue(errorLabel.getText().contains("Invalid"));
    }

    @Test
    @Order(6)
    public void TC06_AddEmployeeConfirmPasswordNotMatch() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(normalize-space(.), 'Add')]")
        ));
        addButton.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".oxd-select-text-input")));
        dropdown.click();

        By adminLocator = By.xpath("//div[@role='option' and contains(normalize-space(.), 'Admin')]");
        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(adminLocator));
        adminOption.click();

        driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("Ranga");
        By managerLocator = By.xpath("//div[@role='option' and contains(normalize-space(.), 'Ranga Akunuri')]");
        WebElement managerOption = wait.until(ExpectedConditions.elementToBeClickable(managerLocator));
        managerOption.click();

        WebElement statusDropdown = driver.findElement(
                By.xpath("//label[text()='Status']/following::div[@class='oxd-select-text-input'][1]")
        );
        statusDropdown.click();

        WebElement enabledOption = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='option' and normalize-space()='Enabled']")
                ));
        enabledOption.click();

        WebElement usernameInput = driver.findElement(By.xpath(
                "//label[text()='Username']/following::input[1]"
        ));
        usernameInput.clear();
        usernameInput.sendKeys("John123");

        WebElement passwordInput = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
        passwordInput.sendKeys("John123456");

        WebElement confirmPasswordInput = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
        confirmPasswordInput.sendKeys("John123");

        WebElement saveButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.oxd-button--secondary")
                ));
        saveButton.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'oxd-input-field-error-message') and text()='Passwords do not match']")
        ));

        assertEquals("Passwords do not match", errorLabel.getText());
    }
}
