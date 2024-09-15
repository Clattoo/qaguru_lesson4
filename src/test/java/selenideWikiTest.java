import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class selenideWikiTest {
    @BeforeAll
    static void setUp () {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

    }

    @Test
    void searchJunit5CodeSelenideWikiTest() {
        // Открытие github.com
        open("https://github.com/");
        // Нажатие на строку поиска
        $("[placeholder='Search or jump to...']").click();
        // Ввод в строку поиска слова selenide
        $("#query-builder-test").setValue("selenide").pressEnter();
        // Проверка наличия заголовка selenide/selenide
        $("[data-testid=\"results-list\"]").shouldHave(text("selenide/selenide"));
        // Переход в репозиторий selenide
        $$("[data-testid=\"results-list\"]").first().$("a").click();
        // Проверка правильности перехода по ссылке, поиск заголовка selenide/selenide
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
        // Переход на страницу Wiki
        $("#wiki-tab").click();
        // Проверка правильности перехода на страницу Wiki
        $("#wiki-content").shouldHave(text("Welcome to the selenide wiki!"));
        // Переход на страницу Soft Assertions
        $("#wiki-content").$(withText("oft assertio")).click();
        //Проверка правильности перехода на страницу Soft Assertions
        $("#wiki-wrapper").shouldHave(text("SoftAssertions"));
        // Проверка наличия примера кода для JUnit5
        $("#wiki-body").shouldHave(text("3. Using JUnit5 extend test class: "
                + """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                @Test
                 void test() {
                   Configuration.assertionMode = SOFT;
                   open("page.html");
                    $("#first").should(visible).click(); 
                    $("#second").should(visible).click(); 
                    }
                }"""));
    }
}
