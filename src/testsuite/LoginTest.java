package testsuite;

import Utilities.Utility;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LoginTest extends Utility {

    String baseUrl = "https://www.saucedemo.com";

    @Before
    public void openBrowser() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        sendTextToElement(By.xpath("//input[@id='user-name']"), "standard_user");//Locate Username field and enter username
        sendTextToElement(By.id("password"), "secret_sauce");//Locate Password field and enter password
        clickOnElement(By.id("login-button"));//click on login button
        String expectedMessage = "Products";
        String actualMessage = getTextElement(By.xpath("//span[contains(text(),'Products')]"));
        Assert.assertEquals("Not directed to Products page", expectedMessage, actualMessage);
    }

    @Test
    public void verifyThatSixProductsAreDisplayedOnPage() {
        sendTextToElement(By.xpath("//input[@id='user-name']"), "standard_user");//Locate Username field and enter username
        sendTextToElement(By.id("password"), "secret_sauce");//Locate Password field and enter password
        clickOnElement(By.id("login-button"));//click on login button
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));//storing products in a list
        ArrayList<String> product = new ArrayList<>();
        for (WebElement e : products) {
            product.add(e.getText());
        }
        Assert.assertEquals("Products not matching", 6, product.size());//verifying six prodcuts displayed on page

    }
    @After
    public void tearDown(){//close method
        close();
    }

}
