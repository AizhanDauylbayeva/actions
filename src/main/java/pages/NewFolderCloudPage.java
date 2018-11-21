package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverSingleton;

import java.util.ArrayList;
import java.util.List;

public class NewFolderCloudPage extends AbstractPage{
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

    public String getTitle(){
        return WebDriverSingleton.getWebDriverInstance().getTitle();
    }

    public void switchTab(){
        action
                .keyDown(Keys.CONTROL)
                .sendKeys(Keys.TAB)
                .build()
                .perform();
    }

    public void contextClick(){
        closeContainer.click();
        highlightElement(cloudLink);
        action.moveToElement(cloudLink).perform();
        action
                .contextClick()
        .moveByOffset(5,-19)
                .click()
//                .sendKeys(Keys.ARROW_DOWN)
//                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        unHighlightElement(cloudLink);
    }

    public CloudPage openNewTab(){
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        return new CloudPage();
    }

}