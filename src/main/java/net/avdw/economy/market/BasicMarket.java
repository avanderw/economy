package net.avdw.economy.market;

import net.avdw.economy.market.api.*;

class BasicMarket implements AMarket {
    private final AStorage storage;
    private final ALedger ledger;

    BasicMarket(AStorage storage) {
        this.storage = storage;
        this.ledger = new InfiniteLedger();
    }

    BasicMarket(BasicStorage storage, ALedger ledger) {
        this.storage = storage;
        this.ledger = ledger;
    }

    BasicMarket(ALedger ledger) {
        this.storage = new InfiniteStorage();
        this.ledger = ledger;
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
    public Long costBulkPurchase(Good good, Long quantity) {
        return getPrice(good) * quantity;
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
    public void sellTo(Good good, Long quantity) throws MarketException {
        if (ledger.queryBalance() < costBulkPurchase(good, quantity)) {
            throw new MarketException();
        }
        storage.give(good, quantity);
    }
}
