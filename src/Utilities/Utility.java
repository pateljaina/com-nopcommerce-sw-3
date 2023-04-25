package utilities;

import browsertesting.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {
    public void clickOnElement(By by) {
        driver.findElement(by).click();

    }

    public String getTextElement(By by) {
        return driver.findElement(by).getText();//gets actual message from console
    }

    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);

    }

    public void selectByValueFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByValue(text);
    }

    public void selectByIndexFromDropDown(By by, int a) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByIndex(a);
    }

    public void mouseHoverToElementAndCLick(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().build().perform();

    }

    public void mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);//this method will send Text to element


    }

    public void verifyText(String expectedMessage, By by, String message) {
        Assert.assertEquals(message, driver.findElement(by), expectedMessage);
    }

    public void switchToAlert() {

        driver.switchTo().alert();
    }

    public void acceptAlert() {

        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {

        driver.switchTo().alert().dismiss();
    }

    public void sendKeysToAlert(String text) {

        driver.switchTo().alert().sendKeys(text);
    }
}







