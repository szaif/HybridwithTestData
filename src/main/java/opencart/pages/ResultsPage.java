package opencart.pages;

import opencart.utility.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultsPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    public ResultsPage(WebDriver driver)
    {
        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }

    private By resultsProduct = By.xpath("/html/body/div[2]/div/div/div[3]/div");


    public String getResultsPageTitle(String searchKey)
    {
       return elementUtil.titleAndCapture(searchKey,10);
    }

   public int getProductResultsCount()
   {
       int resultCount= elementUtil.waitForElementsVisibility(resultsProduct,50).size();
       System.out.println("Search result products count is : " +resultCount);
       return resultCount;
   }
     public ProductInfoPage selectProduct(String productName)
     {
         By productNameLocator= By.linkText(productName);
         elementUtil.doClick(productNameLocator);
         return new ProductInfoPage(driver);
     }
}
