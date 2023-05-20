package com.example.testovaci_uloha.tests.actions;

import enums.AccessRights;
import enums.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ViewOnlyUserActions {
    private GeneralUserActions generalUserActions = new GeneralUserActions();

    public void loginAction() {
        User user = User.getRandomUserWithSpecificAccessRights(AccessRights.VIEW_0NLY)
                .orElseThrow(() -> new IllegalArgumentException("There is no user with view only access rights!"));
        generalUserActions.loginAction(user);
    }

    public void shouldNotBeTransferMoneyAvailable() {
        assertFalse("Money transfer option shouldn't be visible", generalUserActions.isTransferMoneyAvailable());
    }
}
