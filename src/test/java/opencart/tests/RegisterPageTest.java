package opencart.tests;

import opencart.base.BaseTest;
import opencart.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

public class RegisterPageTest extends BaseTest {

   @BeforeClass
    public void regSetUp()
{
    registerPage= loginPage.navigateToRegisterPage();
}

  public String  getRandomEmailid()
  {
      //return System.currentTimeMillis()+"@gmail.com";
      return  "testautomation" + UUID.randomUUID() +"@gmail.com";
  }

//  @DataProvider(name="regData")
//  public Object[][] getUserRegTestData() {
//      return new Object[][]
//      {
//              {"shuchi","gupta","9888871121","Test@123","Yes"},
//              {"Rimi","sharma","9999976621","Test@1234","No"},
//              {"Jyoti","saxena","9000071121","Test@123","Yes"}
//
//      };
//
//  }

    @DataProvider(name= "regExcelData")
    public Object[][] getRegExcelTestData()
    {
        Object[][] regData= ExcelUtil.getTestData("Sheet1");
        return regData;

    }

@Test(dataProvider = "regExcelData")
    public void userRegisterTest(String firstName,String lastName,String telephone,String password,String subscribe)
  {
      String actRegSuccMessage = registerPage.registerUser(firstName,lastName,getRandomEmailid(),telephone,password,subscribe);
      Assert.assertEquals(actRegSuccMessage,"Your Account Has Been Created!");
  }




}
