Feature: Filter Sports Feed on Home Page
  As a sports enthusiast using Flock
  I want to filter the home feed by the "Sports" category
  So that I can view only sports events and meetups

  Background:
    Given I am on the FlockApp login page
    When I enter a valid email address
    And I ender a valid password
    And I click the login button


  # HAPPY PATHS
  @Happy @Smoke
  Scenario: Successfully filter the home feed by the Sports category
    When I click on the "Sports" category filter
    Then the feed should display only cards with the "SPORTS" badge

  @Happy
  Scenario: Switch back to all categories from the sports filter
    Given the feed is filtered by "Sports"
    When I click on the "All" category filter
    Then cards from multiple categories should be displayed


  # SAD

  @Sad
  Scenario: No sports events match a search query
    Given the feed is filtered by "Sports"
    When I enter "NonExistentSport123" into the search bar
    Then no event cards should be displayed