Feature: Product Count Test
  This is a test for the product count on the flower products page.

  Scenario: Checking Count of Products on the Page
    Given I go to "https://www.mizu.com/flowers"
    When I close the address focus on the page
    Then Checking that 60 products came on each page for the first 10 pages
