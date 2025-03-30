package TestCases;

import PageObject.HomePage;
import PageObject.LoginPage;
import TestBase.BaseClass;
import TestBase.ExcelReportListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExcelReportListener.class)
public class TC_Home_Page extends BaseClass {

    @Test(priority = 1)
    public void check_totaldivandmsg()
    {
        LoginPage lp=new LoginPage(dr);
        lp.login();
        HomePage hp=new HomePage(dr);
        if(hp.total_msg_btn()==hp.total_Req_Div())
        {
            Assert.assertTrue(true);
        }else {
            Assert.fail();
        }
    }
}
