package pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.SelenidePageFactory;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage extends SelenidePageFactory {
    
    private static final By SUBMIT_BUTTON = By.xpath("//button[contains(@class, 'btn-primary')]");
    private static final By NAME_FIELD = By.xpath("//input[contains(@id, 'username')]");
    private static final By PASSWORD_FIELD = By.xpath("//input[contains(@id, 'password')]");

    public void waitForLoginButtonToAppear() {
        Selenide.$(SUBMIT_BUTTON).shouldBe(Condition.visible);
    }

    public void sendTextToNameField(String name) {
        Selenide.$(NAME_FIELD).sendKeys(name);
    }

    public void sendTextToPasswordField(String name) {
        Selenide.$(PASSWORD_FIELD).sendKeys(name);
    }

    public void submit() {
        Selenide.$(SUBMIT_BUTTON).click();
        Selenide.$(SUBMIT_BUTTON).should(Condition.disappear, Duration.ofSeconds(10));
    }
}

