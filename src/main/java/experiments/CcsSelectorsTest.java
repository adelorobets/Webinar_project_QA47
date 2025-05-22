package experiments;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CcsSelectorsTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void selectorsHomePage() {
        driver.get("https://demoqa.com/");
        hideBanner();
        hideFooter();

        // Получение логотипа
        WebElement imgTools = driver.findElement(By.cssSelector("img[src='/images/Toolsqa.jpg']"));
        System.out.println("Tag: " + imgTools.getTagName());
        System.out.println("Src: " + imgTools.getAttribute("src"));

        // Переход в блок Elements
        WebElement divElements = driver.findElement(By.cssSelector(".card-up"));
        divElements.click();

        // Клик по пункту "Radio Button"
        WebElement radioButton = driver.findElement(By.id("item-2"));
        radioButton.click();

        // Клик по радио-кнопке "Yes"
        WebElement radioButtonYes = driver.findElement(By.cssSelector("label[for='yesRadio']"));
        radioButtonYes.click();

        pause(10);
        driver.navigate().back();
        pause(10);
        driver.navigate().back();
        hideFooter();

        // Клик по последнему элементу (Book Store)
        WebElement divBookStore = driver.findElement(By.cssSelector("div.category-cards > div.card.mt-4.top-card:last-child"));
        divBookStore.click();

        pause(10);
        driver.navigate().back();

        // Клик по четвертому элементу (Widgets)
        WebElement divWidgets = driver.findElement(By.cssSelector("div.category-cards > div.card.mt-4.top-card:nth-child(4)"));
        divWidgets.click();

        pause(10);
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void hideBanner() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#fixedban')?.style.setProperty('display', 'none');");
    }

    public void hideFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('footer')?.style.setProperty('display', 'none');");
    }
}
