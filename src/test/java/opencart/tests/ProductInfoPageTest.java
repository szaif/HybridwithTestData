package opencart.tests;

import opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest {
    @BeforeClass
    public void productInfoPageSetup() {
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void productInfoTest() {
        resultsPage = accPage.doSearch("Macbook");
        productInfoPage = resultsPage.selectProduct("MacBook Pro");
        Map<String,String> productInfoMap = productInfoPage.getProductInfoMap();
        System.out.println(productInfoMap);
       softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
       softAssert.assertEquals(productInfoMap.get("productName"), "MacBook Pro");
       softAssert.assertEquals(productInfoMap.get("productPrice"), "$2,000.00");
       softAssert.assertAll();


    }
}










