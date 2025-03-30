package TestCases;

import PageObject.HomePage;
import PageObject.JobPage;
import PageObject.LoginPage;
import TestBase.BaseClass;
import TestBase.ExcelReportListener;
import Utilities.Job_Post_DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExcelReportListener.class)
public class TC_Job_Page extends BaseClass {

    @Test(dataProvider = "job_post",dataProviderClass = Job_Post_DataProvider.class)
    public void verify_post_Job(String title,String disc,String type,String path)
    {
        LoginPage lp=new LoginPage(dr);
        lp.login();
        HomePage hp=new HomePage(dr);
        hp.clickAd();
        JobPage jp=new JobPage(dr);
        jp.uploadJob();

        jp.filltitle(title);
        jp.filldesc(disc);
        jp.setSelectType(type);
        jp.setUploadImage(path);

        jp.upload_post();
        jp.close();
    }


}
