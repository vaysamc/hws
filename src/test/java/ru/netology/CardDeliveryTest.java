package ru.netology;


import com.codeborne.selenide.Condition;
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
        open("http://localhost:9999");
    }

    @Test
    public void testCardDeliveryForm() {
        $("[data-test-id=city] input").setValue("Москва");


        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date.format(formatter));

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79991234567");


        $("[data-test-id=agreement]").click();



        $("[data-test-id=success-notification]").shouldHave(Condition.text("Встреча успешно забронирована на "
                + date), Duration.ofSeconds(15));
    }
}
