package opencart.factory;

import opencart.frameWorkexception.FrameworkException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static java.sql.DriverManager.getDriver;

public class DriverFactory {

    WebDriver driver;

    public WebDriver initDriver(Properties prop) {
        String browserName = prop.getProperty("browser");
        System.out.println("Browser name is : " + browserName);

        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                System.out.println("Please enter correct BrowserName: " + browserName);
                throw new FrameworkException("NOBROWSERFOUNDEXCEPTION");
        }

        if (driver != null) {
            driver.manage().window().maximize();
            driver.get(prop.getProperty("url"));
        }

        return driver;
    }

    public Properties initProp() throws FileNotFoundException {

        Properties prop = new Properties();
        FileInputStream ip = null;
        //mvn clean install -Denv = "qa"---this calls to run this command on cmd for qa env
        //mvn clean install ---this means null environment is passed
        String envName = System.getProperty("env");//this env value is passed from command line
        System.out.println("Running test cases on environment: " + envName);

        try
        {
        if (envName == null) {
            System.out.println("No env is given...hence running on QA environment");
            ip = new FileInputStream("./src/main/resources/Config/qa.config.properties");

        } else {
            switch (envName.toLowerCase().trim()) {
                case "qa":
                    ip = new FileInputStream("./src/main/resources/Config/qa.config.properties");
                    break;
                case "dev":
                    ip = new FileInputStream("./src/main/resources/Config/dev.config.properties");
                    break;
                case "stage":
                    ip = new FileInputStream("./src/main/resources/Config/stage.config.properties");
                    break;
                case "uat":
                    ip = new FileInputStream("./src/main/resources/Config/uat.config.properties");
                    break;

                default:
                    System.out.println("Please enter correct env: " + envName);
                    throw new FrameworkException("NoEnvironmentException");
            }
        }

            }

                catch(FileNotFoundException e)
                {
                    e.printStackTrace();
        }

        try {
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;

    }

//    public static String getScreenshot()
//    {
//        File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        String path = System.getProperty("user.dir")+"/screenshot/" + System.currentTimeMillis() + ".png";
//        File destination = new File(path);
//        try
//        {
//            FileUtils.copyFile(srcFile,destination));
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        return path;
    }
}
