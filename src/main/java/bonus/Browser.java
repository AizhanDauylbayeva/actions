package bonus;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Browser {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 10;
    private WebDriver driver;
    private static Browser instance = null;

    private Browser(WebDriver driver) {
        this.driver = driver;
    }

    public static Browser getInstance() {
        if (instance != null) {
            return instance;
        }
        return instance = init();
    }

    private static Browser init() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return new Browser(driver);
    }

    public void open(String url) {
        Logger.info("Going to URL: " + url);
        driver.get(url);
    }

    public static void kill() {
        if (instance != null) {
            try {
                instance.driver.quit();
            } finally {
                instance = null;
            }
        }
    }

    protected void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", element);
    }

    protected void unHighlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", element);
    }

    protected void waitForElementVisible(WebElement element) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementEnabled(WebElement element) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void click(WebElement element) {
        waitForElementVisible(element);
        Logger.info("Clicking element '" + element.getText() + "' (Located: " + element + ")");
        element.click();
    }

}
