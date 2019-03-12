package net.avdw.economy.market.api;

import net.avdw.economy.market.MarketException;

public interface AMarket {
    void register(Good good, Long quantity);
    void buyFrom(Good good, Long quantity) throws MarketException;
    void sellTo(Good good, Long quantity) throws MarketException;
    Long getPrice(Good good);
    Long getQuantity(Good good);
    Long costBulkPurchase(Good good, Long quantity);
    Long costBulkSale(Good good, Long quantity);
}
