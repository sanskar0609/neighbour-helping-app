package TestCases;

import PageObject.*;
import TestBase.BaseClass;
import TestBase.ExcelReportListener;
import Utilities.post_request_data_provider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExcelReportListener.class)
public class PR_Post_Request_page extends BaseClass {

    @Test(priority = 1)
    public void checkMap() throws InterruptedException {
        dashboard ds = new dashboard(dr);
        ds.clicklogin();
        LoginPage lg = new LoginPage(dr);
        lg.fillemail("s@gmail.com");
        lg.fillpass("s");
        lg.clicklogin();
        lg.clickclose();
        HomePage hm = new HomePage(dr);
        hm.clickPostRequest();
        mapElement mp=new mapElement(dr);

        mp.clickzoomInbtn();
        mp.clickzoomoutbtn();
        mp.clickSearchbtn();
        Thread.sleep(5000);
        mp.SearchLoc();
        Actions actions = new Actions(dr);
        actions.sendKeys(mp.searchInput, Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(5000);
        Assert.assertTrue(true);
        hm.clickLogout();

    }

    @Test(priority = 2, dataProvider = "postRequest",dataProviderClass = post_request_data_provider.class)
    public void verify_Post_Request_Form(String title,String desc,String cat)
    {
        dashboard ds = new dashboard(dr);
        ds.clicklogin();
        LoginPage lg = new LoginPage(dr);
        lg.fillemail("s@gmail.com");
        lg.fillpass("s");
        lg.clicklogin();
        lg.clickclose();
        HomePage hm = new HomePage(dr);
        hm.clickPostRequest();
        PostRequestPage pr=new PostRequestPage(dr);
        pr.fillReqTitle(title);
        pr.fillDescription(desc);
        pr.selectcategory(cat);
        if(pr.latlanpresent())
        {
            Assert.assertTrue(true);
        }
        else {
            Assert.fail();
        }
    }

}
