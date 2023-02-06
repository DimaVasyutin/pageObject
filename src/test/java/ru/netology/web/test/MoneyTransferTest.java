package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.Transfer;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    private String idFirstCard = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    private String idSecondCard = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    @Test
    void shouldTransferMoneyFromFirstToSecond() {
        var loginPage = new LoginPageV2();
        var transfer = new Transfer();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        int balanceBefore = verificationPage.validVerify(verificationCode).getCardBalance(idFirstCard);
        int sum = 1000;
        int balanceAfter = transfer.transferFromFistToSecond(authInfo, sum).getCardBalance(idFirstCard);
        int expected = balanceBefore - sum;
        Assertions.assertEquals(expected, balanceAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        var loginPage = new LoginPageV2();
        var transfer = new Transfer();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        int balanceBefore = verificationPage.validVerify(verificationCode).getCardBalance(idSecondCard);
        int sum = 1000;
        int balanceAfter = transfer.transferFromSecondToFirst(authInfo, sum).getCardBalance(idSecondCard);
        int expected = balanceBefore - sum;
        Assertions.assertEquals(expected, balanceAfter);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondMoreThanThereIsMoney() {
        var loginPage = new LoginPageV2();
        var transfer = new Transfer();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        int balanceBefore = verificationPage.validVerify(verificationCode).getCardBalance(idFirstCard);
        int sum = balanceBefore + 1000;
        int balanceAfter = transfer.transferFromFistToSecond(authInfo, sum).getCardBalance(idFirstCard);
        int expected = balanceBefore - sum;
        Assertions.assertEquals(expected, balanceAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstMoreThanThereIsMoney() {
        var loginPage = new LoginPageV2();
        var transfer = new Transfer();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        int balanceBefore = verificationPage.validVerify(verificationCode).getCardBalance(idSecondCard);
        int sum = balanceBefore + 1000;
        int balanceAfter = transfer.transferFromSecondToFirst(authInfo, sum).getCardBalance(idSecondCard);
        int expected = balanceBefore - sum;
        Assertions.assertEquals(expected, balanceAfter);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondNegativSum() {
        var loginPage = new LoginPageV2();
        var transfer = new Transfer();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        int balanceBefore = verificationPage.validVerify(verificationCode).getCardBalance(idFirstCard);
        int sum = -1000;
        int balanceAfter = transfer.transferFromFistToSecond(authInfo, sum).getCardBalance(idFirstCard);
        int expected = balanceBefore + sum;
        Assertions.assertEquals(expected, balanceAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstNegativSum() {
        var loginPage = new LoginPageV2();
        var transfer = new Transfer();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        int balanceBefore = verificationPage.validVerify(verificationCode).getCardBalance(idSecondCard);
        int sum = -1000;
        int balanceAfter = transfer.transferFromSecondToFirst(authInfo, sum).getCardBalance(idSecondCard);
        int expected = balanceBefore + sum;
        Assertions.assertEquals(expected, balanceAfter);
    }

}

