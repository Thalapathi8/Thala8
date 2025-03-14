import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

public class FileUploadDownloadTest {
    WebDriver driver;
    String downloadPath = "C:\\Users\\YourUsername\\Downloads"; // Change to your download folder

    @BeforeMethod
    public void setUp() {
        // Set path to ChromeDriver and initialize WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update path
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///path-to-your-local/file_upload_download.html"); // Update path
    }

    @Test
    public void testFileUpload() {
        // Locate file upload input and upload a valid image file
        WebElement uploadInput = driver.findElement(By.id("fileInput"));
        String filePath = "C:\\path-to-your-image\\test-image.png"; // Update with a valid file
        uploadInput.sendKeys(filePath);

        // Click Upload button
        driver.findElement(By.tagName("button")).click();

        // Wait and verify success message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploadMessage")));
        Assert.assertEquals(message.getText(), "File Uploaded Successfully!", "Upload message mismatch");
    }

    @Test
    public void testFileDownload() throws InterruptedException {
        // Click the download link
        driver.findElement(By.linkText("Click here to download sample.txt")).click();

        // Wait a few seconds for the download to complete
        Thread.sleep(3000);

        // Verify if file exists in the download directory
        File downloadedFile = Paths.get(downloadPath, "sample.txt").toFile();
        Assert.assertTrue(downloadedFile.exists(), "File not downloaded!");
        
        // Optionally, delete the file after verification
        if (downloadedFile.exists()) {
            downloadedFile.delete();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
