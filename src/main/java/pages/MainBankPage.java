package pages;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.SelenidePageFactory;
import enums.User;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class MainBankPage extends SelenidePageFactory {

    private static final By USER_NAME_LOCATOR = By.xpath("//a[contains(text(),'Odhlásit')]/preceding-sibling::span");
    private static final By TRANSFER_MONEY = By.xpath("//button[@value='transfer']");


    public User getCurrentUser() {
        String userName = $(USER_NAME_LOCATOR).getText();
        return User.getUserByName(userName);
    }

    public void sendMoneyTo(User randomReceiver) {
    }

    public boolean isTransferMoneyAvailable() {
        return Selenide.$(TRANSFER_MONEY).isDisplayed();
    }
}

