package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class Transfer {
    private SelenideElement transferButtonToFirst = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button");
    private SelenideElement transferButtonToSecond = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button");
    private SelenideElement transferSum = $("[data-test-id=amount] input");
    private SelenideElement transferFrom = $("[data-test-id=from] input");
    private SelenideElement actionTransfer = $("[data-test-id=action-transfer]");

    public DashboardPage transferFromFistToSecond(DataHelper.AuthInfo info, int sum) {

        transferButtonToSecond.click();
        transferSum.setValue(String.valueOf(sum));
        transferFrom.setValue(info.getAccountNumberOne());
        actionTransfer.click();
        return new DashboardPage();
    }

    public DashboardPage transferFromSecondToFirst(DataHelper.AuthInfo info, int sum) {
        transferButtonToFirst.click();
        transferSum.setValue(String.valueOf(sum));
        transferFrom.setValue(info.getAccountNumberTwo());
        actionTransfer.click();
        return new DashboardPage();
    }

}
