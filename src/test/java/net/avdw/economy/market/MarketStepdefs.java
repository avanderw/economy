package net.avdw.economy.market;

import cucumber.api.java8.En;
import net.avdw.economy.market.api.AMarket;
import net.avdw.economy.market.api.Demand;
import net.avdw.economy.market.api.Good;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class MarketStepdefs implements En {
    private final List<AMarket> allMarkets = new ArrayList<>();
    private final Good good = new Good("unit-elastic", new Demand(1000, 100));
    private AMarket market;

    public MarketStepdefs() {
        Given("^a demand market$", () -> market = new DemandMarket(new Storage(), new DemandPriceCalculator()));
        Given("^all the markets$", () -> {
            allMarkets.add(new BasicMarket(new Storage()));
            allMarkets.add(new DemandMarket(new Storage(), new DemandPriceCalculator()));
//            allMarkets.add(new ProfitMarket(new Storage()));
//            allMarkets.add(new TaxedMarket(new Storage()));
        });
        And("^I register a unit-elastic good for trade$", () -> market.register(good, 100L));
        And("^the market price should increase$", () -> {
            throw new UnsupportedOperationException();
        });
        And("^the market price should decrease$", () -> {
            throw new UnsupportedOperationException();
        });
        And("^all the markets price should increase$", () -> {
            throw new UnsupportedOperationException();
        });
        And("^all the markets price should decrease$", () -> {
            throw new UnsupportedOperationException();
        });
        When("^I cost a bulk purchase$", () -> {
            throw new UnsupportedOperationException();
        });
        When("^I cost a bulk sale$", () -> {
            throw new UnsupportedOperationException();
        });
        When("^I purchase one good from the market$", () -> {
            throw new UnsupportedOperationException();
        });
        When("^I sell one good to the market$", () -> {
            throw new UnsupportedOperationException();
        });
        When("^I purchase more than one of the good from the market$", () -> {
            throw new UnsupportedOperationException();
        });
        When("^I sell more than one of the good to the market$", () -> {
            throw new UnsupportedOperationException();
        });
        When("^I purchase one good from all the markets$", () -> {
            throw new UnsupportedOperationException();
        });
        When("^I register a unit-elastic good for trade on all the markets$", () -> allMarkets.forEach(m -> m.register(good, 100L)));
        When("^I sell one good to all the markets$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^the current price difference should be less than the quantity x original price$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^the current price difference should be more than the quantity x original price$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^the market quantity should increase$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^the market quantity should decrease$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^the total price should be less than the quantity x current price$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^the market should have a current price for the good$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^the total price should be more than a the quantity x current price$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^all the markets should have a current price for the good$", () -> allMarkets.forEach(m -> assertThat(m.getPrice(good), greaterThan(0L))));
        Then("^all the markets quantity should decrease$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^all the markets quantity should increase$", () -> {
            throw new UnsupportedOperationException();
        });
    }
}
