package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.EmulatorDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    static String deviceHost = System.getProperty("deviceHost");

    @BeforeAll
    static void beforeAll() {

         switch (deviceHost) {
            case "android":
                Configuration.browser = BrowserstackDriver.class.getName();
                break;
            case "emulator":
                Configuration.browser = EmulatorDriver.class.getName();
                break;
            default:
                throw new RuntimeException(
                        "Invalid value for 'deviceHost'. Valid values are: androi "
                );
        }
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 15000;
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {

        String sessionId = sessionId().toString();

        Attach.pageSource();
        closeWebDriver();

        if (deviceHost.equals("android")) {
            Attach.addVideo(sessionId);
        }
    }
}
