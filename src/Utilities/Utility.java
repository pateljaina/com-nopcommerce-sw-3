package Utilities;

import browserfactory.BaseTest;
import org.openqa.selenium.By;

public class Utility extends BaseTest {

    public void clickOnElement(By by) {
        driver.findElement(by).click();//to use this needs to extend LoginTest utility case
    }

    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);//this method will send Text to element
    }

    public String getTextElement(By by) {
        return driver.findElement(by).getText();//gets actual message from console

    }

}
