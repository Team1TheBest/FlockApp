package com.sparta.FlockApp.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

@DefaultUrl("https://nishman89.github.io/flock/profile.html")
public class ProfilePage extends PageObject {

    @FindBy(className = "profile-name")
    private WebElementFacade profileName;

    @FindBy(className = "profile-sub")
    private WebElementFacade profileInfo;

    @FindBy(className = "profile-edit-btn")
    private WebElementFacade editProfileButton;

    @FindBy(className = "edit-profile-overlay")
    private WebElementFacade editProfileOverlay;

    @FindBy(xpath = "//*[@id='profile-body']/div[1]/div[2]/div/span[2]")
    private List<WebElementFacade> profileValues;

    @FindBy(className = "inline-select")
    private WebElementFacade preferenceMenu;

    @FindBy(className = "inline-chips-wrap")
    private WebElementFacade chipsWrap;

    @FindBy(css = ".inline-chip")
    private List<WebElementFacade> allInterests;

    @FindBy(css = ".inline-chip.active")
    private List<WebElementFacade> selectedInterests;

    @FindBy(id= "signout-btn")
    private WebElementFacade signOutButton;



    public String getProfileName() {
        return profileName.getText();
    }
    public String getProfileInfo() {
        return profileInfo.getText();
    }

    public int getFlocksJoinedTotal() {
        var out = profileValues.getFirst().getText().replace(" \uD83C\uDF89", "");
        return Integer.parseInt(out);
    }

    public int getInterestsSelected() {
        return Integer.parseInt(profileValues.get(1).getText());
    }

    public void openPreferenceMenu() {
        preferenceMenu.click();
    }

    public void updatePreference() {
        preferenceMenu.thenFind("//*[@id='profile-body']/div[2]/div[2]/div/div/select/option[4]").click();
    }

    public String getEditButtonText() {
        return editProfileButton.getText();
    }

    public boolean isEditOverlayVisible() {
        return editProfileOverlay.isVisible();
    }

    public void clickEditProfileButton() {
        editProfileButton.click();
    }

    public void clickSignOutButton() {
        signOutButton.click();
    }

    public int getActiveInterests() {
        List <WebElementFacade> interestBadges = chipsWrap.thenFindAll(By.cssSelector(".inline-chip.active"));
        var amount = 0;
        for (WebElementFacade interestBadge : interestBadges) {
            amount += 1;
        }
        return amount;
    }

    public String getCurrentPreferredCity() {
        return preferenceMenu.getSelectedValue();
    }

    public List<String> getAvailableCities() {
        return preferenceMenu.getSelectOptions();
    }

    public void selectNewCity(String city) {
        preferenceMenu.selectByVisibleText(city);
    }

    public List<String> getSelectedInterestNames() {
        List<String> names = new ArrayList<>();
        for (WebElementFacade chip : selectedInterests) {
            String name = chip.getText().replaceAll("[^\\p{L}\\p{N}& ]", "").trim();
            names.add(name);
        }
        return names;
    }

    public void updateInterest(String interest) {
        for (WebElementFacade interestBadge : allInterests) {
            if (interestBadge.getText().contains(interest)) {
                interestBadge.click();
                return;
            }
        }
    }
}
