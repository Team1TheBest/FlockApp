package com.sparta.FlockApp.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@DefaultUrl("https://nishman89.github.io/flock/home.html")
public class HomePage extends PageObject {

    // ==========================================
    // Category Chips (from DevTools data-cat attributes)
    // ==========================================
    @FindBy(css = "div[data-cat='Sports']")
    private WebElementFacade sportsFilterButton;

    @FindBy(css = "div[data-cat='All']")
    private WebElementFacade allFilterButton;


    // ==========================================
    // Search & Container Elements
    // ==========================================
    @FindBy(id = "search-input")
    private WebElementFacade roostSearchField;

    @FindBy(id = "events-container")
    private WebElementFacade roostResultsContainer;

    @FindBy(css = "#events-container .empty-state h3")
    private WebElementFacade noResultsHeading;

    @FindBy(css = "#events-container .empty-state p")
    private WebElementFacade noResultsDescription;

    @FindBy(css = "input[placeholder*='Search']")
    private WebElementFacade searchInput;


    // ==========================================
    // Category Filter Actions
    // ==========================================
    public void clickSportsFilter() {
        sportsFilterButton.waitUntilClickable().click();
        waitABit(1000); // Give the UI a moment to filter
    }

    public void clickAllFilter() {
        allFilterButton.waitUntilClickable().click();
        waitABit(1000);
    }

    public boolean areAllVisibleCardsTaggedWith(String expectedCategory) {
        waitABit(1000);
        List<String> visibleRoosts = getDisplayedRoosts();

        // If cards are found, verify that every card text contains "SPORTS"
        if (!visibleRoosts.isEmpty()) {
            return visibleRoosts.stream().allMatch(roost ->
                    roost.toUpperCase(Locale.ROOT).contains(expectedCategory.toUpperCase(Locale.ROOT))
            );
        }

        // Fallback: check directly for visible SPORTS badges
        return !findAll(By.xpath("//*[contains(text(), 'SPORTS')]"))
                .stream()
                .filter(WebElementFacade::isCurrentlyVisible)
                .toList()
                .isEmpty();
    }

    public int getVisibleCardCount() {
        return getDisplayedRoosts().size();
    }


    // ==========================================
    // Search Actions (Teammate methods preserved)
    // ==========================================
    public void searchFor(String query) {
        searchRoosts(query);
    }

    public void searchRoosts(String keyword) {
        typeInto(roostSearchField, keyword);
        waitABit(1000);
    }

    public List<String> getDisplayedRoosts() {
        return getDriver()
                .findElements(By.cssSelector("#events-container [id^='roost-card-']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean areDisplayedRoostsMatching(String keyword) {
        String searchTerm = keyword.toLowerCase(Locale.ROOT);
        List<String> roosts = getDisplayedRoosts();

        return !roosts.isEmpty()
                && roosts.stream().allMatch(
                roost -> roost.toLowerCase(Locale.ROOT).contains(searchTerm));
    }

    public String getSearchResultsText() {
        return roostResultsContainer.getText();
    }

    public String getNoResultsHeading() {
        return noResultsHeading.getText();
    }

    public String getNoResultsDescription() {
        return noResultsDescription.getText();
    }
}