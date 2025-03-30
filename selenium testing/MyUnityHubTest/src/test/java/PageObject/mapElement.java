package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class mapElement extends BasePage{
    public mapElement(WebDriver dr) {
        super(dr);
    }

    @FindBy(css=".search-button")
    WebElement searchbtn;

   public @FindBy(id="searchtext9")
    WebElement searchInput;

    @FindBy(css=".leaflet-control-zoom-in")
    WebElement zoomInBtn;

    @FindBy(css=".leaflet-control-zoom-out")
    WebElement zoomOutBtn;

    public void clickzoomInbtn()
    {
        zoomInBtn.click();
    }

    public void clickzoomoutbtn()
    {
        zoomOutBtn.click();
    }

    public void clickSearchbtn()
    {
        searchbtn.click();
    }

    public void SearchLoc()
    {
        searchInput.sendKeys("mudkhed");
    }
}
