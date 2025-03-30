package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage{

    public ProfilePage(WebDriver dr) {
        super(dr);
    }

    @FindBy(xpath = "//div[@class='card']")
    WebElement card;

    @FindBy(xpath = "//p[@class='email']")
    WebElement gmail;

    @FindBy(xpath = "//a[normalize-space()='Back to Dashboard']")
    WebElement btnbackToDash;

    @FindBy(xpath = "//button[normalize-space()='Upload']")
    WebElement btnupload;

    @FindBy(xpath = "//input[@id='profile_photo']")
    WebElement selectFile;

    @FindBy(css = ".username")
    WebElement username;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement btnlogout;

    public  String  getUsername()
    {
        return username.getText();
    }

    public String getEmail()
    {
        return gmail.getText();
    }
    public void click_Go_Dash()
    {
        btnbackToDash.click();
    }

    public void setSelectFile(String path)
    {
        selectFile.sendKeys(path);
    }

    public void clickupload()
    {
        btnupload.click();
    }

    public void logout()
    {
        btnlogout.click();
    }




}
