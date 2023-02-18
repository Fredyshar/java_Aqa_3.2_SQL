package ru.netology.bankLogin.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bankLogin.data.DataHelp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement buttonNext = $("[data-test-id='action-login']");
    private SelenideElement errorMassage = $("[data-test-id='error-notification']");

    public LoginPage() {
        $("h2").shouldBe(visible);
    }

    public void getErrorMassage(String textError) {
        errorMassage
                .shouldHave(Condition.text(textError))
                .shouldBe(visible);
    }

    public VerificationPage validLogin(DataHelp.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        buttonNext.click();
        return new VerificationPage();
    }

    public void invalidLoginOrPassword(DataHelp.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        buttonNext.click();
    }
}
