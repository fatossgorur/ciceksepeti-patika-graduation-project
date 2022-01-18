Feature: Price Order Test
  This is a test for the correct price sort on the flower products page.

  Scenario: Checking Correct Price Sort with Filtering
    Given I go to "https://www.mizu.com/flowers"
    When I close the address focus on the page
    And Click "Sort" from filter field
    And Select "Price: High to Low" from sort filter field
    Then Checking that the products are listed in the correct price sort
