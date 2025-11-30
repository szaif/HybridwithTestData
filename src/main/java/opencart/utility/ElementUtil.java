package opencart.utility;


import opencart.frameWorkexception.FrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementUtil {
    private WebDriver driver;


    public ElementUtil(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public boolean checkElementisDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }

    public  WebElement waitForElementVisible(By locator,int timeOut)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void doSendKeys(By locator, String value) {
        if (value == null) {
            System.out.println("null vlaues are not allowed");
            throw new FrameworkException("VALUECANNOTBENULL");
        }

        WebElement ele = getElement(locator);
        ele.clear();
        ele.sendKeys(value);
    }

    public  void doClick(By locator)
    {
        getElement(locator).click();
    }

    public List<WebElement> waitForElementsVisibility (By locator, int timeOut)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public  String titleAndCapture(String titlefragment, int timeOut)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.titleContains(titlefragment)))
        {
            String title = driver.getTitle();
            System.out.println(title);
            return title;
        } else {
            System.out.println("Title Not Found in given timeout");
            return null;
        }
    }

  public String doGetElementText(By locator)
  {
      return getElement(locator).getText();
  }

  public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public String waitForURLContainsAndCapture(String urlFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
            String url = driver.getCurrentUrl();
            return url;
        }
        else {
            System.out.println("url is not present within the given timeout : " + timeOut);
            return null;
        }
    }



}


