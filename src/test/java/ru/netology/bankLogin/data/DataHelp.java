package ru.netology.bankLogin.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelp {
    private DataHelp() {
    }

    private static Faker faker;

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo() {
        faker = new Faker(new Locale("en"));
        return new AuthInfo(faker.name().username(), faker.internet().password());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }


    public static VerificationCode getNotVerificationCodeFor() {
        return new VerificationCode("54321");
    }

}
