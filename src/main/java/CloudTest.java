import Entity.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.CloudPage;
import pages.HomePage;
import pages.InboxPage;
import pages.NewFolderCloudPage;
import utils.Screenshoter;
import utils.WebDriverSingleton;

public class CloudTest {
    private InboxPage inbox;
    private User user = new User("new_account_2018", "password2018");

    @Test(description = "Login test")
    public void loginTest() {
        inbox = new HomePage().open().fillUsername(user.getUsername()).fillPassword(user.getPass()).chooseDomain().signIn();
    //    Assert.assertTrue(inbox.isUserSignIn(), "Authentication failed");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dragNDropImageTest(){
        CloudPage cloudPage = inbox.openCloudPage();
        cloudPage.closePanel();
        cloudPage.dragAndDrop();
        cloudPage.moveToFolder();
        NewFolderCloudPage newfolderpage = new NewFolderCloudPage();
        //Assert.assertTrue(cloudPage.isImageMoved(newfolderpage));
    }

    @AfterClass(description = "closePanel browser")
    public void kill(){
        WebDriverSingleton.kill();
    }
}
