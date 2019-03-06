package net.avdw.economy.market;

import cucumber.api.java8.En;
import net.avdw.economy.market.api.AMarket;
import net.avdw.economy.market.api.Demand;
import net.avdw.economy.market.api.Good;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MarketStepdefs implements En {
    private static final long ORIGINAL_QUANTITY = 100L;
    private static final long BULK_QUANTITY = 10L;
    private final Random random = new Random();
    private final Long randomQuantity = (long) (random.nextInt(8) + 2);
    private final List<AMarket> allMarkets = new ArrayList<>();
    private final Map<AMarket, Long> originalPrices = new HashMap<>();
    private final Good good = new Good("unit-elastic", new Demand(1000, 100));
    private Long initialQuantity;
    private Long bulkPrice;
    private AMarket market;

    public MarketStepdefs() {
        Given("^a demand market$", () -> market = new DemandMarket(new BasicStorage(), new DemandPriceCalculator()));
        Given("^all the markets$", () -> {
            allMarkets.add(new BasicMarket(new BasicStorage()));
            allMarkets.add(new DemandMarket(new BasicStorage(), new DemandPriceCalculator()));
//            allMarkets.add(new ProfitMarket(new Storage()));
//            allMarkets.add(new TaxedMarket(new Storage()));
        });
        Given("^a market with no ledger$", () -> {
            throw new UnsupportedOperationException();
        });
        Given("^a market with a ledger that has no balance$", () -> {
            throw new UnsupportedOperationException();
        });
        Given("^a market with a ledger that has a positive balance$", () -> {
            throw new UnsupportedOperationException();
        });
        And("^I register a unit-elastic good for trade$", () -> market.register(good, ORIGINAL_QUANTITY));
        And("^the market price should increase$", () -> {
            throw new UnsupportedOperationException();
        });
        And("^the market price should decrease$", () -> {
            throw new UnsupportedOperationException();
        });
        And("^all the markets price, except basic, should increase$", () ->
                allMarkets.forEach(m -> {
                    Long currentPrice = m.getPrice(good);
                    Long originalPrice = originalPrices.get(m);
                    if (!(m instanceof BasicMarket)) {
                        assertThat(currentPrice, greaterThan(originalPrice));
                    }
                }));
        And("^all the markets price, except basic, should decrease$", () ->
                allMarkets.forEach(m -> {
                    Long currentPrice = m.getPrice(good);
                    Long originalPrice = originalPrices.get(m);
                    if (!(m instanceof BasicMarket)) {
                        assertThat(currentPrice, lessThan(originalPrice));
                    }
                }));
        And("^the running balance will decrease$", () -> {
            throw new UnsupportedOperationException();
        });
        When("^I cost a bulk purchase$", () -> {
            initialQuantity = market.getQuantity(good);
            bulkPrice = market.costBulkPurchase(good, BULK_QUANTITY);
        });
        When("^I cost a bulk sale$", () -> {
            initialQuantity = market.getQuantity(good);
            bulkPrice = market.costBulkSale(good, BULK_QUANTITY);
        });
        When("^I purchase one good from the market$", () -> market.buyFrom(good, 1L));
        When("^I sell one good to the market$", () -> market.sellTo(good, 1L));
        When("^I purchase more than one of the good from the market$", () -> allMarkets.forEach(m -> m.buyFrom(good, randomQuantity)));
        When("^I sell more than one of the good to the market$", () -> allMarkets.forEach(m -> m.sellTo(good, randomQuantity)));
        When("^I purchase one good from all the markets$", () -> allMarkets.forEach(m -> m.buyFrom(good, 1L)));
        When("^I register a unit-elastic good for trade on all the markets$", () -> allMarkets.forEach(m -> {
            m.register(good, ORIGINAL_QUANTITY);
            originalPrices.putIfAbsent(m, m.getPrice(good));
        }));
        When("^I sell one good to all the markets$", () -> allMarkets.forEach(m -> m.sellTo(good, 1L)));
        When("^I try to sell it goods$", () -> {
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
        Then("^the bulk price should be less than the sold quantity x current price$", () -> assertThat(bulkPrice, lessThan(BULK_QUANTITY * market.getPrice(good))));
        Then("^the market should have a current price for the good$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^the bulk price should be more than a the purchased quantity x current price$", () -> assertThat(bulkPrice, greaterThan(BULK_QUANTITY * market.getPrice(good))));
        Then("^all the markets should have a current price for the good$", () -> allMarkets.forEach(m -> assertThat(m.getPrice(good), greaterThan(0L))));
        Then("^all the markets quantity should decrease$", () -> allMarkets.forEach(m -> assertThat(m.getQuantity(good), lessThan(ORIGINAL_QUANTITY))));
        Then("^all the markets quantity should increase$", () -> allMarkets.forEach(m -> assertThat(m.getQuantity(good), greaterThan(ORIGINAL_QUANTITY))));
        Then("^it will be able to purchase them$", () -> {
            throw new UnsupportedOperationException();
        });
        Then("^it will not be able to purchase them$", () -> {
            throw new UnsupportedOperationException();
        });
        And("^the quantity in the market should not change$", () -> assertThat(market.getQuantity(good), equalTo(initialQuantity)));
    }
}
