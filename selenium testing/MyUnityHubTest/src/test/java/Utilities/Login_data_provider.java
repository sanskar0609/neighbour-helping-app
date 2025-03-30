package Utilities;

import org.testng.annotations.DataProvider;

public class Login_data_provider {

    @DataProvider(name = "dp")
    Object[][] provider()
    {
        Object[][] data={

                {"s@gmail.com","s"},
                { "abc@gmail.com","test123" },
                {"s@gmail.com","s"}

        };
        return data;
    }
}
