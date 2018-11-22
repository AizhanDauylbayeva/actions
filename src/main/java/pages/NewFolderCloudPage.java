package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverSingleton;

import java.util.ArrayList;
import java.util.List;

public class NewFolderCloudPage extends AbstractPage {
    private Actions action = new Actions(driver);

    @FindBy(xpath = "//a[@class='js-href breadcrumbs__item__link']")
    private WebElement cloudLink;

    @FindBy(xpath = ".//div[@class='b-thumb__image js-image']")
    private List<WebElement> imagesList;

    @FindBy(xpath = ".//div[@class='b-bubble__container']//i[@data-bem='ico']")
    private WebElement closeContainer;

    public List<WebElement> getImagesNewFolder() {
        return imagesList;
    }


    public String getTitle() {
        return WebDriverSingleton.getWebDriverInstance().getTitle();
    }

    public void openImageAndSwitch() {
        closeContainer.click();
        highlightElement(cloudLink);
        action.click(cloudLink).perform();
        action
                .moveToElement(getImagesNewFolder().get(0))
                .click()
                .sendKeys(Keys.ARROW_RIGHT)
                .build()
                .perform();
    }

    public String getImageName(){
        return getImagesNewFolder().get(1).getTagName();
    }

    public CloudPage openNewTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        return new CloudPage();
    }

}