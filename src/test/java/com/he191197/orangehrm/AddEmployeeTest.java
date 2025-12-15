package com.he191197.orangehrm;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddEmployeeTest extends BaseTest {
    @Test
    @Order(3)
    public void TC03_AddEmployeeTestSuccessfully() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement PimMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='PIM']]")
        ));
        PimMenu.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(normalize-space(.), 'Add')]")
        ));
        addButton.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='firstName']")));
        firstName.sendKeys("Ranga");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='lastName']")));
        lastName.sendKeys("Akunuri");

        WebElement saveButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.oxd-button--secondary")
                ));
        saveButton.click();

        WebElement successMes =wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(normalize-space(),'Successfully Saved')]")));
        assertTrue(successMes.isDisplayed());

    }
}
