package net.avdw.economy.market;

import net.avdw.economy.market.api.AMarket;
import net.avdw.economy.market.api.Good;

class TaxedMarket implements AMarket {
    private final Storage storage;

    TaxedMarket(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void register(Good good, Long quantity) {
        storage.store(good, quantity);
    }

    @Override
    public Long getPrice(Good good) {
        throw new UnsupportedOperationException();
    }
}
