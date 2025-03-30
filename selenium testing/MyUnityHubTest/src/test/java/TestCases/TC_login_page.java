package TestCases;

import PageObject.LoginPage;
import PageObject.dashboard;
import TestBase.BaseClass;
import TestBase.ExcelReportListener;
import Utilities.Login_data_provider;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExcelReportListener.class)
public class TC_login_page extends BaseClass {




    @Test(priority = 1)
    public void verify_link()
    {
        try {
            LoginPage lg = new LoginPage(dr);
            int count= lg.totallink();
            Assert.assertEquals(count,5);

        }catch (Exception e)
        {
            Assert.fail();
        }
    }
    @Test(priority = 5, dataProviderClass = Login_data_provider.class, dataProvider = "dp")
    public void check_login(String email, String pass) {
        try {
            LoginPage lg = new LoginPage(dr);
            dashboard ds = new dashboard(dr);
            ds.clicklogin();

            if (!ds.isloginvisible()) {
                Assert.fail("Login page not displayed");
                return;
            }

            lg.fillemail(email);
            lg.fillpass(pass);
            lg.clicklogin();

            // Wait for page load (if necessary)
            Thread.sleep(2000);  // Better: Use WebDriverWait

            // Verify if redirected to correct page
            String expectedURL = "http://unityhub.great-site.net/dashboard.php";
            String actualURL = dr.getCurrentUrl();  // Get the current URL from WebDriver

            if (actualURL.equals(expectedURL)) {
                Assert.assertTrue(true, "Login successful!");
                lg.clickclose();
                lg.clicklogout();
            } else {
                if(dr.getCurrentUrl().equals("http://unityhub.great-site.net/login.php"))
                {
                    dr.navigate().back();
                    dr.navigate().back();
                    Assert.assertTrue(true);
                }

            }
        } catch (Exception e) {
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void logolinkcheck()
    {
        LoginPage lg = new LoginPage(dr);
        dashboard ds = new dashboard(dr);
        ds.clicklogin();
        lg.clicklogolink();
        Assert.assertEquals(ds.isdashvisible(),true);
    }

    @Test(priority = 4)
    public void CheckRegistrationLinkonLoginPage()
    {
        LoginPage lg = new LoginPage(dr);
        dashboard ds = new dashboard(dr);
        ds.clicklogin();
        lg.DonthaveLink();
        Assert.assertEquals(ds.isregvisible(),true);
    }


}
