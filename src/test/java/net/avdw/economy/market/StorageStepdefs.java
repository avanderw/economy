package net.avdw.economy.market;

import cucumber.api.java8.En;
import net.avdw.economy.market.api.AStorage;
import net.avdw.economy.market.api.Demand;
import net.avdw.economy.market.api.Good;
import net.avdw.economy.market.api.StorageException;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StorageStepdefs implements En {
    private static final long ORIGINAL_QUANTITY = 100L;
    private final Random random = new Random();
    private final Good good = new Good("unit-elastic", new Demand(1000, 100));
    private AStorage storage;
    private Long quantity;
    private StorageException lastException;

    public StorageStepdefs() {
        Given("^a storage$", () -> storage = new BasicStorage());
        When("^I store a good$", () -> storage.store(good, ORIGINAL_QUANTITY));
        When("^I take any quantity of the good$", () -> {
            quantity = (long) (random.nextInt(9) + 1);
            try {
                storage.take(good, quantity);
            } catch (StorageException e) {
                lastException = e;
            }
        });
        When("^I give any quantity of the good$", () -> {
            quantity = (long) (random.nextInt(9) + 1);
            storage.give(good, quantity);
        });
        Then("^I can retrieve the quantity of the good$", () -> assertThat(storage.getQuantity(good), greaterThan(0L)));
        Then("^the storage is reduced by the quantity taken$", () -> assertThat(storage.getQuantity(good), equalTo(ORIGINAL_QUANTITY - quantity)));
        Then("^the storage is increased by the quantity given$", () -> assertThat(storage.getQuantity(good), equalTo(ORIGINAL_QUANTITY + quantity)));
        Given("^an infinite storage$", () -> storage = new InfiniteStorage());
        Then("^the storage allows it$", () -> assertThat(lastException, nullValue()));
        Then("^the storage must not allow it$", () -> assertThat(lastException, notNullValue()));
    }
}
