
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver webDriver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tom\\Downloads\\chromedriver_win32(4)\\chromedriver.exe");
        DesiredCapabilities desiredCapabilities =DesiredCapabilities.chrome();
        desiredCapabilities.setCapability("os", "Windows");
        desiredCapabilities.setCapability("os_version", "10");
        desiredCapabilities.setCapability("browser", "Chrome");
        desiredCapabilities.setCapability("browser_version", "80.0");
        desiredCapabilities.setPlatform(Platform.WIN10);
        desiredCapabilities.setBrowserName(BrowserType.CHROME);
        desiredCapabilities.setCapability("version","80.0.3987.116");
        ChromeOptions options = new ChromeOptions();
        options.merge(desiredCapabilities);
        webDriver=new RemoteWebDriver(new URL("http://192.168.1.102:4444/wd/hub"),options);
        webDriver.get("https://rozetka.com.ua/");
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(){
        webDriver.quit();
    }

    /*public static void main(String[] args) throws MalformedURLException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Tom\\Downloads\\geckodriver.exe");
        DesiredCapabilities desiredCapabilities =DesiredCapabilities.firefox();
        desiredCapabilities.setCapability("marionette","true");
        desiredCapabilities.setPlatform(Platform.WIN10);
        ChromeOptions options = new ChromeOptions();
      WebDriver  webDriver=new RemoteWebDriver(new URL("http://192.168.1.102:4444/wd/hub"),desiredCapabilities);
        webDriver.get("https://rozetka.com.ua/");
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }*/
}
