package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewFolderCloudPage {
    @FindBy(xpath = ".//div[@class='b-thumb__image js-image']")
    private List<WebElement> imagesList;

    public List<WebElement> getImagesNewFolder() {
        return imagesList;
    }
}
