package pages.lastCallsPage;

import com.codeborne.selenide.SelenideElement;

public class OutgoingCallsTab extends LastCallsPage {
    @Override
    public SelenideElement getFieldDestinationNumber(String name) {
        return super.getFieldDestinationNumber(name);
    }
}
