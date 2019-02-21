package net.avdw.economy.market;

import net.avdw.economy.market.api.Demand;

class DemandPriceCalculator {
    Long calculate(Demand demand, Long quantity) {
        if (demand.getElasticity() == 0)
            return Long.MAX_VALUE;

        if (demand.getElasticity() == Integer.MAX_VALUE)
            return demand.getZeroPrice();

        return Math.round(-(1 / (demand.getElasticity() / 100.)) * quantity + demand.getZeroPrice());
    }
}
