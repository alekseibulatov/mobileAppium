package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;

public class OnboardingPage {
    public SelenideElement
            onboardingImage = $(id("org.wikipedia.alpha:id/imageViewCentered")),
            primaryText = $(id("org.wikipedia.alpha:id/primaryTextView")),
            secondaryText = $(id("org.wikipedia.alpha:id/secondaryTextView")),
            continueButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")),
            addLangButton = $(id("org.wikipedia.alpha:id/addLangContainer")),
            rejectToSendDataButton = $(id("org.wikipedia.alpha:id/rejectButton")),
            skipButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")),
            acceptToSendDataButton = $(id("org.wikipedia.alpha:id/acceptButton"));


    public OnboardingPage checkBasicElements(String pageTitle) {
        onboardingImage.shouldBe(visible);
        primaryText.shouldHave(text(pageTitle));
        secondaryText.shouldBe(visible);

        return this;
    }

    public OnboardingPage goToNextPage() {
        continueButton.click();

        return this;
    }

    public OnboardingPage checkButtonIsVisible(SelenideElement element) {
        element.shouldBe(visible);

        return this;
    }
}

