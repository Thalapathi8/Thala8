import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductSearchTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Set path to chromedriver
        driver = new ChromeDriver();
        driver.get("https://your-search-page-url.com"); // Replace with actual URL
    }

    @Test
    public void testSearchMobile() {
        driver.findElement(By.id("searchBox")).sendKeys("Mobile");
        driver.findElement(By.id("searchButton")).click();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "Product found: Mobile");
    }

    @Test
    public void testSearchRandom() {
        driver.findElement(By.id("searchBox")).sendKeys("Laptop");
        driver.findElement(By.id("searchButton")).click();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "Product not found.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
