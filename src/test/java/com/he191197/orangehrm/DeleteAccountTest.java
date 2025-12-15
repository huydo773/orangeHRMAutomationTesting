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
public class DeleteAccountTest extends BaseTest {

    @Test
    @Order(14)
    public void TC14_TestDeleteOtherAccountSuccessfully() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='rowgroup']//div[@role='row'][.//div[text()='John1234']]//button[i[contains(@class,'bi-trash')]][1]")
        ));
        deleteButton.click();

        WebElement yesDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'oxd-button--label-danger') and contains(.,'Yes, Delete')]")
        ));
        yesDeleteButton.click();

        WebElement successMes = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(normalize-space(),'Successfully Deleted')]")));
        assertTrue(successMes.isDisplayed());
    }

    @Test
    @Order(15)
    public void TC15_TestDeleteOwnAccount() {
        login("Admin", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//span[text()='Admin']]")
        ));
        adminMenu.click();

        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@role='rowgroup']//div[@role='row']")
        ));

        for (int i = 1; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            List<WebElement> columns = row.findElements(By.xpath(".//div[@role='cell']"));

            WebElement usernameCell = columns.get(1).findElement(By.xpath(".//div"));
            String username = usernameCell.getText();

            if (username.equals("Admin")) {
                WebElement deleteButton = row.findElement(By.xpath(".//button[i[contains(@class,'bi-trash')]]"));
                wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
                deleteButton.click();
            }
        }


        WebElement errMes = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(normalize-space(),'Cannot be deleted')]")));
        assertTrue(errMes.isDisplayed());
    }
}
