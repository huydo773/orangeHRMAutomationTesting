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
public class FilterAccountTest extends BaseTest{

    @Test
    @Order(10)
    public void TC10_FilterByUserRole() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();

        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')]")
        ));
        roleDropdown.click();

        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option' and normalize-space()='Admin']")
        ));
        adminOption.click();

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Search']")
        ));
        searchButton.click();

        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@role='rowgroup']//div[@role='row']")
        ));

        for (int i = 1; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            List<WebElement> columns = row.findElements(By.xpath(".//div[@role='cell']"));

            WebElement userRoleCell = columns.get(2).findElement(By.xpath(".//div"));
            String userRole = userRoleCell.getText();

            assertEquals("Admin", userRole);
        }
    }

    @Test
    @Order(11)
    public void TC11_FilterByStatus() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();

        WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')]")
        ));
        statusDropdown.click();

        WebElement enabledOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option' and normalize-space()='Enabled']")
        ));
        enabledOption.click();

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Search']")
        ));
        searchButton.click();

        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@role='rowgroup']//div[@role='row']")
        ));

        for (int i = 1; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            List<WebElement> columns = row.findElements(By.xpath(".//div[@role='cell']"));

            WebElement statusCell = columns.get(4).findElement(By.xpath(".//div"));
            String status = statusCell.getText();

            assertEquals("Enabled", status);
        }
    }
}
