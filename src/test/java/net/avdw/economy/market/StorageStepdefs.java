package net.avdw.economy.market;

import cucumber.api.java8.En;
import net.avdw.economy.market.api.Demand;
import net.avdw.economy.market.api.Good;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class StorageStepdefs implements En {
    private static final long ORIGINAL_QUANTITY = 100L;
    private final Random random = new Random();
    private final Good good = new Good("unit-elastic", new Demand(1000, 100));
    private BasicStorage storage;
    private Long quantity;

    public StorageStepdefs() {
        Given("^a storage$", () -> storage = new BasicStorage());
        When("^I store a good$", () -> storage.store(good, ORIGINAL_QUANTITY));
        When("^I take any quantity of the good$", () -> {
            quantity = (long) (random.nextInt(9) + 1);
            storage.take(good, quantity);
        });
        When("^I give any quantity of the good$", () -> {
            quantity = (long) (random.nextInt(9) + 1);
            storage.give(good, quantity);
        });
        Then("^I can retrieve the quantity of the good$", () -> assertThat(storage.getQuantity(good), greaterThan(0L)));
        Then("^the storage is reduced by the quantity taken$", () -> assertThat(storage.getQuantity(good), equalTo(ORIGINAL_QUANTITY - quantity)));
        Then("^the storage is increased by the quantity given$", () -> assertThat(storage.getQuantity(good), equalTo(ORIGINAL_QUANTITY + quantity)));
    }
}
