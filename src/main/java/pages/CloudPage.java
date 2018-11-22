package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CloudPage extends AbstractPage {
    private Actions action = new Actions(driver);

    @FindBy(xpath = ".//div[@class='b-thumb__image js-image']")
    private List<WebElement> imagesList;

    @FindBy(xpath = "//div[@data-id='/New folder']/*[@class='b-thumb__content']")
    private WebElement newFolder;

    @FindBy(xpath = "//button[@data-name='move']")
    private WebElement moveButton;

    @FindBy(xpath = "//div[@class='b-panel__close__icon']")
    private WebElement closepanel;

    public void closePanel() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", closepanel);
    }

    private List<WebElement> getImagesList() {
        return imagesList;
    }

    public CloudPage dragAndDrop() {
        action.dragAndDrop(getImagesList().get(0), newFolder).build().perform();
        return this;
    }

    public void clickMoveToFolder() {
        highlightElement(moveButton);
        waitForElementEnabled(moveButton);
        unHighlightElement(moveButton);
        moveButton.click();
    }

    public CloudPage moveMouseToNewFolder() {
        waitForElementEnabled(newFolder);
        action.moveToElement(newFolder).perform();
        return this;
    }

    public NewFolderCloudPage doubleClick() {
        action.doubleClick().build().perform();
        return new NewFolderCloudPage();
    }

}
