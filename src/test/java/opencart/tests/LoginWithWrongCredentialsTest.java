package opencart.tests;

import opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginWithWrongCredentialsTest extends BaseTest {

    @DataProvider
    public Object[][] incorrectLoginTestData()
    {
        return new Object[][]
                {
                        {"newemail@gmail.com","test@123"},
                        {"demotrial@gmail.com","Test@123"},
                        {"freshemail@gmail.com","Test@123"}

                };
    }

    @Test(dataProvider="incorrectLoginTestData")
    public void loginWithWrongCredentialsTest(String userName,String pwd)  {
        loginPage.doLoginWithWrongCredentials(userName,pwd);
        Assert.assertFalse(loginPage.doLoginWithWrongCredentials(userName,pwd));
    }







}
