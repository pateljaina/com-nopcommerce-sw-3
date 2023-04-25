package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before//action before every method
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        mouseHoverToElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[2]/a"));
        mouseHoverToElementAndCLick(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));
        String expectedMessage = "Cell phones";
        String actualMessage = getTextElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        Assert.assertEquals("not directed to Cell phone page", expectedMessage, actualMessage);
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        mouseHoverToElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[2]/a"));//2.1 hover on electronics tab
        mouseHoverToElementAndCLick(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));//2.2 hovr on cell phones
        Thread.sleep(2000);
        String expectedMessage = "Cell phones";//2.3 verify text cell phones
        String actualMessage = getTextElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        Assert.assertEquals("not directed to Cell phone page", expectedMessage, actualMessage);
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));//2.4 click on list view
        Thread.sleep(2000);
        clickOnElement(By.linkText("Nokia Lumia 1020"));//2.6 click on element Nokia Lumia 1020
        String expectedMessage1 = "Nokia Lumia 1020";//2.7 verify text nokia lumia
        String actualMessage1 = getTextElement(By.xpath("//h1[normalize-space()='Nokia Lumia 1020']"));
        Assert.assertEquals("Not directed to Nokia Lumia 1020", expectedMessage1, actualMessage1);
        String expectedMessage2 = "$349.00";//2.7 verify price $349.00
        String actualMessage2 = getTextElement(By.cssSelector("#price-value-20"));
        Assert.assertEquals("Price does not match", expectedMessage2, actualMessage2);
        WebElement element = driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']"));
        element.click();
        element.sendKeys(Keys.CONTROL + "a"); // Select all existing text
        element.sendKeys("2"); //2.8 change quantity to 2
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));//click on add to cart button
        String expectedMessage3 = "The product has been added to your shopping cart";//2.10 verify added to cart
        String actualMessage3 = getTextElement(By.xpath("//div[@id='bar-notification']/div/p"));
        Assert.assertEquals("Product not added to cart", expectedMessage3, actualMessage3);
        clickOnElement(By.xpath("//span[@title='Close']"));//2.10 close by clicking cross on green message bar
        mouseHoverToElement(By.xpath("//span[@class='cart-label']"));//2.11 mouse hover shopping cart
        Thread.sleep(2000);
        mouseHoverToElementAndCLick(By.xpath("//button[normalize-space()='Go to cart']"));//click on go to cart
        String expectedMessage4 = "Shopping cart";//2.12 verify shopping cart
        String actualMessage4 = getTextElement(By.xpath("//div[@class='page-title']"));
        Assert.assertEquals("Product not added to cart", expectedMessage4, actualMessage4);
        String expectedMessage5 = "2";//2.13 verify quantity
        WebElement x = driver.findElement(By.xpath("//input[@class=\"qty-input\"]"));
        String qty = x.getAttribute("value");
        Assert.assertEquals("Quantity does not match", expectedMessage5, qty);
        String expectedMessage6 = "$698.00";//2.14 verify total price
        String actualMessage6 = getTextElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"));
        Assert.assertEquals("Price incorrect", expectedMessage6, actualMessage6);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));//2.15 click on agree terms of service
        clickOnElement(By.xpath("//button[@id='checkout']"));//2.16 click on checkout
        String expectedMessage7 = "Welcome, Please Sign In!";//2.17 verify welcome please sign in
        String actualMessage7 = getTextElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));// 2.17 verify text welcome
        Assert.assertEquals("Not directed to requested page", expectedMessage7, actualMessage7);
        clickOnElement(By.xpath("//button[normalize-space()='Register']"));//2.18 click on register tab
        String expectedMessage8 = "Register";//2.19 verify text register
        String actualMessage8 = getTextElement(By.xpath("//div[@class='page-title']"));
        Assert.assertEquals("Not directed to register page", expectedMessage8, actualMessage8);
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "Jaina");//2.20 fill all mandatory fields
        sendTextToElement(By.xpath("//input[@id='LastName']"), "Patel");//2.20 fill all mandatory fields
        selectByVisibleTextFromDropDown(By.xpath("//select[@name='DateOfBirthDay']"), "7");//2.20 fill all mandatory fields
        selectByVisibleTextFromDropDown(By.xpath("//select[@name='DateOfBirthMonth']"), "February");//2.20 fill all mandatory fields
        selectByVisibleTextFromDropDown(By.xpath("//select[@name='DateOfBirthYear']"), "1988");//2.20 fill all mandatory fields
        sendTextToElement(By.id("Email"), "patelj8@gmail.com");//2.20 fill all mandatory fields
        sendTextToElement(By.id("Password"), "Prime12345");//2.20 fill all mandatory fields
        sendTextToElement(By.id("ConfirmPassword"), "Prime12345");//2.20 fill all mandatory fields
        clickOnElement(By.id("register-button"));//2.21 click on register
        String expectedMessage9 = "Your registration completed";//2.22 veriy message your registration is completed
        String actualMessage9 = getTextElement(By.xpath("//div[@class='result']"));
        Assert.assertEquals("Error in registration", expectedMessage9, actualMessage9);
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));//2.23 click continue
        //now login with the registered email and password to make sure basket is not emptied
        clickOnElement(By.xpath("//a[normalize-space()='Log in']"));//follow login process
        sendTextToElement(By.xpath("//input[@id='Email']"), "patelj8@gmail.com");
        sendTextToElement(By.xpath("//input[@id='Password']"), "Prime12345");
        clickOnElement(By.xpath("//button[normalize-space()='Log in']"));
        String expectedMessage10 = "Shopping cart";//2.24 verify text shopping cart
        String actualMessage10 = getTextElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        Assert.assertEquals("Not directed to cart page", expectedMessage10, actualMessage10);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));//2.25 click on agree terms of service
        clickOnElement(By.xpath("//button[@id='checkout']"));// 2.26 click on chek out
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");//2.27 fill mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");//2.27 fill mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "9 Littleheath Road");//2.27 fill mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "CR2 7PH");//2.27 fill mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07984984543");//2.27 fill mandatory fields
        clickOnElement(By.xpath("//button[text() = 'Continue']"));//2.28 click on conitnue
        clickOnElement(By.id("shippingoption_2"));//2.29 click on radio button 2nd day air
        Thread.sleep(2000);
        clickOnElement(By.xpath("//div[@id='shipping-method-buttons-container']/button"));//2.30 click continue
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']")); //2.31 credit card
        clickOnElement(By.xpath("//div[@id='checkout-step-payment-method']/div/button"));//continue
        selectByVisibleTextFromDropDown(By.id("CreditCardType"), "Visa");//2.32 select visa from drop down
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Mrs Jaina C Patel");//2.33 fill all details
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "4111 1111 1111 1111");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "23");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2024");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "999");
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));//2.34 click continue
        String expectedMessage15 = "Credit card";//2.35 verify payment method is credit card
        String actualMessage15 = getTextElement(By.xpath("//span[normalize-space()='Credit Card']"));
        String expectedMessage16 = "$698.00";//2.37 verify total is $698.00
        String actualMessage16 = getTextElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"));
        Assert.assertEquals("Price does not match expected price", expectedMessage16, actualMessage16);
        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));//2.38 click confirm
        String expectedMessage11 = "Thank you";//2.39 verify the text 'thank you'
        String actualMessage11 = getTextElement(By.xpath("//h1[contains(text(),'Thank you')]"));
        Assert.assertEquals("Not directed to correct page", expectedMessage11, actualMessage11);
        String expectedMessage12 = "Your order has been successfully processed!";//2.40 verify that order has been placed successfully
        String actualMessage12 = getTextElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
        Assert.assertEquals("Problem with order", expectedMessage12, actualMessage12);
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));//2.41 continue
        String expectedMessage13 = "Welcome to our store";//2.42 verify welcome to our store
        String actualMessage13 = getTextElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        Assert.assertEquals("Not redirected to homepage", expectedMessage13, actualMessage13);
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));// click logout link
        String url = driver.getCurrentUrl();//2.44 get current url
        String expectedMessage14 = "https://demo.nopcommerce.com/";//verify current url
        String actualMessage14 = url;
        Assert.assertEquals("Not on the expected url", expectedMessage14, url);

    }

    @After
    public void teardown() {
        close();
    }


}
