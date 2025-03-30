package Utilities;

import org.testng.annotations.DataProvider;

public class post_request_data_provider {

    @DataProvider(name = "postRequest")
    Object[][] provider()
    {
        Object[][] data={

                {"i need water bottle","i need an 20L water bottles i give you Rs20","other"}
//                { "abc@gmail.com","test123" },
//                {"s@gmail.com","s"}




        };
        return data;
    }
}
