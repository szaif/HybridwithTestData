package opencart.tests;


import opencart.base.BaseTest;
import opencart.pojo.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchCommonDataTest  extends BaseTest
{
    @BeforeClass
    public void searchSetup()
    {
        accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @DataProvider(name="productData")
    public Object[][] getProductTestData() {
        return new Object[][]{
                //calling constructor of Product class
                { new Product("Macbook", "Macbook Pro", 4)},
                { new Product("iMac", "iMac", 3)},
                { new Product("Samsung", "Samsung Galaxy Tab 10.1", 7)},
                { new Product("Samsung", "Samsung SyncMaster 941BW ", 1)}

        };
    }

    @Test(priority=1,dataProvider = "productData")
    //instead of passing arguments in method to hold values, we will pass Product class refrence
    public void searchProductResultCountTest(Product product)
    {
        resultsPage= accPage.doSearch(product.getSearchKey());
        Assert.assertTrue(resultsPage.getProductResultsCount()>0);
    }

    @Test(priority=2,dataProvider="productData")
    public void searchPageTitleTest(Product product)
    {
        resultsPage= accPage.doSearch(product.getSearchKey());
        String actSearchTitle= resultsPage.getResultsPageTitle(product.getSearchKey());
        System.out.println("Search Page Title is " +actSearchTitle);
        Assert.assertEquals(actSearchTitle,"Search - "+product.getSearchKey());
    }



    @Test(priority=3,dataProvider= "productData")
    public void selectProductTest(Product product)
    {
        resultsPage= accPage.doSearch(product.getSearchKey());
        productInfoPage = resultsPage.selectProduct(product.getSearchKey());
        String actProductHeaderName=  productInfoPage.getProductHeaderName();
        System.out.println("Actual Product name is " +actProductHeaderName);
        Assert.assertEquals(actProductHeaderName,product.getProductName());
    }



    @Test(priority=4,dataProvider="productData")
    public void productImagesTest(Product product)
    {
        resultsPage= accPage.doSearch(product.getSearchKey());
        productInfoPage = resultsPage.selectProduct(product.getProductName());
        int actProductImagesCount= productInfoPage.getProductImagesCount();
        System.out.println("Actual Product images count is " +actProductImagesCount);
        Assert.assertEquals(actProductImagesCount,product.getProductImages());
    }



}



