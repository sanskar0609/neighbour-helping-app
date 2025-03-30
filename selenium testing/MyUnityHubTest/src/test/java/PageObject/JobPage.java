package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class JobPage extends BasePage{
    public JobPage(WebDriver dr) {
        super(dr);
    }

    @FindBy(xpath = "//button[@id='openFormBtn']")
    WebElement btnpost_job;

    @FindBy(xpath = "//select[@id='filterType']")
    WebElement selectfilter;

    @FindBy(xpath = "//input[@id='title']")
    WebElement txttitle;

    @FindBy(xpath = "//textarea[@id='description']")
    WebElement txtDesc;

    @FindBy(xpath = "//select[@id='type']")
    WebElement selectType;

    @FindBy(xpath = "//input[@id='image']")
    WebElement uploadImage;

    @FindBy(xpath = "//button[normalize-space()='Upload']")
    WebElement btnupload;

    @FindBy(xpath = "//span[@class='close']")
    WebElement btnclose;

    public void filltitle(String title)
    {
        txttitle.sendKeys(title);
    }

    public void filldesc(String desc)
    {
        txtDesc.sendKeys(desc);
    }

    public void setSelectType(String type)
    {
        Select select=new Select(selectType);
        select.selectByVisibleText(type);
    }

    public void setUploadImage(String path)
    {
        uploadImage.sendKeys(path);
    }

    public void upload_post()
    {
        btnupload.click();
    }

    public void setSelectfilter(String fil)
    {
        Select select=new Select(selectfilter);
        select.selectByVisibleText(fil);
    }

    public void close()
    {
        btnclose.click();
    }

    public void setPost_job()
    {
        btnupload.click();
    }

    public void uploadJob()
    {
        btnpost_job.click();
    }


}
