Feature: Assignment

  Scenario: Add a box meal to the basket
    Given user navigates to homepage
    And user navigates to menu item "Find A KFC"
    When user searches for "cobham" location
    Then "cobham" location page is displayed
    When user chooses option for order collection
    When user navigates to Box Meals menu item
    And user adds "Trilogy Box Meal" product to the basket
    Then "Continue with my order" button is displayed to the user
    And when user adds to the order and views the order
    Then "1 x Trilogy Box Meal" is selected under the order