package opencart.base;

import opencart.factory.DriverFactory;
import opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest
{

        WebDriver driver;
        protected LoginPage loginPage;
        protected AccountsPage accPage;
        protected ResultsPage resultsPage;
        protected ProductInfoPage productInfoPage;
        protected RegisterPage registerPage;

        protected DriverFactory df;
        protected Properties prop;

        protected SoftAssert softAssert;

        @Parameters({"browser"})
        @BeforeTest
        public void setUp(String browserName) throws Exception
        {
            df = new DriverFactory();
            prop = df.initProp();

            //It will check if we are not sending aby browser value from xml file ie browser =null, then it will takr browser value from prop file
            //if browserName!=null, then it will pick browser value from XML Parameter value
            if(browserName!=null) {
                prop.setProperty("browser", browserName);
            }
            driver= df.initDriver(prop);
            loginPage = new LoginPage(driver);
            softAssert= new SoftAssert();
        }

        @AfterTest
         public void tearDown()
    {
        driver.quit();
    }
}



