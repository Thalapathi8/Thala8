import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EcommerceTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void searchAndAddProduct() {
        driver.get("file:///path/to/products.html"); // Load Product Listing Page

        // Search for a product (not functional but included for structure)
        WebElement searchBox = driver.findElement(By.id("searchInput"));
        searchBox.sendKeys("Laptop");

        // Add product to cart
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@data-product-id='1']"));
        addToCartButton.click();

        // Navigate to Cart Page
        driver.get("file:///path/to/cart.html");

        // Verify product is in the cart
        WebElement cartItem = driver.findElement(By.xpath("//span[contains(text(),'Laptop')]"));
        WebElement cartPrice = driver.findElement(By.xpath("//span[contains(text(),'$1000')]"));

        Assert.assertTrue(cartItem.isDisplayed(), "Laptop is not added to the cart");
        Assert.assertEquals(cartPrice.getText(), "$1000", "Price mismatch in cart");
    }

    @Test(priority = 2)
    public void updateCartAndCheckout() {
        // Modify quantity
        WebElement quantityInput = driver.findElement(By.className("cart-quantity"));
        quantityInput.clear();
        quantityInput.sendKeys("2");

        // Verify Grand Total update
        WebElement grandTotal = driver.findElement(By.id("grandTotal"));
        Assert.assertEquals(grandTotal.getText(), "$2000", "Grand total is incorrect");

        // Remove Product
        WebElement removeButton = driver.findElement(By.className("remove-btn"));
        removeButton.click();

        // Verify Grand Total changes
        WebElement updatedGrandTotal = driver.findElement(By.id("grandTotal"));
        Assert.assertEquals(updatedGrandTotal.getText(), "$0", "Grand total did not update after removal");

        // Proceed to Checkout
        WebElement checkoutBtn = driver.findElement(By.id("checkoutBtn"));
        checkoutBtn.click();
    }

    @Test(priority = 3)
    public void completeCheckoutProcess() {
        driver.get("file:///path/to/checkout.html");

        // Fill Billing Details
        driver.findElement(By.id("billingName")).sendKeys("John Doe");
        driver.findElement(By.id("billingAddress")).sendKeys("123 Street, City");

        // Select Payment Method
        Select paymentDropdown = new Select(driver.findElement(By.id("paymentMethod")));
        paymentDropdown.selectByVisibleText("Credit Card");

        // Click Place Order
        driver.findElement(By.id("placeOrderBtn")).click();

        // Verify Order Success Message
        driver.get("file:///path/to/orderConfirmation.html");
        WebElement successMessage = driver.findElement(By.id("orderSuccess"));
        Assert.assertEquals(successMessage.getText(), "Order Placed Successfully!", "Order confirmation message mismatch");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
