package net.avdw.economy.market;

import cucumber.api.java8.En;
import net.avdw.economy.market.api.Demand;

import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DemandStepdefs implements En {
    private static final DemandPriceCalculator calculator = new DemandPriceCalculator();
    private Long quantityChange;
    private Demand demand;

    public DemandStepdefs() {
        Given("^a demand is unit elastic$", () -> demand = new Demand(1000L, 100));
        Given("^a demand is inelastic$", () -> demand = new Demand(1000L, 50));
        Given("^a demand is elastic$", () -> demand = new Demand(1000L, 150));
        Given("^a demand is perfectly elastic$", () -> demand = new Demand(1000L, Integer.MAX_VALUE));
        Given("^a demand is perfectly inelastic$", () -> demand = new Demand(1000L, 0));
        When("^the quantity changes by any amount$", () -> quantityChange = (long) (new Random().nextInt(19) + 1));
        Then("^the price remains constant$", () -> {
            Long initialPrice = calculator.calculate(demand, 100L);
            Long priceAfterBuy = calculator.calculate(demand, 100L + quantityChange);
            Long priceAfterSell = calculator.calculate(demand, 100L - quantityChange);

            assertThat(priceAfterBuy, is(initialPrice));
            assertThat(priceAfterSell, is(initialPrice));
        });
        Then("^the price change is equivalent to the quantity change$", () -> {
            Long initialPrice = calculator.calculate(demand, 100L);
            Long priceAfterBuy = calculator.calculate(demand, 100L + quantityChange);
            Long priceAfterSell = calculator.calculate(demand, 100L - quantityChange);

            assertThat(priceChange(initialPrice, priceAfterBuy), is(quantityChange));
            assertThat(priceChange(initialPrice, priceAfterSell), is(quantityChange));
        });
        Then("^the price change is more than the quantity change$", () -> {
            Long initialPrice = calculator.calculate(demand, 100L);
            Long priceAfterBuy = calculator.calculate(demand, 100L + quantityChange);
            Long priceAfterSell = calculator.calculate(demand, 100L - quantityChange);

            assertThat(priceChange(initialPrice, priceAfterBuy), greaterThan(quantityChange));
            assertThat(priceChange(initialPrice, priceAfterSell), greaterThan(quantityChange));
        });
        Then("^the price change is less than the quantity change$", () -> {
            Long initialPrice = calculator.calculate(demand, 100L);
            Long priceAfterBuy = calculator.calculate(demand, 100L + quantityChange);
            Long priceAfterSell = calculator.calculate(demand, 100L - quantityChange);

            assertThat(priceChange(initialPrice, priceAfterBuy), lessThan(quantityChange));
            assertThat(priceChange(initialPrice, priceAfterSell), lessThan(quantityChange));
        });
        Then("^the price is infinite$", () -> {
            Long initialPrice = calculator.calculate(demand, 100L);
            Long priceAfterBuy = calculator.calculate(demand, 100L + quantityChange);
            Long priceAfterSell = calculator.calculate(demand, 100L - quantityChange);

            assertThat(initialPrice, is(Long.MAX_VALUE));
            assertThat(priceAfterBuy, is(initialPrice));
            assertThat(priceAfterSell, is(initialPrice));
        });
    }

    private Long priceChange(Long p1, Long p2) {
        return Math.abs(p1 - p2);
    }
}


