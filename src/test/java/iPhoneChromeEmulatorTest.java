import io.trueautomation.client.driver.TrueAutomationDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.trueautomation.client.TrueAutomationHelper.ta;


public class iPhoneChromeEmulatorTest {

    public static WebDriver driver;

    @BeforeTest
    public void spinUp() {
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", "iPhone 6/7/8");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        driver = new TrueAutomationDriver(chromeOptions);

    }


    @Test
    public void clickCategoryFromHome () throws InterruptedException {
        driver.navigate().to("http://annieselke.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        By category = By.xpath(ta("taCategory","//button//span//a[contains(text(),'Rugs')]"));

        WebElement element = driver.findElement(category);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        By categoryPage = By.xpath(ta("taCategoryPage", "//div[@data-page='Subcategory']"));

        Assert.assertEquals("Subcategory", driver.findElement(categoryPage).getAttribute("data-page"));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
