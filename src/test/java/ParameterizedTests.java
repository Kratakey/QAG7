import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedTests {

    @CsvSource(value = {
            "001; https://www.youtube.com/watch?v=pln38fIbYqA; Десять причин моей ненависти - Андрей Солнцев. QA Fest 2019",
            "002; https://www.youtube.com/watch?v=dQw4w9WgXcQ; Rick Astley - Never Gonna Give You Up (Official Music Video)"
    },
            delimiter = ';')
    @ParameterizedTest(name = "{0}")
    void youtubeTests(Integer number, String page, String text) {
        open(page);
        $("#info-contents").shouldHave(Condition.text(text));
    }

    @EnumSource(value = Params.class, names = {"REASON"}, mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void googleTests(Params params) {
        open("https://google.com");
        $(By.name("q")).sendKeys(params.getText());
        $(By.name("btnK")).click();
        $("#rcnt").shouldHave(Condition.text(params.name()));
    }

    static Stream<Arguments> yandexMethod() {
        return Stream.of(
                Arguments.of(
                        "Garen", "Гарен"
                ),
                Arguments.of(
                        "Shyvana", "Шивана"
                )
        );
    }

    @MethodSource("yandexMethod")
    @ParameterizedTest(name = "{0}")
    void yandexTests(String textEN, String textRU) {
        open("https://yandex.ru");
        $("#text").sendKeys(textEN);
        $("[type=submit]").click();
        $("#search-result").shouldHave(Condition.text(textRU));
    }
}
