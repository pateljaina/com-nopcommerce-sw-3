package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before//action before every method
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        clickOnElement(By.linkText("Computers"));//1.1 click on computers
        clickOnElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']")); //1.2 click on desktop
        List<WebElement> beforeFilter = driver.findElements(By.className("product-title"));//storing list of products
        ArrayList<String> beforeFilterProducts = new ArrayList<>();
        for (WebElement e : beforeFilter) {
            beforeFilterProducts.add(e.getText());
        }
        Collections.sort(beforeFilterProducts);//List will sort in alphabetical order
        clickOnElement(By.xpath("//option[contains(text(),'Name: Z to A')]"));//1.3 click on descending order
        List<WebElement> afterFilter = driver.findElements(By.className("product-title"));//storing after filtering list of products
        ArrayList<String> afterFilterProducts = new ArrayList<>();//
        for (WebElement e : afterFilter) {
            afterFilterProducts.add(e.getText());
        }
        Assert.assertEquals("Products not in Selected Filter format", afterFilterProducts, beforeFilterProducts);//1.4 Verify to see if sorted z to a
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        clickOnElement(By.linkText("Computers"));//2.1 click on computers
        clickOnElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']"));//2.2 click on desktop
        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: A to Z");//2.3 Sort by position order
        Thread.sleep(2000);//allowing time to finish the process
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));//2.4 click on "Add to cart"
        String expectedMessage = "Build your own computer";//2.5 Verify the text build your computer
        String actualMessage = getTextElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        Assert.assertEquals("Build your own computer page not displayed", expectedMessage, actualMessage);
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");//2.6 select class to select text
        selectByVisibleTextFromDropDown((By.cssSelector("#product_attribute_2")), "8GB [+$60.00]");//2.7 select class to select text
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));//2.8 clicking on radio button
        clickOnElement(By.cssSelector("#product_attribute_4_9"));//2.9 clicking on radio button
        Thread.sleep(2000);//allowing time to finish the process
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']")); //2.10 clicking on check box
        Thread.sleep(2000);//allowing time to finish the process
        String expectedMessage1 = "$1,475.00";//price expected $1475
        String actualMessage1 = getTextElement(By.id("price-value-1"));
        Assert.assertEquals("Price is incorrect", expectedMessage1, actualMessage1);
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));//2.12 product added to shopping cart
        String expectedMessage2 = "The product has been added to your shopping cart";//2.13 verifying product has been added to cart
        String actualMessage2 = getTextElement(By.xpath("//p[contains(text(),'The product has been added to your ')]"));
        Assert.assertEquals("Product has not been added to cart", expectedMessage2, actualMessage2);
        clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));//closing the bar by clicking the cross button
        mouseHoverToElementAndCLick(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        String expectedMessage3 = "Shopping cart";
        String actualMessage3 = getTextElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals("Shopping cart not opened", expectedMessage3, actualMessage3);
        WebElement element = driver.findElement(By.xpath("//input[@class='qty-input']"));
        element.click();
        element.sendKeys(Keys.CONTROL + "a"); // Select all existing text
        element.sendKeys("2"); //2.16 Type in the new text
        clickOnElement(By.xpath("//button[@id='updatecart']"));//2.16
        String expectedMessage4 = "$2,950.00";//2.17
        String actualMessage4 = getTextElement(By.xpath("//tbody/tr[1]/td[6]/span[1]"));
        clickOnElement(By.id("termsofservice"));//2.18 click on checkbox 'I agree with terms of service'.
        clickOnElement(By.id("checkout"));//2.19 click on checkout
        String expectedMessage5 = "Welcome, Please Sign In!";//2.20 verifying text welcome
        String actualMessage5 = getTextElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        Assert.assertEquals("Not directed to requested page", expectedMessage5, actualMessage5);
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));//2.21 click on chekout as guest
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Jaina");//2.22 fill mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Patel");//2.22 fill mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "pjaina@gmail.com");//2.22 fill mandatory fields
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");//2.22 fill mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");//2.22 fill mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "9 Littleheath Road");//2.22 fill mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "CR2 7PH");//2.22 fill mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07984984543");//2.22 fill mandatory fields
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));//2.23 click on continue
        clickOnElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/div[1]/input[1]"));//2.24 click on radio button
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]"));//2.25 click on continue
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));
        selectByVisibleTextFromDropDown(By.cssSelector("#CreditCardType"), "Master card");//select "Credit card"
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Mrs Jaina C Patel");//2.28 fill al details
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "0759 2136 2156 5987");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "23");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2024");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "999");
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));//2.29 click on continue
        String expectedMessage6 = "Credit Card";//2.30 verify payment method credit card
        String actualMessage6 = getTextElement(By.xpath("//span[contains(text(),'Credit Card')]"));
        Assert.assertEquals("Payment method incorrect", expectedMessage6, actualMessage6);
        String expectedMessage7 = "Next Day Air";//2.31 verify shipping method is "Next Day Air"
        String actualMessage7 = getTextElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[6]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/ul[1]/li[1]/span[2]"));
        Assert.assertEquals("Incorrect shipping method", expectedMessage7, actualMessage7);
        String expectedMessage8 = "$2,950.00";//2.33 verify total $2,950
        String actualMessage8 = getTextElement(By.xpath("//body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[6]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/td[2]/span[1]/strong[1]"));
        Assert.assertEquals("Total, incorrect", expectedMessage8, expectedMessage8);
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));//2.34 click on 'confirm'
        String expectedMessage9 = "Thank you";//2.35 verify the text 'thank you'
        String actualMessage9 = getTextElement(By.xpath("//h1[contains(text(),'Thank you')]"));
        Assert.assertEquals("Not directed to correct page", expectedMessage9, actualMessage9);
        String expectedMessage10 = "Your order has been successfully processed!";//2.36 verify "Your order has been successfully processed"
        String actualMessage10 = getTextElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
        Assert.assertEquals("Problem with order", expectedMessage10, actualMessage10);
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));//2.37 click on continue
        String expectedMessage11 = "Welcome to our store";//2.37 verify the text Welcome to our store
        String actualMessage11 = getTextElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        Assert.assertEquals("Not redirected to homepage", expectedMessage11, actualMessage11);
    }

    @After
    public void tearDown() {

        close();
    }
}