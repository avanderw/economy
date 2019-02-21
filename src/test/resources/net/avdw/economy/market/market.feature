Feature: Market
  A place to sell and purchase goods

  Scenario: Register goods
    The market must be able to register goods for trade

    Given a market
    When I register a unit-elastic good for trade
    Then the market should have a current price for the good

  Scenario: Cost bulk purchase
    The market must be able to estimate a bulk purchase

    Given a market
    And I register a unit-elastic good for trade
    When I cost a bulk purchase
    Then the total price should be more than a the quantity x current price

  Scenario: Cost bulk sale
    The market must be able to estimate a bulk sale

    Given a market
    And I register a unit elastic good for trade
    When I cost a bulk sale
    Then the total price should be less than the quantity x current price

  Scenario: Purchase a good
    The market must be able to purchase goods

    Given a market
    And I register a unit-elastic good for trade
    When I purchase one good from the market
    Then the market quantity should decrease
    And the market price should increase

  Scenario: Sell a good
    The market must be able to sell goods

    Given a market
    And I register a unit-elastic good for trade
    When I sell one good to the market
    Then the market quantity should increase
    And the market price should decrease

  Scenario: Bulk purchase
    The market must respond to change in quantity

    Given a market
    And I register a unit-elastic good for trade
    When I purchase more than one of the good from the market
    Then the current price difference should be more than the quantity x original price

  Scenario: Bulk sale
    The market must respond to change in quantity

    Given a market
    And I register a unit-elastic good for trade
    When I sell more than one of the good to the market
    Then the current price difference should be less than the quantity x original price



