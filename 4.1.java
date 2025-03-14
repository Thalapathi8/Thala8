import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class UserPreferencesTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set path to ChromeDriver and initialize WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///path-to-your-local/preferences.html"); // Update with your local path
    }

    @Test
    public void testSelectAllCheckBoxesAndRadio() {
        // Select all checkboxes
        driver.findElement(By.id("newsletters")).click();
        driver.findElement(By.id("updates")).click();
        driver.findElement(By.id("offers")).click();

        // Select a radio button
        driver.findElement(By.xpath("//input[@name='gender'][@value='Male']")).click();

        // Click Save button
        driver.findElement(By.tagName("button")).click();

        // Validate success message
        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Preferences saved successfully!", "Success message is incorrect.");
    }

    @Test
    public void testSelectSomeCheckBoxesAndDifferentRadio() {
        // Select only one checkbox
        driver.findElement(By.id("newsletters")).click();

        // Select another radio button
        driver.findElement(By.cssSelector("input[name='gender'][value='Female']")).click();

        // Click Save button
        driver.findElement(By.tagName("button")).click();

        // Validate success message
        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Preferences saved successfully!", "Success message is incorrect.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
