package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    public void setUp() {
        // Открыть браузер и перейти на страницу приложения
        open("http://localhost:9999");
    }

    @AfterEach
    public void tearDown() {
        // Закрыть браузер после выполнения тестов
        closeWebDriver();
    }

    @Test
    public void testCardDeliveryForm() {
        // Заполнение поля "Город"
        $("[data-test-id=city] input").setValue("Москва");

        // Заполнение поля "Дата"
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date.format(formatter));

        // Заполнение поля "Фамилия и Имя"
        $("[data-test-id=name] input").setValue("Иванов Иван");

        // Заполнение поля "Телефон"
        $("[data-test-id=phone] input").setValue("+79991234567");

        // Установка флажка согласия
        $("[data-test-id=agreement]").click();

        // Отправка формы
        $("[data-test-id=submit]").click();

        // Проверка успешной отправки
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}