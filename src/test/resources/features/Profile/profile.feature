@profile
Feature: User profile
  As a Flock user
  I want to view and manage my profile
  So that my details and interests are up to date

  Background:
    Given I am on the FlockApp login page
    When I enter a valid email address
    And I ender a valid password
    And I click the login button
    Then I open the profile page


  @smoke
  Scenario: Profile Details
    Then I should see the profile name "Nish Mandal"
    And I should see the profile details "26 years old · London"
    And I should see the "✎ Edit profile" button

  Scenario: Open Edit Form
    When I click ✎ Edit profile
    Then the edit profile form should be displayed


  Scenario: Flocks and interests updates
    Then the Flocks joined stat should be 4 🎉
    And the Interests selected stat should be 7

  Scenario: Interests selected matches the highlighted interests
    Then the Interests selected stat should equal the number of selected interests

  # ---------- Preferences ----------

  Scenario: Current city is shown in the preferences
    Then the City preference should be "Edinburgh"

  Scenario: Changing the city updates the profile
    When I change the City preference to "Glasgow"
    Then the City preference should be "Glasgow"
    And the profile details should include "Glasgow"

  # ---------- My Interests ----------

  Scenario: Selected interests are highlighted
    Then the following interests should be selected:
      | Football      |
      | Gym & Fitness |
      | Food & Dining |
      | Pub & Social  |
      | Cinema        |
      | Swimming      |
      | Dancing       |

  Scenario Outline: Adding an interest
    Given the interest "<interest>" is not selected
    When I tap the interest "<interest>"
    Then the interest "<interest>" should be selected
    And the Interests selected stat should increase by 1

    Examples:
      | interest     |
      | Rugby        |
      | Running      |
      | Board Games  |