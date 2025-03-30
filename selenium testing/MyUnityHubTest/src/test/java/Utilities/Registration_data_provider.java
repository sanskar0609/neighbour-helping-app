package Utilities;

import org.testng.annotations.DataProvider;

public class Registration_data_provider {

    @DataProvider(name = "RegistrationData")
    Object[][] provider()
    {
        Object[][] data={
                { "abc","abc@gmail.com","test123" },
                {"sanskar","s@gmail.com","s"},
                { "abc","abc@gmail.com","test123" },
                {"s","sgmail.com","s"}

        };
        return data;
    }
}
