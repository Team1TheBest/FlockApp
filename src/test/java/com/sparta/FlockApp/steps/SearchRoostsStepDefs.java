package com.sparta.FlockApp.steps;

import com.sparta.FlockApp.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SerenityJUnit5Extension.class)
public class SearchRoostsStepDefs {

    @Managed
    private HomePage homePage;

    @When("I search for {string}")
    public void iSearchFor(String keyword) {
        homePage.searchRoosts(keyword);
    }

    @Then("I should see Roosts matching {string}")
    public void iShouldSeeRoostsMatching(String keyword) {
        assertTrue(
                homePage.areDisplayedRoostsMatching(keyword),
                "Expected the displayed Roosts to match: " + keyword
        );
    }

    @Then("I should see an empty-results message")
    public void iShouldSeeAnEmptyResultsMessage() {
        assertTrue(
                homePage.getDisplayedRoosts().isEmpty(),
                "Expected no Roost cards for a search with no matches"
        );
        assertEquals("No Roosts found", homePage.getNoResultsHeading());
        assertEquals(
                "Try a different city or category.",
                homePage.getNoResultsDescription()
        );
    }
}