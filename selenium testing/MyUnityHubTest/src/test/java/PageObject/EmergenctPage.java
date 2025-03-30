package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmergenctPage extends BasePage {


    public EmergenctPage(WebDriver dr) {
        super(dr);
    }

    @FindBy(xpath = "//h1[normalize-space()='Emergency Services']")
    WebElement headlink;

    public boolean isheadvisible()
    {
       return headlink.isDisplayed();
    }
}
