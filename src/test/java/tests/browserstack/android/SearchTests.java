package tests.browserstack.android;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.className;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

@Tag("android")
public class SearchTests extends TestBase {
    @Test
    @Feature("Поле поиска на сайте Wikipedia на мобильной устройстве Google Pixel 3")
    @Story("Поиск на сайте Wikipedia")
    @Owner("alekseibulatov")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка поиска значения BrowserStack в поле поиска Wikipedia")
    void searchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Feature("Проверка кебаб-меню сайта Wikipedia на  мобильной устройстве Google Pixel 3")
    @Story("Поиск на сайте Wikipedia")
    @Owner("alekseibulatov")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка открытия полей авторизации с главной страницы")
    void openLoginFormTest() {
        step("Кликнуть на кебаб-меню на главной странице", () -> {
            $(id("org.wikipedia.alpha:id/menu_overflow_button")).click();
        });
        step("Кликнуть на пункт Log in to Wikipedia", () -> {
            $(id("org.wikipedia.alpha:id/explore_overflow_account_name")).click();
        });
        step("Проверка что  открылось окно Log in", () -> {
            $(className("android.widget.TextView")).shouldHave(Condition.text("Log in to Wikipedia"));
        });
    }
}
