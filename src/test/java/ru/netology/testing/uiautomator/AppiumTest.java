package ru.netology.testing.uiautomator;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ignoreHiddenApiPolicyError", true);
        desiredCapabilities.setCapability("appium:noReset", true);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void emptyTextTest() {
        PageLocator pageLocator = new PageLocator(driver);
        pageLocator.userInput.sendKeys("Netology");
        pageLocator.buttonChange.click();
        pageLocator.textToBeChanged.isDisplayed();
        pageLocator.userInput.sendKeys(" ");
        pageLocator.buttonChange.click();
        pageLocator.textToBeChanged.isDisplayed();
        Assertions.assertEquals("Netology", pageLocator.textToBeChanged.getText());
    }

    @Test
    public void newActivityTest() {
        PageLocator pageLocator = new PageLocator(driver);
        pageLocator.userInput.sendKeys("Netology2");
        pageLocator.buttonActivity.click();
        pageLocator.text.isDisplayed();
        Assertions.assertEquals("Netology2", pageLocator.text.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

