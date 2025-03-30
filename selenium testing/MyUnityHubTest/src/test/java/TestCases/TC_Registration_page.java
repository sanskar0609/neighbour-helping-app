package TestCases;

import PageObject.LoginPage;
import PageObject.RegistrationPage;
import PageObject.dashboard;
import TestBase.BaseClass;
import TestBase.ExcelReportListener;
import Utilities.Registration_data_provider;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExcelReportListener.class)
public class TC_Registration_page extends BaseClass {

    @Test(dataProviderClass = Registration_data_provider.class, dataProvider = "RegistrationData")
    public void Verify_Registration(String name, String email, String pass) {
        try {
            dashboard ds = new dashboard(dr);

            ds.clickregister();  // Navigate to the registration page

            // Enter data
            RegistrationPage rg = new RegistrationPage(dr);
            rg.fillname(name);
            rg.fillemail(email);
            rg.fillpass(pass);
            rg.clickreg();  // Submit registration

            String currentURL = dr.getCurrentUrl();
            System.out.println(currentURL);


            if (!rg.invalid()) {
                // Registration successful; navigate to login
                rg.click_log_in_here();

                ds = new dashboard(dr);
                if (ds.isloginvisible()) {
                    Assert.assertTrue(true, "Registration successful; login is visible.");
                } else {
                    dr.get("http://unityhub.great-site.net/");
                    Assert.fail("Registration might have failed; 'Log in' button not visible.");
                }
            } else if (currentURL.equalsIgnoreCase("http://unityhub.great-site.net/register.php")) {
                // Registration failed (remains on the same page)
                dr.navigate().back();
                Assert.fail("Registration failed; still on registration page.");
            } else {
                dr.navigate().back();
                // If redirected to an unexpected page, fail the test
                Assert.fail("Unexpected behavior after registration.");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}