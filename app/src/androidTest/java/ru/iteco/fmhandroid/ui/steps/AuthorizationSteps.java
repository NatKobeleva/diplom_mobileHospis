package ru.iteco.fmhandroid.ui.steps;

import ru.iteco.fmhandroid.ui.page.AuthorizationPage;

public class AuthorizationSteps {

    private final AuthorizationPage authPage = new AuthorizationPage();

    public void performLogin(String login, String password) {
        authPage
                .enterLogin(login)
                .enterPassword(password)
                .clickLoginButton();
    }

    public void performLoginWithEmptyLogin(String password) {
        authPage
                .enterLogin("")
                .enterPassword(password)
                .clickLoginButton()
                .checkLoginErrorMessage("Login and password cannot be empty");
    }

    public void performLoginWithEmptyPassword(String login) {
        authPage
                .enterLogin(login)
                .enterPassword("")
                .clickLoginButton()
                .checkLoginErrorMessage("Login and password cannot be empty");
    }

    public void performLoginWithWrongLogin(String password) {
        String randomLogin = "user_" + java.util.UUID.randomUUID().toString().substring(0, 8);
        authPage
                .enterLogin(randomLogin)
                .enterPassword(password)
                .clickLoginButton()
                .checkLoginErrorMessage("Something went wrong. Try again later.");
    }

    public void performLoginWithWrongPassword(String login) {
        String randomPassword = "pass_" + java.util.UUID.randomUUID().toString().substring(0, 8);
        authPage
                .enterLogin(login)
                .enterPassword(randomPassword)
                .clickLoginButton()
                .checkLoginErrorMessage("Something went wrong. Try again later.");
    }

    public void performLoginWithWrongLoginAndPass() {
        String randomLogin = "user_" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String randomPassword = "pass_" + java.util.UUID.randomUUID().toString().substring(0, 8);
        authPage
                .enterLogin(randomLogin)
                .enterPassword(randomPassword)
                .clickLoginButton()
                .checkLoginErrorMessage("Something went wrong. Try again later.");
    }
}