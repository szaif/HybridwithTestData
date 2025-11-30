package opencart.pages;

import opencart.utility.AppConstants;
import opencart.utility.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage
{
   private WebDriver driver;
   private ElementUtil elementUtil;

    public AccountsPage(WebDriver driver)
    {
        this.driver= driver;
        elementUtil= new ElementUtil(this.driver);
    }


    private By logout = By.linkText("Logout");
    private By myAccount =By.linkText("My Account");
    private By accHeaders = By.xpath("//div[@id='content']/h2");
    private By search = By.name("search");
    private By searchIcon = By.cssSelector("div#search button");

public String getAccountPageTitle()
{
   return elementUtil.titleAndCapture(AppConstants.ACCOUNTS_PAGE_TITLE_VALUE,10);
}

public boolean isLogoutLinkExist()
{
    return elementUtil.checkElementisDisplayed(logout);
}

public boolean isMyAccountLinkExist()
{
    return elementUtil.checkElementisDisplayed(myAccount);
}

public List<String> getAccountsPageHeaderList()
{
    List<WebElement> headersList = elementUtil.waitForElementsVisibility(accHeaders,10);
    List<String> headersValList= new ArrayList<String>();
    for(WebElement header: headersList)
    {
       String text = header.getText();
        headersValList.add(text);
    }
    return headersValList;
}

public ResultsPage doSearch(String searchTerm)
{
    elementUtil.waitForElementVisible(search,10);
    elementUtil.doSendKeys(search,searchTerm);
    elementUtil.doClick(searchIcon);
    return new ResultsPage(driver);
}


}
