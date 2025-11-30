package opencart.pages;

import opencart.utility.AppConstants;
import opencart.utility.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {
     private WebDriver driver;
     private ElementUtil eleUtil;

  public LoginPage(WebDriver driver)
  {
      this.driver= driver;
      eleUtil= new ElementUtil(this.driver);
  }

  private By emailId = By.id("input-email");
  private By password = By.id("input-password");
  private By loginBtn = By.xpath("//input[@value='Login']");
  private By forgotPwdlink = By.linkText("Forgotten Password");
  private By footerLinks = By.xpath("//footer//a");
  private By loginErrorMessage = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
  private By registerLink = By.linkText("Register");


  public String getLoginPageTitle()
  {
    return eleUtil.titleAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE,5);

  }

  public String getLoginPageURL()
  {

      return eleUtil.waitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE,5);
  }

  public boolean isForgotPwdLinkExist()
  {
      return eleUtil.checkElementisDisplayed(forgotPwdlink);
  }

  public List<String> getFooterElementsList()
  {
      List<WebElement> footerLinksList =eleUtil.waitForElementsVisibility(footerLinks,10);
      List<String>footerTextList =new ArrayList<String>();
      for(WebElement footerLink:footerLinksList)
      {
          System.out.println(footerLink.getText());
          footerTextList.add(footerLink.getText());
      }
      return footerTextList;

  }

  public  AccountsPage doLogin(String userName, String pwd)
  {
      eleUtil.waitForElementVisible(emailId,10).sendKeys(userName);
      eleUtil.doSendKeys(password,pwd);
      eleUtil.doClick(loginBtn);
      return new AccountsPage(driver);
  }
    //Negative Testing scenario
    public  boolean doLoginWithWrongCredentials(String userName, String pwd) {
        eleUtil.waitForElementVisible(emailId, 10);
        eleUtil.doSendKeys(emailId,userName);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);

        String errorMessage = eleUtil.doGetElementText(loginErrorMessage);
        System.out.println(errorMessage);
        if (errorMessage.contains(" Warning: No match for E-Mail Address and/or Password."))
        {
            return true;
        }
        return false;

    }

    public RegisterPage navigateToRegisterPage()
    {
        eleUtil.doClick(registerLink);
        return new RegisterPage(driver);
    }

}
