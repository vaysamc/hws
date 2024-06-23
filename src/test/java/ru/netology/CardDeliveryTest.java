package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardDeliveryTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // запуск фон. режим браузер
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCardDeliveryForm() {
        driver.get("http://localhost:9999");

        // поле город
        WebElement cityField = driver.findElement(By.cssSelector("[data-test-id=city] input"));
        cityField.sendKeys("Москва");

        // поле дата
        WebElement dateField = driver.findElement(By.cssSelector("[data-test-id=date] input"));
        dateField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE); // Очистка поля
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateField.sendKeys(date.format(formatter));

        // поля фамилия и имя
        WebElement nameField = driver.findElement(By.cssSelector("[data-test-id=name] input"));
        nameField.sendKeys("Иванов Иван");

        // поле телефон
        WebElement phoneField = driver.findElement(By.cssSelector("[data-test-id=phone] input"));
        phoneField.sendKeys("+79991234567");

        // флажок согласия
        WebElement agreementCheckbox = driver.findElement(By.cssSelector("[data-test-id=agreement]"));
        agreementCheckbox.click();

        // отправка формы
        WebElement submitButton = driver.findElement(By.cssSelector("[data-test-id=submit]"));
        submitButton.click();

        // проверка 
        WebElement successNotification = driver.findElement(By.cssSelector("[data-test-id=success-notification]"));
        assertTrue(successNotification.isDisplayed(), "Уведомление об успешной отправке не отображается");
    }
}