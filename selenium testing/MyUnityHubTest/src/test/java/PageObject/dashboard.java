package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class dashboard extends BasePage{

    public dashboard(WebDriver dr) {
        super(dr);
    }

    @FindBy(xpath = "//body//a")
    List<WebElement> totallink;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkreg;

    @FindBy(xpath = "//a[normalize-space()='LogIn']")
    WebElement lnklogin;

    @FindBy(xpath = "//h2[normalize-space()='Register']")
    WebElement txtreg;

    @FindBy(xpath = "//h2[normalize-space()='Login']")
    WebElement txtlogin;

    @FindBy(xpath = "//div[@class='header']//h1[contains(text(),'UnityHub')]")
    WebElement dash;

    @FindBy(xpath = "//div[@class='stats']//div[2]")
    WebElement emgLocButton;

    @FindBy(xpath = "//div[@class='stats']//div[1]")
    WebElement emgCallBtn;

    @FindBy(xpath = "//button[@class='btn']")
    WebElement regbtn;

    public int countlink()
    {
        return totallink.size();
    }

    public void clicklogin()
    {
        lnklogin.click();
    }

    public void clickregister()
    {
        lnkreg.click();
    }

    public void clickemgcall()
    {
        emgCallBtn.click();
    }

    public void clickemdloc()
    {
        emgLocButton.click();
    }

    public void clickregbtn()
    {
        regbtn.click();
    }

    public boolean isregvisible()
    {
        return txtreg.isDisplayed();
    }

    public boolean isloginvisible()
    {
        return txtlogin.isDisplayed();
    }

    public boolean isdashvisible(){return dash.isDisplayed();}


}
