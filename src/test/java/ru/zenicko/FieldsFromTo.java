package ru.zenicko;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Тест съют: Тестирование полей \"Откуда\" и \"Куда\"")
public class FieldsFromTo {

    @BeforeEach
    void openPageCreateRoute() {
        open("https://yandex.ru/maps/213/moscow/?ll=37.622504%2C55.753215&mode=routes&rtext=&rtt=auto&z=10");
        $("input[placeholder=Куда]").shouldBe(Condition.visible);
    }

    @Tag("Non-parameterized")
    @DisplayName("Non-parameterized: Наличие надписи адреса на карте после ввода адреса в поле \"Откуда\"")
    @Test
        // Большой Козихинский переулок, 5с5
        // 179-й квартал
    void shouldBeAddressOnMap() {
        String fieldName = "Откуда";
        String expectedAddress = "Большой Козихинский переулок, 5с5";
        String actualAddress = "";

        $(format("input[placeholder=%s]", fieldName))
                .setValue(expectedAddress).pressEnter();

        sleep(2000);
        for (SelenideElement temp : $$(".map-placemark__wrapper .waypoint-pin-view__text span")) {
            actualAddress += temp.text() + " ";
        }
        actualAddress = actualAddress.substring(0, actualAddress.length() - 1);

        Assertions.assertEquals(expectedAddress, actualAddress,
                format("The actual address %s is not equal the expected address %s", actualAddress, expectedAddress));

    }

    @Tag("Parameterized: one parameter (@ValueSource)")
    @ValueSource(strings = {"Откуда", "Куда"})
    @DisplayName("Parameterized: one parameter (@ValueSource)")
    @ParameterizedTest(name="Наличие надписи адреса на карте после ввода адреса в поле \"{0}\"")
    void shouldBeAddressOnMap(String fieldName) {
        String expectedAddress = "Большой Козихинский переулок, 5с5";
        String actualAddress = "";

        $(format("input[placeholder=%s]", fieldName))
                .setValue(expectedAddress).pressEnter();

        sleep(2000);

        for (SelenideElement temp : $$(".map-placemark__wrapper .waypoint-pin-view__text span")) {
            actualAddress += temp.text() + " ";
        }
        actualAddress = actualAddress.substring(0, actualAddress.length() - 1);

        Assertions.assertEquals(expectedAddress, actualAddress,
                format("The actual address %s is not equal the expected address %s", actualAddress, expectedAddress));
    }

    @Tag("Parameterized: two parameters (CsvSource)")
    @CsvSource(value = {"Откуда|Большой Козихинский переулок, 5с5", "Куда|179-й квартал"}, delimiter = '|')
    @DisplayName("Parameterized: two parameters (@CsvSource)")
    @ParameterizedTest(name="Наличие надписи адреса ({1}) на карте после ввода адреса в поле \"{0}\"")
    void shouldBeAddressOnMap(String fieldName, String expectedAddress) {
        String actualAddress = "";

        $(format("input[placeholder=%s]", fieldName))
                .setValue(expectedAddress).pressEnter();

        sleep(2000);

        for (SelenideElement temp : $$(".map-placemark__wrapper .waypoint-pin-view__text span")) {
            actualAddress += temp.text() + " ";
        }
        actualAddress = actualAddress.substring(0, actualAddress.length() - 1);

        Assertions.assertEquals(expectedAddress, actualAddress,
                format("The actual address %s is not equal the expected address %s", actualAddress, expectedAddress));
    }

    @Tag("Parameterized: three parameters (@CsvFileSource)")
    @CsvFileSource(resources = "/testdata.csv", encoding = "cp1251", delimiter = ';')
    @DisplayName("Parameterized: three parameters (@CsvFileSource)")
    @ParameterizedTest(name="Наличие надписи адреса ({1}) на карте после ввода адреса в поле \"{0}\"")
    void shouldBeAddressOnMap(String fieldName, String expectedAddress, String thirdParam ) {
        String actualAddress = "";

        $(format("input[placeholder=%s]", fieldName))
                .setValue(expectedAddress).pressEnter();

        sleep(2000);

        for (SelenideElement temp : $$(".map-placemark__wrapper .waypoint-pin-view__text span")) {
            actualAddress += temp.text() + " ";
        }
        actualAddress = actualAddress.substring(0, actualAddress.length() - 1);

        Assertions.assertEquals(expectedAddress, actualAddress,
                format("The actual address %s is not equal the expected address %s", actualAddress, expectedAddress));
    }


    static Stream<Arguments> shouldBeAddressOnMapProvider() {
        return Stream.of(
                arguments("Откуда", "Большой Козихинский переулок, 5с5", "1", "1"),
                arguments("Куда", "179-й квартал", "2", "2")
        );
    }

    @Tag("Parameterized: four parameters (@MethodSource)")
    @MethodSource("shouldBeAddressOnMapProvider")
    @DisplayName("Parameterized: four parameters (@MethodSource)")
    @ParameterizedTest(name="Наличие надписи адреса ({1}) на карте после ввода адреса в поле \"{0}\"")
    void shouldBeAddressOnMap(String fieldName, String expectedAddress, String thirdParam, int fourParam ) {
        String actualAddress = "";

        $(format("input[placeholder=%s]", fieldName))
                .setValue(expectedAddress).pressEnter();

        sleep(2000);

        for (SelenideElement temp : $$(".map-placemark__wrapper .waypoint-pin-view__text span")) {
            actualAddress += temp.text() + " ";
        }
        actualAddress = actualAddress.substring(0, actualAddress.length() - 1);

        Assertions.assertEquals(expectedAddress, actualAddress,
                format("The actual address %s is not equal the expected address %s", actualAddress, expectedAddress));
    }

}





