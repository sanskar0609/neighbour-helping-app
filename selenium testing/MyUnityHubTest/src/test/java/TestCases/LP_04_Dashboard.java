package TestCases;

import PageObject.EmergenctPage;
import PageObject.dashboard;
import TestBase.BaseClass;
import TestBase.ExcelReportListener;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExcelReportListener.class)
public class LP_04_Dashboard extends BaseClass {

    @Test(priority = 1)
    public void verify_link()
    {
        try {
            dashboard ds=new dashboard(dr);
            System.out.println(ds.countlink());
            Thread.sleep(5000);
            int count=ds.countlink();
            Assert.assertEquals(count,5);

        }catch (Exception e)
        {
            Assert.fail();
        }
    }

    @Test(priority = 2)
    public void checkloginlink()
    {
        try {
            dashboard ds=new dashboard(dr);
            ds.clicklogin();
            boolean count=ds.isloginvisible();
            System.out.println(count);
            Assert.assertEquals(count,true);

        }catch (Exception e)
        {
            Assert.fail();
        }
    }

    @Test(priority = 3)
    public void checkregistration()
    {
        try {
            dashboard ds=new dashboard(dr);
            ds.clickregister();
            boolean count=ds.isregvisible();
            System.out.println(count);
            Assert.assertEquals(count,true);
            dr.navigate().back();

        }catch (Exception e)
        {
            Assert.fail();
        }
    }




}
