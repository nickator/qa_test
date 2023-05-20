package pages;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.SelenidePageFactory;
import data.GeneralConstants;
import enums.User;
import org.openqa.selenium.By;
import utils.MySqlUtil;
import utils.TableNavigator;
import utils.Utils;

import static com.codeborne.selenide.Selenide.$;
import static data.GeneralConstants.AMOUNT_TEXT;


public class MainBankPage extends SelenidePageFactory {

    private static final By USER_NAME_LOCATOR = By.xpath("//a[contains(text(),'Odhlásit')]/preceding-sibling::span");
    private static final By TRANSFER_MONEY_BUTTON = By.xpath("//button[@value='transfer']");
    private static final By RECIVER_NAME_FIELD = By.xpath("//input[@id='target']");
    private static final By AMOUNT_FIELD = By.xpath("//input[contains(@id, 'amount')]");
    private static final By NOTE_FIELD = By.xpath("//input[@id='note']");
    private static final By TRANSFER_CONFIRMATION_TEXT = By.xpath("//div[contains(@class,'alert-success') and contains(text(),'Peníze převedeny')]");


    public User getCurrentUser() {
        String userName = $(USER_NAME_LOCATOR).getText();
        return User.getUserByName(userName);
    }

    public void sendMoneyTo(User randomReceiver) {
        $(RECIVER_NAME_FIELD).sendKeys(randomReceiver.getName());
        $(AMOUNT_FIELD).sendKeys(Double.toString(GeneralConstants.SEND_MONEY_AMOUNT));
        $(NOTE_FIELD).sendKeys(GeneralConstants.NOTE_TEXT);
        $(TRANSFER_MONEY_BUTTON).click();
    }

    public boolean isTransferMoneyAvailable() {
        return $(TRANSFER_MONEY_BUTTON).isDisplayed();
    }

    public boolean isConfirmationTextVisible() {
        return $(TRANSFER_CONFIRMATION_TEXT).isDisplayed();
    }

    public Double getLastTransactionAmount() {
        String amountText = TableNavigator.getFirstFieldValueByColumnIndex(AMOUNT_TEXT);
        return Utils.convertCurrencyTextToDouble(amountText);
    }
}

