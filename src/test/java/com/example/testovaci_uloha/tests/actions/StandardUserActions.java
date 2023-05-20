package com.example.testovaci_uloha.tests.actions;

import enums.AccessRights;
import enums.User;
import pages.MainBankPage;

import static org.junit.Assert.assertTrue;

public class StandardUserActions {
    private GeneralUserActions generalUserActions = new GeneralUserActions();
    private MainBankPage mainBankPage = new MainBankPage();
    public void loginAction() {
        User user = User.getRandomUserWithSpecificAccessRights(AccessRights.STANDARD)
                .orElseThrow(() -> new IllegalArgumentException("There is no user with view only access rights!"));
        generalUserActions.loginAction(user);
    }
    public void shouldBeTransferMoneyAvailable() {
        assertTrue(generalUserActions.isTransferMoneyAvailable());
    }

    public void transferMoneyToRandomUser() {
        User currentUser = mainBankPage.getCurrentUser();
        User randomReceiver = User.getRandomUserToReceiveMoney(currentUser);
        mainBankPage.sendMoneyTo(randomReceiver);
    }
}
