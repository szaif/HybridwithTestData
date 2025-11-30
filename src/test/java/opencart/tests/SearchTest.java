package opencart.tests;

import opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest
{
    @BeforeClass
public void searchSetup()
{
    accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
}

   @DataProvider
   public Object[][] getProductSearchKeyData()
   {
       return new Object[][]
       {
               {"Macbook"},
               {"iMac"},
               {"Samsung"}
       };
   }

   @Test(priority=1,dataProvider = "getProductSearchKeyData")
    public void searchProductTest(String searchKey)
   {
       resultsPage= accPage.doSearch(searchKey);
       Assert.assertTrue(resultsPage.getProductResultsCount()>0);
   }

   @Test(priority=2,dataProvider="getProductSearchKeyData")
    public void searchPageTitleTest(String searchKey)
   {
       resultsPage= accPage.doSearch(searchKey);
       String actSearchTitle= resultsPage.getResultsPageTitle(searchKey);
       System.out.println("Search Page Title is " +actSearchTitle);
       Assert.assertEquals(actSearchTitle,"Search - "+searchKey);
   }

   @DataProvider
   public Object[][] getProductTestData()
   {
       return new Object[][]
               {
                       {"Macbook","MacBook Pro"},
                       {"iMac","iMac"},
                       {"Samsung","Samsung SyncMaster 941BW"},
                       {"Samsung","Samsung Galaxy Tab 10.1"}

               };
   }

   @Test(priority=3,dataProvider= "getProductTestData")
    public void selectProductTest(String searchKey,String productName)
   {
       resultsPage= accPage.doSearch(searchKey);
       productInfoPage = resultsPage.selectProduct(productName);
       String actProductHeaderName=  productInfoPage.getProductHeaderName();
       System.out.println("Actual Product name is " +actProductHeaderName);
       Assert.assertEquals(actProductHeaderName,productName);
   }

    @DataProvider
    public Object[][] getProductImagesTestData()
    {
        return new Object[][]
                {
                        {"Macbook","MacBook Pro",4},
                        {"iMac","iMac",3},
                        {"Samsung","Samsung SyncMaster 941BW",1},
                        {"Samsung","Samsung Galaxy Tab 10.1",7}

                };
    }

   @Test(priority=4,dataProvider="getProductImagesTestData")
    public void productImagesTest(String searchKey,String productName,int imagesCount)
   {
       resultsPage= accPage.doSearch(searchKey);
       productInfoPage = resultsPage.selectProduct(productName);
       int actProductImagesCount= productInfoPage.getProductImagesCount();
       System.out.println("Actual Product images count is " +actProductImagesCount);
       Assert.assertEquals(actProductImagesCount,imagesCount);
   }

}
