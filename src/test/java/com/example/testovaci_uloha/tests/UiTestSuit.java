package com.example.testovaci_uloha.tests;
import enums.WebsiteUrl;
import ui_actor.UiActor;
import com.example.testovaci_uloha.tests.actions.StandardUserActions;
import com.example.testovaci_uloha.tests.actions.ViewOnlyUserActions;
import org.junit.jupiter.api.*;



public class UiTestSuit {
    private ViewOnlyUserActions viewOnlyUserActions = new ViewOnlyUserActions();
    private StandardUserActions standardUserActions = new StandardUserActions();

    @BeforeEach
    public void setUp() {
        UiActor.openChromePage(WebsiteUrl.LOGIN.getUrl());
    }

    @Test
    public void loginStandardUser() {
        standardUserActions.loginAction();
    }

    @Test
    public void loginViewOnlyUser() {
        viewOnlyUserActions.loginAction();
    }

    @Test
    public void checkVisibilityOfTransferMoneyViewOnlyUser() {
        viewOnlyUserActions.loginAction();
        viewOnlyUserActions.shouldNotBeTransferMoneyAvailable();
    }

    @Test
    public void checkVisibilityOfTransferMoneyStandardUser() {
        standardUserActions.loginAction();
        standardUserActions.shouldBeTransferMoneyAvailable();
    }

    @Test
    public void transferMoneyToRandomUser() {
        standardUserActions.loginAction();
        standardUserActions.transferMoneyToRandomUser();
    }



}



