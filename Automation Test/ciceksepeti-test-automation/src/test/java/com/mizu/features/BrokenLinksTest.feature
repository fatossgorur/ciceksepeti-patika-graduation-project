Feature: Broken Links Test
  This is a test for home pages

  Scenario: Broken Links Validation
    Given I go to home page
    When I close the address focus on the page
    Then I verify broken link of sub-categories under the main menu
