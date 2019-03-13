Feature: Market
  A place to sell and purchase goods

  Scenario: Register goods
    The market must be able to register goods for trade

    Given all the markets
    When I register a unit-elastic good for trade on all the markets
    Then all the markets should have a current price for the good

  Scenario: Purchase a good
  The market must be able to purchase goods

    Given all the markets
    And I register a unit-elastic good for trade on all the markets
    When I purchase one good from all the markets
    Then all the markets quantity should decrease
    And all the markets price, except basic, should increase

  Scenario: Sell a good
  The market must be able to sell goods

    Given all the markets
    And I register a unit-elastic good for trade on all the markets
    When I sell one good to all the markets
    Then all the markets quantity should increase
    And all the markets price, except basic, should decrease

  Scenario: Cost a demand adjusted bulk purchase
    The market must be able to estimate a bulk purchase

    Given a demand market
    And I register a unit-elastic good for trade
    When I cost a bulk purchase
    Then the bulk price should be more than a the purchased quantity x current price
    And the quantity in the market should not change

  Scenario: Cost a demand adjusted bulk sale
    The market must be able to estimate a bulk sale

    Given a demand market
    And I register a unit-elastic good for trade
    When I cost a bulk sale
    Then the bulk price should be less than the sold quantity x current price
    And the quantity in the market should not change

  Scenario: Demand adjusted bulk purchase
    The market must respond to change in quantity

    Given a demand market
    And I register a unit-elastic good for trade
    When I purchase more than one of the good from the market
    Then the quantity in the market should reduce by more than one

  Scenario: Demand adjusted bulk sale
    The market must respond to change in quantity

    Given a demand market
    And I register a unit-elastic good for trade
    When I sell more than one of the good to the market
    Then the quantity in the market should increase by more than one

  Scenario: Market with no ledger
    A market with no ledger will always be able to transact

    Given a market with no ledger
    When I try to sell the market goods
    Then the market will be able to purchase them

  Scenario: Market with an empty ledger
    If the market has an empty ledger then it has restrictions

    Given a market with a ledger that has no balance
    When I try to sell the market goods
    Then the market will not be able to purchase them

  Scenario: Market with a full ledger
    If the market has a ledger with money in it

    Given a market with a ledger that has a positive balance
    When I try to sell the market goods
    Then the market will be able to purchase them

  Scenario: Profit market
    The market should record a profit when making a sale

    Given a profit market
    When I sell one good to the market
    And I purchase one good from the market
    Then the market must record a profit

  Scenario: Taxed Market
    The market should be taxed when making a profit

    Given a tax market
    When I sell one good to the market
    And I purchase one good from the market
    Then the market must record the tax
    And the profit should be the same as a profit market




