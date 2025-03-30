package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver dr) {
        super(dr);
    }

    @FindBy(xpath = "//input[@placeholder='Full Name']")
    WebElement txtname;

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement txtpass;

    @FindBy(xpath = "//button[normalize-space()='Register']")
    WebElement btnReg;

    @FindBy(xpath = "//a[normalize-space()='LogIn']")
    WebElement btnlogin;

    @FindBy(css = ".icon icon-generic")
    WebElement log_in_here;


    public void fillname(String name)
    {
        txtname.sendKeys(name);
    }

    public void fillemail(String email)
    {
        txtEmail.sendKeys(email);
    }

    public void fillpass(String pass)
    {
        txtpass.sendKeys(pass);
    }

    public void clickreg(){
        btnReg.click();
    }

    public void clicklogin()
    {
        btnlogin.click();
    }

    public void click_log_in_here()
    {
        log_in_here.click();
    }

    public boolean invalid()
    {
        return log_in_here.isDisplayed();
    }


}
