package com.sparta.FlockApp.steps;

import com.sparta.FlockApp.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SportsFeedSteps {

    @Managed
    HomePage homePage;

    // ==========================================
    // ONLY SPORTS FEED STEPS BELOW (NO LOGIN STEPS)
    // ==========================================

    @When("I click on the {string} category filter")
    public void i_click_on_the_category_filter(String category) {
        if (category.equalsIgnoreCase("Sports")) {
            homePage.clickSportsFilter();
        } else if (category.equalsIgnoreCase("All")) {
            homePage.clickAllFilter();
        }
    }

    @Then("the feed should display only cards with the {string} badge")
    public void the_feed_should_display_only_cards_with_the_badge(String badgeText) {
        assertThat(
                "All displayed cards should have the " + badgeText + " badge",
                homePage.areAllVisibleCardsTaggedWith(badgeText),
                is(true)
        );
    }

    @Given("the feed is filtered by {string}")
    public void the_feed_is_filtered_by(String category) {
        homePage.clickSportsFilter();
    }

    @Then("cards from multiple categories should be displayed")
    public void cards_from_multiple_categories_should_be_displayed() {
        assertThat(
                "Feed should show cards when set to All",
                homePage.getVisibleCardCount() > 0,
                is(true)
        );
    }

    @When("I enter {string} into the search bar")
    public void i_enter_into_the_search_bar(String searchQuery) {
        // Optional sad path step if you have the search bar scenario
        homePage.searchFor(searchQuery);
    }

    @Then("no event cards should be displayed")
    public void no_event_cards_should_be_displayed() {
        // Optional sad path assertion
        assertThat(
                "No event cards should be visible",
                homePage.getVisibleCardCount(),
                is(0)
        );
    }
}