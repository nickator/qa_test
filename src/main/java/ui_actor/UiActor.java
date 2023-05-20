package ui_actor;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.impl.SelenidePageFactory;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;

public class UiActor extends SelenidePageFactory {
    public static void openChromePage(String url) {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open(url);
    }

}
