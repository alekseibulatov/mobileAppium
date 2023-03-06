package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.EmulatorConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class MobileDriver implements WebDriverProvider {


    public static URL getAppiumServerUrl() {
     //   EmulatorConfig emulatorConfig = ConfigFactory.create(EmulatorConfig.class, System.getProperties());
        try {
          //  return new URL(emulatorConfig.appiumServer());
            return new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Nonnull
    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        EmulatorConfig emulatorConfig = ConfigFactory.create(EmulatorConfig.class, System.getProperties());

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setDeviceName(emulatorConfig.deviceName())
                .setPlatformVersion(emulatorConfig.platformVersion())
                .setApp(getAppPath())
                .setAppPackage(emulatorConfig.appPackage())
                .setAppActivity(emulatorConfig.appActivity());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    private String getAppPath() {
        EmulatorConfig emulatorConfig = ConfigFactory.create(EmulatorConfig.class, System.getProperties());

        File app = new File(emulatorConfig.appPath());
        if (!app.exists()) {
            try (InputStream in = new URL(emulatorConfig.appURL()).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}
