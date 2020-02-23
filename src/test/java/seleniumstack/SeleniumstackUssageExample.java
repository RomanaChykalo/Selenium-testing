package seleniumstack;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumstackUssageExample {
    private WebDriver webDriver;
    public static final String USERNAME = "romanachykalo1";
    public static final String AUTOMATE_KEY = "t6iu4FiPyKbizbKkhd6q";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("os", "Windows");
        caps.setCapability("browser_version", "80.0");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.selenium_version", "3.5.2");

        webDriver = new RemoteWebDriver(new URL(URL), caps);
   /*    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tom\\Downloads\\chromedriver_win32(4)\\chromedriver.exe");
       webDriver=new ChromeDriver();*/
        webDriver.get("https://rozetka.com.ua/");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @Test
    public void test() throws MalformedURLException {
        WebElement laptopCategory = webDriver.findElement(By.xpath(".//div/aside/main-page-sidebar/sidebar-fat-menu/div/ul/li[1]"));
        Actions action = new Actions(webDriver);
        action.moveToElement(laptopCategory).perform();
        WebElement lenovoCategory = webDriver.findElement(By.linkText("Lenovo"));
        lenovoCategory.click();
        List<WebElement> laptopsOnPageImages = webDriver.findElements(By.cssSelector("img.ng-lazyloaded"));
        laptopsOnPageImages.get(0).click();
        webDriver.navigate().refresh();

        String[] splitedUrl = webDriver.getCurrentUrl().split("/");
        String laptopModelName = splitedUrl[splitedUrl.length - 1];
        System.out.println(laptopModelName);

        WebElement buyButton = webDriver.findElement(By.cssSelector("button.buy-button"));
        buyButton.click();

        WebElement continueShoppingButton = webDriver.findElement(By.cssSelector("a.cart-modal__bottom-continue"));
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].scrollIntoView();", continueShoppingButton);
        continueShoppingButton.click();

        WebElement basket = webDriver.findElement(By.cssSelector("a.header-actions__button_type_basket"));
        basket.click();
        System.out.println(webDriver.getCurrentUrl());
        Assert.assertTrue(webDriver.getCurrentUrl().contains(laptopModelName));
    }

    @AfterClass
    public void tearDown(){
        webDriver.quit();
    }
}
