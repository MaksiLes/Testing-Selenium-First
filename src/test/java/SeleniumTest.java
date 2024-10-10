import org.openqa.selenium.*;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.bidi.module.LogInspector;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.CopyOnWriteArrayList;

public class SeleniumTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void basicOptions() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @Test
    public void myTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        Assert.assertEquals("Web form", title);

        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys("Hello, Olesia!");

        WebElement button = driver.findElement(By.className("btn-outline-primary"));
        button.click();

        WebElement message = driver.findElement(By.id("message"));
        String receivedMessage = message.getText();
        Assert.assertEquals("Received!", receivedMessage);
    }

    @Test
    public void getBrowserName() {
        ChromeOptions chromeOptions = new ChromeOptions();
        String name = chromeOptions.getBrowserName();
        System.out.println(name);
        Assert.assertFalse(name.isEmpty(), "Browser name should not be empty");

        //Загрузка страницы. Можно ждать загрузки всех ресурсов, можно ждать загрузки только первой страницы, или не ждаьб вообще.
        System.out.println(chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL));
        WebDriver driver = new ChromeDriver(chromeOptions);
        try {
            // Navigate to Url
            driver.get("https://selenium.dev");
        } finally {
            driver.quit();
        }

    }

    //Загрузка файла
    @Test
    public void fileUploadTest() {
        driver.get("https://the-internet.herokuapp.com/upload");
        File uploadFile = new File("src/test/resourse/778px-Губка_Боб_персонаж.png");

        WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();

        WebElement fileName = driver.findElement(By.id("uploaded-files"));
        Assert.assertEquals("778px-Губка_Боб_персонаж.png", fileName.getText());
    }

    @Test
    public void actionsApi() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement clickable = driver.findElement(By.name("my-text"));
        new Actions(driver)
                .moveToElement(clickable)
                .pause(Duration.ofSeconds(5))
                .clickAndHold()
                .pause(Duration.ofSeconds(5))
                .sendKeys("abc")
                .pause(Duration.ofSeconds(5))
                .perform();
    }

    @Test
    public void keyDown() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html");

        new Actions(driver)
                .keyDown(Keys.SHIFT)
                .sendKeys("a")
                .perform();

        WebElement textField = driver.findElement(By.id("textInput"));
        System.out.println(textField.getAttribute("value"));
        Assert.assertEquals("A", textField.getAttribute("value"));
    }

    @Test
    public void clickAndHold() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");
        WebElement clickable = driver.findElement(By.id("clickable"));
        new Actions(driver)
                .clickAndHold(clickable)
                .perform();

        Assert.assertEquals("focused", driver.findElement(By.id("click-status")).getText());
    }



    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
