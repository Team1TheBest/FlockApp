Feature: Search for Roosts
  As a signed-in user
  I want to search Roosts by keyword
  So that I can find events relevant to me

  Background:
    Given I am on the FlockApp login page
    When I enter a valid email address
    And I enter a valid password
    And I click the login button
    Then I should be redirected to the home page

  @HappyPath
  Scenario: Find Roosts by keyword
    When I search for "Derby"
    Then I should see Roosts matching "Derby"

  @SadPath
  Scenario: Search with no matching results
    When I search for "no matching event"
    Then I should see an empty-results message