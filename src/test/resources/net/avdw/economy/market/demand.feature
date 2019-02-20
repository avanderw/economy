Feature: Demand
  demands in a market have a demand associated with them

  Scenario: Unit elasticity
    Q changes like P

    Given a demand is unit elastic
    When the quantity changes by any amount
    Then the price change is equivalent to the quantity change

  Scenario: Inelastic P-demand
    Q changes less than P

    Given a demand is inelastic
    When the quantity changes by any amount
    Then the price change is more than the quantity change

  Scenario: Elastic P-demand
    Q changes more than P

    Given a demand is elastic
    When the quantity changes by any amount
    Then the price change is less than the quantity change

  Scenario: Perfect P-elasticity
    Q changes while P = constant

    Given a demand is perfectly elastic
    When the quantity changes by any amount
    Then the price remains constant

  Scenario: Perfect P-inelasticity
    P changes while Q = constant

    Given a demand is perfectly inelastic
    When the quantity changes by any amount
    Then an error should be recorded
