package com.example.testovaci_uloha.tests.actions;
import data.GeneralConstants;
import data.Transactions;
import enums.AccessRights;
import enums.User;
import pages.MainBankPage;
import utils.MySqlUtil;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StandardUserActions {
    private GeneralUserActions generalUserActions = new GeneralUserActions();
    private MainBankPage mainBankPage = new MainBankPage();

    public void loginAction() {
        User user = User.getRandomUserWithSpecificAccessRights(AccessRights.STANDARD)
                .orElseThrow(() -> new IllegalArgumentException("There is no user with view only access rights!"));
        generalUserActions.loginAction(user);
    }
    public void shouldBeTransferMoneyAvailable() {
        assertTrue("Money transfer option should be visible!", generalUserActions.isTransferMoneyAvailable());
    }

    public void transferMoneyToRandomUser() {
        User currentUser = mainBankPage.getCurrentUser();
        User randomReceiver = User.getRandomUserToReceiveMoney(currentUser);
        mainBankPage.sendMoneyTo(randomReceiver);


        MySqlUtil mySqlUtil = new MySqlUtil();
        Transactions lastTransaction = mySqlUtil.getAllMoneyTransfers();

        assertAll("Transfer Money Confirmation",
                () -> assertTrue("Confirmation text for transfer money should be visible!", mainBankPage.isConfirmationTextVisible()),
                () -> assertEquals("Send transaction amount should be same!", GeneralConstants.SEND_MONEY_NEGATIVE, mainBankPage.getLastTransactionAmount()),
                () -> assertEquals("Transaction amount should be same as stored in dbs!", GeneralConstants.SEND_MONEY_AMOUNT, lastTransaction.getAmount())
        );
    }
}
