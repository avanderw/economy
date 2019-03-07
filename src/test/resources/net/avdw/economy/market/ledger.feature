Feature: Ledger
  Transactions should be tracked to maintain a running balance

Scenario: Deposit with any ledger
  Given any ledger
  When I deposit into the ledger
  Then the ledger running balance will increase

Scenario: Withdraw from full ledger
  Given a full ledger
  When I withdraw from the ledger
  Then the ledger running balance will decrease

Scenario: Decrease with empty ledger
  Given an empty ledger
  When I withdraw from the ledger
  Then the ledger will not allow the withdrawal

Scenario: Audit withdrawal
  Given a full ledger
  When I withdraw from the ledger
  Then the ledger will audit the withdrawal

Scenario: Audit deposit
  Given any ledger
  When I deposit into the ledger
  Then the ledger will audit the deposit

Scenario: Keep multiple audits
  Given any ledger
  When I deposit into the ledger
  And I withdraw from the ledger
  Then there will be multiple audits