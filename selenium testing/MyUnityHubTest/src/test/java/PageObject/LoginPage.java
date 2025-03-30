package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver dr) {
        super(dr);
    }

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement txtemail;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement txtpass;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement btnlogin;

    @FindBy(xpath = "//button[@id='close-ad']")
    WebElement btnclose;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement btnlogout;

    @FindBy(xpath = "//body//a")
    List<WebElement> totallink;

    @FindBy(xpath = "//a[normalize-space()='UnityHub']")
    WebElement logolink;

    @FindBy(xpath = "//div[@class='login-container']//a[@href='register.html']")
    WebElement lnkdonthaveacc;

    public void fillemail(String email)
    {
        txtemail.sendKeys(email);
    }

    public void fillpass(String pass)
    {
        txtpass.sendKeys(pass);
    }

    public void clicklogin()
    {
        btnlogin.click();
    }

    public void clickclose()
    {
        btnclose.click();
    }

    public void clicklogout()
    {
        btnlogout.click();
    }

    public boolean isdashboarddisplay()
    {
        return btnclose.isDisplayed();
    }

    public int totallink()
    {
        return totallink.size();
    }

    public void clicklogolink()
    {
        logolink.click();

    }

    public void DonthaveLink()
    {
        lnkdonthaveacc.click();
    }

    public void login()
    {
        dashboard ds = new dashboard(dr);
        ds.clicklogin();
        LoginPage lg = new LoginPage(dr);
        lg.fillemail("s@gmail.com");
        lg.fillpass("s");
        lg.clicklogin();
        lg.clickclose();
    }



}
