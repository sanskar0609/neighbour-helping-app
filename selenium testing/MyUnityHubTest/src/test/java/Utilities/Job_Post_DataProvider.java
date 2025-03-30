package Utilities;

import org.testng.annotations.DataProvider;

public class Job_Post_DataProvider {

    @DataProvider(name = "job_post")
    Object[][] provider()
    {
        Object[][] data={

                {"pepsi","20% off on pepsi","Advertisement","C:\\Users\\sansk\\OneDrive\\Pictures\\Saved Pictures\\download.jpeg"}


        };
        return data;
    }
}
