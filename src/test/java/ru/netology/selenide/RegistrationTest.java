package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationTest {

    @BeforeEach
    void setup() {
        Selenide.open("http://localhost:9999");
    }

    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void cardDeliveryOrderTest() {
        Selenide.open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue("Москва");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        form.$("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(planningDate);
        form.$("[data-test-id='name'] input").setValue("Громов Павел");
        form.$("[data-test-id='phone'] input").setValue("+79990000000");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $(".notification__content")
            .should(Condition.visible, Duration.ofSeconds(15))
            .should(Condition.text("Встреча успешно забронирована на " + planningDate));



    }
}
