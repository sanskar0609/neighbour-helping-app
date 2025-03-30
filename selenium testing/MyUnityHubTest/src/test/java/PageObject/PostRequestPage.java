package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PostRequestPage extends BasePage{
    public PostRequestPage(WebDriver dr) {
        super(dr);
    }

    @FindBy(xpath = "//div[@id='map']")
    WebElement Mapdiv;

    @FindBy(xpath = "//input[@id='title']")
    WebElement txtRequestTitle;

    @FindBy(xpath = "//textarea[@id='description']")
    WebElement txtDetail;

    @FindBy(xpath = "//select[@id='category']")
    WebElement selectCat;

    @FindBy(xpath = "//input[@id='latitude']")
    WebElement txtLat;

    @FindBy(xpath = "//input[@id='longitude']")
    WebElement txtLong;

    @FindBy(xpath = "//button[normalize-space()='Post Request']")
    WebElement btnrequest;





    public void selectcategory(String st)
    {
        selectCat.click();
        Select select=new Select(selectCat);
        select.selectByValue(st);
    }

    public boolean isMapVisible()
    {
        return Mapdiv.isDisplayed();
    }

    public void fillReqTitle(String title)
    {
        txtRequestTitle.sendKeys(title);
    }

    public void fillDescription(String desc)
    {
        txtDetail.sendKeys(desc);
    }

    public boolean latlanpresent()
    {
        if(txtLat.getAttribute("value").length()>0&&txtLong.getAttribute("value").length()>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public void postRequestSubmitbtn()
    {
        btnrequest.click();
    }


}
