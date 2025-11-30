package opencart.pages;

import opencart.utility.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {

        private WebDriver driver;
        private ElementUtil elementUtil;

    public ProductInfoPage(WebDriver driver)
        {
            this.driver = driver;
            elementUtil = new ElementUtil(this.driver);
        }

        private By productHeader = By.cssSelector("div#content h1");
        private By productImages= By.cssSelector("ul.thumbnails img");
        private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
        private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
        private By quantity = By.id("input-quantity");
        private By addToCartBtn = By.id("button-cart");
        private Map<String,String>productInfoMap;


       public String getProductHeaderName()
       {
           return elementUtil.doGetElementText(productHeader);
       }

       public int getProductImagesCount()
       {
           return elementUtil.waitForElementsVisibility(productImages,20).size();

       }
//    Brand: Apple
//    Product Code: Product 18
//    Reward Points: 800
//    Availability: Out Of Stock

       private void getProductMetaData()
       {
          List<WebElement> metaList =elementUtil.getElements(productMetaData);

          for(WebElement e : metaList)
          {
              String metaText = e.getText();
              String metaInfo[] = metaText.split(":");
              String key =metaInfo[0].trim();
              String value =metaInfo[1].trim();
              productInfoMap.put(key,value);
          }
       }
//    $2,000.00
//    Ex Tax: $2,000.00
       //we declared them Private , as they are not returning anything to share outside class
      private void getProductPriceData()
       {
           List<WebElement> priceList =elementUtil.getElements(productPriceData);
           String priceValue =priceList.get(0).getText();
           String exTaxPrice= priceList.get(1).getText();
           String exTaxPriceValue = exTaxPrice.split(":")[1].trim();

           productInfoMap.put("productPrice",priceValue);
           productInfoMap.put("exTaxPrice",exTaxPriceValue);
       }
    //we declared them Private , as they are not returning anything to share outside class
       public Map<String,String> getProductInfoMap()
       {
//           productInfoMap = new HashMap<String,String>();
           productInfoMap = new LinkedHashMap<String,String>();//in same order it will maintain data
           getProductMetaData();
           getProductPriceData();
           productInfoMap.put("productName",getProductHeaderName());
           return productInfoMap;
       }



    }

