package TestCases;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.ProfilePage;
import PageObject.dashboard;
import TestBase.BaseClass;
import TestBase.ExcelReportListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExcelReportListener.class)
public class P_Profile_page extends BaseClass {

    @Test(priority = 2)
    public void verify_update_profile()
    {
        dashboard ds = new dashboard(dr);
        ds.clicklogin();
        LoginPage lg = new LoginPage(dr);
        lg.fillemail("s@gmail.com");
        lg.fillpass("s");
        lg.clicklogin();
        lg.clickclose();
        HomePage hm = new HomePage(dr);
        hm.clickProfile();
        ProfilePage pg=new ProfilePage(dr);
        pg.setSelectFile("D:\\my photos\\WhatsApp Image 2024-10-19 at 19.13.58_0fdc193b.jpg");
        pg.clickupload();
        pg.logout();
    }

    @Test(priority = 1)
    public void verify_username()
    {
        dashboard ds = new dashboard(dr);
        ds.clicklogin();
        LoginPage lg = new LoginPage(dr);
        lg.fillemail("s@gmail.com");
        lg.fillpass("s");
        lg.clicklogin();
        lg.clickclose();
        HomePage hm = new HomePage(dr);
        hm.clickProfile();
        ProfilePage pg=new ProfilePage(dr);
        if(pg.getEmail().equalsIgnoreCase("s@gmail.com"))
        {
            pg.logout();
            Assert.assertTrue(true);
        }else {
            Assert.fail();
        }
    }

    @Test(priority = 3)
    public void check_dashboard_btn()
    {
        LoginPage lp=new LoginPage(dr);
        lp.login();
        HomePage hp=new HomePage(dr);
        hp.clickProfile();
        ProfilePage pp=new ProfilePage(dr);
        pp.click_Go_Dash();
       LoginPage Lp=new LoginPage(dr);
        if(Lp.isdashboarddisplay())
        {
            Assert.assertTrue(true);
        }else {
            Assert.fail("dashboard not display");
        }
    }
}
