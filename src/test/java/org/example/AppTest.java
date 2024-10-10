package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class AppTest {
    public static void main(String[] args) {
        //начинаем сеанс
        WebDriver driver = new ChromeDriver();
        //Переходим на веб-страницу
//        driver.navigate().to("https://google.com");
//        // Переходим на другую веб страницу
//        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
//        // развернуть страницу на весь экран
//        driver.manage().window().maximize();
//        //Получить заголовок текущей страницы, если текущий совпадает с ожиданием, то ошибки не будет
//        String title = driver.getTitle();
//        Assert.assertEquals(title, "Web form");
//        // Получить url текущей страницы
//        String url = driver.getCurrentUrl();
//        Assert.assertEquals(url,"https://www.selenium.dev/selenium/web/web-form.html");
//        // Просто ожидаем, когда загрузиться все, так как нужно точно знать что эл есть на странице
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//        //Ищем на странице элемент с селектором name и button
//        WebElement textBox = driver.findElement(By.name("my-text"));
//        WebElement submitButton = driver.findElement(By.cssSelector("button"));
//        // Ищем на странице элемент с селектором classname
//        WebElement element = driver.findElement(By.className("form-select"));
//        //увидели, что это массив, состоящий из 4 элементов и нам нужно подобраться к ко второму элементу
//        List <WebElement> l = element.findElements(By.tagName("option"));
//        //Получаем эти 4 элемента
//        String elements = element.getText();
//        System.out.println(elements);
//        //получаем второй из них
//        System.out.println(l.get(1).getText());
//        //Вводим в input слово Selenium
//        textBox.sendKeys("Selenium");
//        // Нажимаем кнопку
//        submitButton.click();

//        driver.get("https://demo.guru99.com/test/guru99home/");
////        driver.switchTo().frame("a077aa5e");
////        System.out.println("We are switch to the iframe");
//        driver.findElement(By.xpath("//div/div/div/div/div/div/div/div/div/img")).click();
//        System.out.println("We are done");

        //Работа с элементами списка выбора
        //Сначала нужно найти select элемент, потом нужно его инициализировать
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement selectElement = driver.findElement(By.name("my-select"));
        Select select = new Select(selectElement);
        //Тут мы получаем список всех опций в select элементе + я сравнила, элементы можно получить как через метод getOptions()
        //Так и если мы найдем элементы в списке с помощью тега. Он выведет все элементы с этим тегом в списке.
        List<WebElement> optionList = select.getOptions();
        List<WebElement> optionElements = selectElement.findElements(By.tagName("option"));
        System.out.println(optionList.get(1).getText());
        System.out.println(optionElements.get(1).getText());
        Assert.assertEquals(optionElements,optionList);
        //Тут мы получаем тот элемент, который выбран по умолчанию
        List<WebElement> selectedOptionList = select.getAllSelectedOptions();
        System.out.println(selectedOptionList.get(0).getText());
        //Тут мы выбираем любой элемент из списка, который нам нужен, по value. Так же можно по индексу, и по тексту в элементе
        select.selectByValue("2");
        //Кликнуть по элементы
//        WebElement clickElement = driver.findElement(By.name("my-colors"));
//        clickElement.click();
        //Очистить поле и отправить новые ключи
        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys("Selenium");
        //Узнать информацию о веб элементе, доступен ли он.
        driver.get("https://www.selenium.dev/selenium/web/inputs.html");
        boolean isEmailVisible = driver.findElement(By.name("email_input")).isDisplayed();
        Assert.assertEquals(isEmailVisible,true);
        //Закончить работу, заснуть
        driver.quit();


    }

}




