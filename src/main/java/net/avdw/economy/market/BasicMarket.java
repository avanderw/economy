package net.avdw.economy.market;

import net.avdw.economy.market.api.AMarket;
import net.avdw.economy.market.api.AStorage;
import net.avdw.economy.market.api.Good;

class BasicMarket implements AMarket {
    private final AStorage storage;

    BasicMarket(AStorage storage) {
        this.storage = storage;
    }

    @Override
    public void register(Good good, Long quantity) {
        storage.store(good, quantity);
    }

    @Override
    public Long getPrice(Good good) {
        return good.getDemand().getZeroPrice();
    }

    @Override
    public Long getQuantity(Good good) {
        return storage.getQuantity(good);
    }

    @Override
    public void buyFrom(Good good, Long quantity) {
        storage.take(good, quantity);
    }

    @Override
    public void sellTo(Good good, Long quantity) {
        storage.give(good, quantity);
    }
}
