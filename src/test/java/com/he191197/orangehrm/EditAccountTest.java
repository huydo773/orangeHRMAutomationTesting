package com.he191197.orangehrm;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EditAccountTest extends BaseTest{

    @Test
    @Order(12)
    public void TC12_EditAccountSuccessfully() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();

        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='rowgroup']//div[@role='row'][.//div[text()='John123']]//button[i[contains(@class,'bi-pencil-fill')]]")
        ));
        editButton.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".oxd-select-text-input")));
        dropdown.click();

        By adminLocator = By.xpath("//div[@role='option' and contains(normalize-space(.), 'ESS')]");
        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(adminLocator));
        adminOption.click();

        WebElement employeeInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='Type for hints...']"))
        );
        employeeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        employeeInput.sendKeys(Keys.DELETE);
        employeeInput.sendKeys("John");
        By managerLocator = By.xpath("//div[@role='option' and contains(normalize-space(.), 'John Doe')]");
        WebElement managerOption = wait.until(ExpectedConditions.elementToBeClickable(managerLocator));
        managerOption.click();


        WebElement statusDropdown = driver.findElement(
                By.xpath("//label[text()='Status']/following::div[@class='oxd-select-text-input'][1]")
        );
        statusDropdown.click();

        WebElement enabledOption = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='option' and normalize-space()='Disabled']")
                ));
        enabledOption.click();

        WebElement usernameInput = driver.findElement(By.xpath(
                "//label[text()='Username']/following::input[1]"
        ));
        usernameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        usernameInput.sendKeys(Keys.DELETE);
        usernameInput.sendKeys("John1234");

        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[input[@type='checkbox' and @true-value='true']]")
        ));
        label.click();

        WebElement passwordInput = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
        passwordInput.sendKeys("John0711");

        WebElement confirmPasswordInput = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
        confirmPasswordInput.sendKeys("John0711");

        WebElement saveButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.oxd-button--secondary")
                ));
        saveButton.click();

        WebElement usernameCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-v-6c07a142='' and text()='John1234']")
        ));

        assertEquals("John1234", usernameCell.getText());
    }

    @Test
    @Order(13)
    public void TC13_EditAccountNonExistentEmployee() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();

        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='rowgroup']//div[@role='row'][.//div[text()='John1234']]//button[i[contains(@class,'bi-pencil-fill')]]")
        ));
        editButton.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".oxd-select-text-input")));
        dropdown.click();

        By adminLocator = By.xpath("//div[@role='option' and contains(normalize-space(.), 'Admin')]");
        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(adminLocator));
        adminOption.click();

        WebElement employeeInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='Type for hints...']"))
        );
        employeeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        employeeInput.sendKeys(Keys.DELETE);
        employeeInput.sendKeys("John Tester");


        WebElement statusDropdown = driver.findElement(
                By.xpath("//label[text()='Status']/following::div[@class='oxd-select-text-input'][1]")
        );
        statusDropdown.click();

        WebElement enabledOption = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='option' and normalize-space()='Disabled']")
                ));
        enabledOption.click();

        WebElement usernameInput = driver.findElement(By.xpath(
                "//label[text()='Username']/following::input[1]"
        ));
        usernameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        usernameInput.sendKeys(Keys.DELETE);
        usernameInput.sendKeys("John1234");

        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[input[@type='checkbox' and @true-value='true']]")
        ));
        label.click();

        WebElement passwordInput = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
        passwordInput.sendKeys("John0711");

        WebElement confirmPasswordInput = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
        confirmPasswordInput.sendKeys("John0711");

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
}
