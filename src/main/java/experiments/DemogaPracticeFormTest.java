package experiments;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DemogaPracticeFormTest {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    public void studentRegistrationFormPositiveTest() {
        // Open the practice form page
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();

        // Remove banner and footer
        hideBanner();
        hideFooter();

        // Fill out basic information
        waitAndSendKeys(By.id("firstName"), "TestName");
        waitAndSendKeys(By.id("lastName"), "TestLastName");
        waitAndSendKeys(By.id("userEmail"), "test@test.com");

        // Select gender
        click(By.xpath("//label[text()='Female']"));

        // Enter a phone number
        waitAndSendKeys(By.id("userNumber"), "0531111111");

        // Set date of birth
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("dateOfBirthInput")));
        dateInput.click();
        dateInput.sendKeys(Keys.CONTROL + "a");
        dateInput.sendKeys("10 May 2000");
        dateInput.sendKeys(Keys.ENTER);

        // Enter subject
        WebElement inputSubjects = wait.until(ExpectedConditions.elementToBeClickable(By.id("subjectsInput")));
        inputSubjects.sendKeys("Maths");
        inputSubjects.sendKeys(Keys.ENTER);

        // Select hobby
        click(By.xpath("//label[text()='Sports']"));

        // Upload picture
        WebElement uploadPicture = driver.findElement(By.id("uploadPicture"));
        uploadPicture.sendKeys("C:\\Users\\shipa\\Desktop\\docs\\your-photo.jpg");

        // Enter address
        waitAndSendKeys(By.id("currentAddress"), "Test Street 1");

        // Select state
        click(By.id("state"));
        click(By.xpath("//div[contains(text(),'NCR')]"));

        // Select city
        click(By.id("city"));
        click(By.xpath("//div[contains(text(),'Delhi')]"));

        // Submit the form
        click(By.id("submit"));

        // Wait for the modal to appear and print submitted data
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        WebElement table = modal.findElement(By.className("table-responsive"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        System.out.println("Submitted data:");
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 2) {
                String label = cells.get(0).getText();
                String value = cells.get(1).getText();
                System.out.println(label + ": " + value);
            }
        }

        // Wait before closing the browser
        pause();
        driver.quit();
    }

    // Removes the banner using JavaScript
    public void hideBanner() {
        ((JavascriptExecutor) driver).executeScript("document.querySelector('#fixedban')?.remove();");
    }

    // Removes the footer using JavaScript
    public void hideFooter() {
        ((JavascriptExecutor) driver).executeScript("document.querySelector('footer')?.remove();");
    }

    // Waits for an element to be visible and sends input text to it
    public void waitAndSendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    // Waits for an element to be clickable and clicks it
    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    // Pauses the execution for 5 seconds
    public void pause() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
