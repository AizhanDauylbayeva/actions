import Entity.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.CloudPage;
import pages.HomePage;
import pages.InboxPage;
import pages.NewFolderCloudPage;
import utils.WebDriverSingleton;

public class CloudTest {
    private InboxPage inbox;
    private CloudPage cloudPage;
    private NewFolderCloudPage newFolderPage;
    private User user = new User("new_account_2018", "password2018");

    @Test(description = "Login test")
    public void loginTest() {
        inbox = new HomePage().open().fillUsername(user.getUsername()).fillPassword(user.getPass()).chooseDomain().signIn();
        //Assert.assertTrue(inbox.isUserSignedIn(), "Authentication failed");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dragNDropImageTest() {
        cloudPage = inbox.openCloudPage();
        cloudPage.closePanel();
        //cloudPage.dragAndDrop().clickMoveToFolder();
    }

    @Test(dependsOnMethods = "dragNDropImageTest")
    public void doubleClickTest() {
        newFolderPage = cloudPage.moveMouseToNewFolder().doubleClick();
        Assert.assertEquals(newFolderPage.getTitle(), "New folder / Облако Mail.Ru");
    }

    @Test(dependsOnMethods = "doubleClickTest")
    public void openImagesTest() {
        newFolderPage.openImageAndSwitch();
        Assert.assertTrue(newFolderPage.getTitle().contains(newFolderPage.getImageName()));
    }

    @AfterClass(description = "closePanel browser")
    public void kill() {
        WebDriverSingleton.kill();
    }
}
