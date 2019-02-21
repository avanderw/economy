package net.avdw.economy.market;

import net.avdw.economy.market.api.AMarket;
import net.avdw.economy.market.api.Good;

class DemandMarket implements AMarket {
    private final Storage storage;
    private final DemandPriceCalculator calculator;

    DemandMarket(Storage storage, DemandPriceCalculator calculator) {
        this.storage = storage;
        this.calculator = calculator;
    }

    @Override
    public void register(Good good, Long quantity) {
        storage.store(good, quantity);
    }

    @Override
    public Long getPrice(Good good) {
        return calculator.calculate(good.getDemand(), storage.getQuantity(good));
    }
}
