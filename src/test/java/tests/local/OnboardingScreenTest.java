package tests.local;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.OnboardingPage;
import tests.TestBase;

import static io.qameta.allure.Allure.step;

public class OnboardingScreenTest extends TestBase {


    @Test
    @Tag("local")
    public void onboardingTest() {

        OnboardingPage onboardingPage = new OnboardingPage();

        step("Check the page with languages", () -> {
            onboardingPage
                    .checkBasicElements("The Free Encyclopedia")
                    .checkButtonIsVisible(onboardingPage.addLangButton)
                    .checkButtonIsVisible(onboardingPage.skipButton)
                    .goToNextPage();
        });

        step("Check the page with info about feed customization", () -> {
            onboardingPage
                    .checkBasicElements("New ways to explore")
                    .goToNextPage();
        });

        step("Check the page with info about sync", () -> {
            onboardingPage
                    .checkBasicElements("Reading lists with sync")
                    .goToNextPage();
        });

        step("Check the page with anonymous data", () -> {
            onboardingPage
                    .checkBasicElements("Send anonymous data")
                    .checkButtonIsVisible(onboardingPage.rejectToSendDataButton)
                    .checkButtonIsVisible(onboardingPage.acceptToSendDataButton);
        });
    }
}

