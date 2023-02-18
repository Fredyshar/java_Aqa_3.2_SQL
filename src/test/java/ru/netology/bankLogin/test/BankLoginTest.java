package ru.netology.bankLogin.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.bankLogin.page.LoginPage;
import ru.netology.bankLogin.data.SqlHelp;
import ru.netology.bankLogin.data.DataHelp;

import static com.codeborne.selenide.Selenide.open;

public class BankLoginTest {
    @AfterAll
    static void cleaning() {
        SqlHelp.cleanDatabase();
    }

    @Test
    void shouldsuccesAuth() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelp.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SqlHelp.getVerCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldReturnErrorLoginOrPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelp.getOtherAuthInfo();
        loginPage.invalidLoginOrPassword(authInfo);
        loginPage.getErrorMassage("Неверно указан логин или пароль");
    }

}
