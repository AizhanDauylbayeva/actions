package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CloudPage extends AbstractPage{
    @FindBy(xpath = ".//div[@class='b-thumb__image js-image']")
    private List<WebElement> imagesList;

    @FindBy(xpath = "//div[@data-id='/New folder']/*[@class='b-thumb__content']")
    private WebElement newFolder;

    @FindBy(xpath = "//button[@data-name='move']")
    private WebElement moveButton;

    @FindBy(xpath = "//div[@class='b-panel__close__icon']")
    private WebElement closepanel;

//    @FindBy(xpath = "b-file-folder-count breadcrumbs__file-folder-count")
//    private WebElement fileCount;

    public void closePanel(){
        closepanel.click();
    }

    private List<WebElement> getImagesList() {
        return imagesList;
    }

    public void dragAndDrop(){
        new Actions(driver).dragAndDrop(getImagesList().get(0), newFolder).build().perform();
    }

    public void moveToFolder(){
        waitForElementEnabled(moveButton);
        moveButton.click();
    }

/*    public boolean isImageMoved(NewFolderCloudPage newPage){
        newFolder.click();
        return getImagesList().get(0).equals(newPage.getImagesNewFolder().get(0));
    }*/

    /*public void switchToPreviousTab(){
        new Actions(driver).sendKeys(driver.findElement(By.tagName("html")), Keys.CONTROL).sendKeys(driver.findElement(By.tagName("html")),Keys.NUMPAD1).build().perform();
    }*/
}
