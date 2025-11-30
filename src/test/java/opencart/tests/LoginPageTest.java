package opencart.tests;


import opencart.base.BaseTest;

import opencart.utility.AppConstants;
import org.testng.Assert;

import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest
{
    @Test
     public void loginPageTitleTest()
      {
      String actTitle= loginPage.getLoginPageTitle();
      Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
      }
      @Test
    public void ForgotPwdLinkExistTest()
    {
       Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }
    @Test
    public void loginPageURLTest()
    {
        String actURL = loginPage.getLoginPageURL();
        Assert.assertEquals(actURL,"https://naveenautomationlabs.com/opencart/index.php?route=account/login");
    }

    @Test
    public void loginTest()
    {
        accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertTrue(accPage.isLogoutLinkExist());

    }


}














