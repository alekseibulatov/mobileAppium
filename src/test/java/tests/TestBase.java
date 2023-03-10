package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.MobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = MobileDriver.class.getName();

        String deviceHost = System.getProperty("deviceHost");
        switch (deviceHost) {
            case "android":
            case "ios":
                Configuration.browser = BrowserstackDriver.class.getName();
                break;
            case "emulator":
                Configuration.browser = MobileDriver.class.getName();
                break;
            default:
                throw new RuntimeException(
                        "Invalid value for 'deviceHost'. Valid values are: android / ios / emulator "
                );
        }
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String deviceHost = System.getProperty("deviceHost");

        //   Attach.pageSource();
        closeWebDriver();

        if (deviceHost.equals("android")) {
            Attach.addVideo(sessionId().toString());
        }
    }
}
