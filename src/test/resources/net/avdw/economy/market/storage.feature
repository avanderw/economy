Feature: Storage
  The requirement is to manage a store of goods

  Scenario: Store
    A storage should store and retrieve goods

    Given a storage
    When I store a good
    Then I can retrieve the quantity of the good

  Scenario: Take
    Given a storage
    And I store a good
    When I take any quantity of the good
    Then the storage is reduced by the quantity taken

  Scenario: Give
    Given a storage
    And I store a good
    When I give any quantity of the good
    Then the storage is increased by the quantity given
