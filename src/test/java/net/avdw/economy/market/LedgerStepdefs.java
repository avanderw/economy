package net.avdw.economy.market;

import cucumber.api.java8.En;
import net.avdw.economy.market.api.ALedger;
import net.avdw.economy.market.api.LedgerException;
import org.hamcrest.core.IsInstanceOf;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LedgerStepdefs implements En {
    private Long originalBalance;
    private ALedger ledger;
    private LedgerException lastException;

    public LedgerStepdefs() {
        Given("^any ledger$", () -> {
            ledger = new DebitLedger();
        });
        When("^I deposit into the ledger$", () -> {
            originalBalance = ledger.queryBalance();
            ledger.deposit(100L);
        });
        Then("^the ledger running balance will increase$", () -> {
            assertThat(ledger.queryBalance(), greaterThan(originalBalance));
        });
        Given("^any full ledger$", () -> {
            ledger = new DebitLedger(100L);
        });
        When("^I withdraw from the ledger$", () -> {
            originalBalance = ledger.queryBalance();
            try {
                ledger.withdraw(50L);
            } catch (LedgerException e) {
                lastException = e;
            }
        });
        Then("^the ledger running balance will decrease$", () -> {
            assertThat(ledger.queryBalance(), lessThan(originalBalance));
        });
        Given("^an empty debit ledger$", () -> {
            ledger = new DebitLedger();
        });
        Then("^the ledger will not allow the withdrawal$", () -> {
            assertThat(lastException, IsInstanceOf.any(LedgerException.class));
        });
        Then("^the ledger will audit the withdrawal$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^the ledger will audit the deposit$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^there will be multiple audits$", () -> {
            throw new UnsupportedOperationException();
        });
        Given("^an empty infinite ledger$", () -> ledger = new InfiniteLedger());
        Then("^the ledger will allow the transactions$", () -> assertThat(lastException, nullValue()));
    }
}
