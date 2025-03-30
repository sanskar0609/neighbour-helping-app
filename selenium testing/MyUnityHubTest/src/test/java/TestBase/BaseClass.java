package TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public WebDriver dr;
    public Properties p;

    @BeforeClass
    @Parameters({"browser"})
    public void setup(String br) throws IOException {
        FileReader file=new FileReader("./src//test//resources//config.properties");
        p=new Properties();
        p.load(file);

        switch (br.toLowerCase()) {
            case "chrome":
                dr = new ChromeDriver();
                break;
            case "edge":
                dr = new EdgeDriver();
                break;
            case "firefox":
                dr = new FirefoxDriver();
                break;
            default:
                System.out.println("inavlid browser");
                return;
        }
        dr.manage().deleteAllCookies();
        dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        dr.get(p.getProperty("appurl1"));//reading url from properites
        dr.manage().window().maximize();

    }

    @AfterClass
    public void tearDown()
    {
        dr.quit();
    }
}
