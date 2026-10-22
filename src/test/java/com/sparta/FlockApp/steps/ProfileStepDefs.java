package com.sparta.FlockApp.steps;

import com.sparta.FlockApp.pages.ProfilePage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;

@ExtendWith(SerenityJUnit5Extension.class)
public class ProfileStepDefs {
    @Managed
    private ProfilePage profilePage;

    @Then("I open the profile page")
    public void iOpenTheProfilePage() {
        profilePage.open();
    }

    @Then("I should see the profile name {string}")
    public void iShouldSeeTheProfileName(String arg0) {
        MatcherAssert.assertThat(profilePage.getProfileName(), Matchers.equalTo(arg0));
    }

    @And("I should see the profile details {string}")
    public void iShouldSeeTheProfileDetails(String arg0) {
        MatcherAssert.assertThat(profilePage.getProfileInfo(), Matchers.equalTo(arg0));
    }

    @And("I should see the {string} button")
    public void iShouldSeeTheButton(String arg0) {
        MatcherAssert.assertThat(profilePage.getEditButtonText(), Matchers.equalTo(arg0));
    }

    @When("I click ✎ Edit profile")
    public void iClickEditProfile() {
        profilePage.clickEditProfileButton();
    }

    @Then("the edit profile form should be displayed")
    public void theEditProfileFormShouldBeDisplayed() {
        MatcherAssert.assertThat(profilePage.isEditOverlayVisible(), Matchers.equalTo(true));
    }

    @Then("the Flocks joined stat should be {int} 🎉")
    public void theFlocksJoinedStatShouldBe(int arg0) {
        MatcherAssert.assertThat(profilePage.getFlocksJoinedTotal(), Matchers.equalTo(arg0));
    }

    @And("the Interests selected stat should be {int}")
    public void theInterestsSelectedStatShouldBe(int arg0) {
        MatcherAssert.assertThat(profilePage.getInterestsSelected(), Matchers.equalTo(arg0));
    }

    @Then("the Interests selected stat should equal the number of selected interests")
    public void theInterestsSelectedStatShouldEqualTheNumberOfSelectedInterests() {
        MatcherAssert.assertThat(profilePage.getInterestsSelected(), Matchers.equalTo(profilePage.getActiveInterests()));
    }

    @When("I change the City preference to {string}")
    public void iChangeTheCityPreferenceTo(String arg0) {
        profilePage.openPreferenceMenu();
        profilePage.selectNewCity(arg0);
    }

    @Then("the City preference should be {string}")
    public void theCityPreferenceShouldBe(String arg0) {
        MatcherAssert.assertThat(profilePage.getCurrentPreferredCity(), Matchers.equalTo(arg0));
    }


    @And("the profile details should include {string}")
    public void theProfileDetailsShouldInclude(String arg0) {
        MatcherAssert.assertThat(profilePage.getProfileInfo(), Matchers.containsString(arg0));
    }


    @Then("the following interests should be selected:")
    public void theFollowingInterestsShouldBeSelected(List<String> expectedInterests) {
        MatcherAssert.assertThat(profilePage.getSelectedInterestNames(),
                containsInAnyOrder(expectedInterests.toArray()));
    }

    @Given("the interest {string} is not selected")
    public void theInterestIsNotSelected(String arg0) {
        MatcherAssert.assertThat(profilePage.getSelectedInterestNames(),
                Matchers.not(containsInAnyOrder(arg0)));
    }

    @When("I tap the interest {string}")
    public void iTapTheInterest(String arg0) {
        profilePage.updateInterest(arg0);
    }

    @Then("the interest {string} should be selected")
    public void theInterestShouldBeSelected(String arg0) {
        MatcherAssert.assertThat(profilePage.getSelectedInterestNames(), Matchers.hasItem(arg0));
    }

    @And("the Interests selected stat should increase by {int}")
    public void theInterestsSelectedStatShouldIncreaseBy(int arg0) {
        MatcherAssert.assertThat(profilePage.getInterestsSelected(), Matchers.equalTo(8));
    }
}
