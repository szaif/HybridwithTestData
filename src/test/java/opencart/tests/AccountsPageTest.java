package opencart.tests;
import opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class AccountsPageTest extends BaseTest
        //this is specially designed in accountPageTest, as AccountPageTest will run only we we log in.
        //Also it will run before we run @Test in ccountPageTest class
{
    @BeforeClass
    public void accPageSetup()
    {
        accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));

    }
    @Test
    public void accountsPageTitleTest()
    {
        String actTitle= accPage.getAccountPageTitle();
        Assert.assertEquals(actTitle, "My Account");
    }
    @Test
    public void isLogoutLinkExistTest()
    {
        Assert.assertTrue( accPage.isLogoutLinkExist());
    }
    public void isMyAccountLinkExistTest()
    {
        Assert.assertTrue( accPage.isMyAccountLinkExist());
    }

    @Test
    public void accPageHeadersCountTest()
    {
        List<String> actAccHeadersList = accPage.getAccountsPageHeaderList();
        Assert.assertEquals(actAccHeadersList.size(), 4);
    }

    @Test
    public void accPageHeadersTest() {
        List<String> actAccHeadersList = accPage.getAccountsPageHeaderList();
        List<String> expAccHeadersList =  Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
        Assert.assertEquals(expAccHeadersList, actAccHeadersList);
    }


}
