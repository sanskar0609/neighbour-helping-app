package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{
    public HomePage(WebDriver dr) {
        super(dr);
    }

    @FindBy(xpath = "//h1[normalize-space()='UnityHub']")
    WebElement heading;

    @FindBy(xpath = "//a[normalize-space()='Post Request']")
    WebElement btnPostRequest;

    @FindBy(xpath = "//a[normalize-space()='Offer Help']")
    WebElement btnOfferHelp;

    @FindBy(xpath = "//a[normalize-space()='profile']")
    WebElement btnProfile;

    @FindBy(xpath = "//a[normalize-space()='Advertisment']")
    WebElement btnAd;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement btnLogOut;

    @FindBy(xpath = "//body/main/div[@class='requests-container']/div")
    List<WebElement> totalreq;

    @FindBy(xpath = "//body/main/div[@class='requests-container']/div//div//a")
    List<WebElement> totalmsgbtn;

    public boolean isHeadingVisible()
    {
        return heading.isDisplayed();
    }


    public void clickPostRequest()
    {
        btnPostRequest.click();
    }

    public void clickProfile()
    {
        btnProfile.click();
    }

    public void clickLogout()
    {
        btnLogOut.click();
    }

    public void clickAd()
    {
        btnAd.click();
    }

    public int total_Req_Div()
    {
        return totalreq.size();
    }

    public int total_msg_btn()
    {
        return totalmsgbtn.size();
    }
}
