
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddToBasketTest extends BaseTest {

    @Test
    public void addProductToBasketAndAfterDeleteItTest() {
        WebElement laptopCategory = BaseTest.webDriver.findElement(By.xpath(".//sidebar-fat-menu/div/ul/li[1]"));
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
}
