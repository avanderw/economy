package net.avdw.economy.market;

import net.avdw.economy.market.api.AMarket;
import net.avdw.economy.market.api.Good;
import net.avdw.economy.market.api.MarketException;
import net.avdw.economy.market.api.StorageException;

class TaxedMarket implements AMarket {
    private final BasicStorage storage;

    TaxedMarket(BasicStorage storage) {
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

    @Override
    public Long getQuantity(Good good) {
        return storage.getQuantity(good);
    }

    @Override
    public Long costBulkPurchase(Good good, Long quantity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long costBulkSale(Good good, Long quantity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void buyFrom(Good good, Long quantity) throws MarketException {
        try {
            storage.take(good, quantity);
        } catch (StorageException e) {
            throw new MarketException();
        }
    }

    @Override
    public void sellTo(Good good, Long quantity) {
        storage.give(good, quantity);
    }
}
