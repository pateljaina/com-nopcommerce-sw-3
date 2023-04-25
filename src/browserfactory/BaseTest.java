package browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;

    public void openBrowser(String baseURL) {
        driver = new ChromeDriver();//declaring the driver
        driver.get(baseURL);//getting the baseUrl
        driver.manage().window().maximize();//maximising the window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));//giving implicit time
    }

    public void close() {//closing the browser
        driver.close();//using close method
    }
}

