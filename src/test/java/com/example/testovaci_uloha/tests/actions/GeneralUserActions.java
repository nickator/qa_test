package com.example.testovaci_uloha.tests.actions;

import enums.User;
import pages.LoginPage;
import pages.MainBankPage;

public class GeneralUserActions {

    private LoginPage loginPage = new LoginPage();
    private MainBankPage mainBankPage = new MainBankPage();

    public void loginAction(User user) {
        loginPage.sendTextToNameField(user.getName());
        loginPage.sendTextToPasswordField(user.getPassword());
        loginPage.submit();
    }

    public boolean isTransferMoneyAvailable() {
        return mainBankPage.isTransferMoneyAvailable();
    }
}
