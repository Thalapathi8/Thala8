import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BookingSystemTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void userLoginAndBookHotel() throws InterruptedException {
        driver.get("file:///path/to/homepage.html");

        // User login
        driver.findElement(By.id("userUsername")).sendKeys("testUser");
        driver.findElement(By.id("userPassword")).sendKeys("password123");
        driver.findElement(By.id("userLoginButton")).click();
        Thread.sleep(1000);

        // Search and book hotel
        driver.findElement(By.id("searchHotel")).sendKeys("Hotel Paradise");
        driver.findElement(By.id("searchButton")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//button[contains(text(),'Book Now')]")).click();
        Thread.sleep(500);

        // Fill booking form
        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("john@example.com");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("date")).sendKeys("2025-03-20");
        driver.findElement(By.id("days")).sendKeys("3");
        driver.findElement(By.id("confirmBooking")).click();
        Thread.sleep(500);

        // Verify booking success
        Assert.assertEquals(driver.findElement(By.id("bookingMessage")).getText(), "Booking Successful!");
    }

    @Test(priority = 2)
    public void adminLoginAndCancelBooking() throws InterruptedException {
        driver.get("file:///path/to/homepage.html");

        // Admin login
        driver.findElement(By.id("adminUsername")).sendKeys("admin");
        driver.findElement(By.id("adminPassword")).sendKeys("admin123");
        driver.findElement(By.id("adminLoginButton")).click();
        Thread.sleep(1000);

        // Cancel booking
        int initialBookings = driver.findElements(By.xpath("//tbody[@id='bookingBody']/tr")).size();
        driver.findElement(By.xpath("//td/button[contains(text(),'Cancel')]")).click();
        Thread.sleep(500);

        // Verify cancellation
        Assert.assertEquals(driver.findElement(By.id("cancelMessage")).getText(), "Booking Cancelled Successfully!");
        int updatedBookings = driver.findElements(By.xpath("//tbody[@id='bookingBody']/tr")).size();
        Assert.assertEquals(updatedBookings, initialBookings - 1);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
