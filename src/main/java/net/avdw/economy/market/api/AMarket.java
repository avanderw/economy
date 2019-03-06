package net.avdw.economy.market.api;

public interface AMarket {
    void register(Good good, Long quantity);
    void buyFrom(Good good, Long quantity);
    void sellTo(Good good, Long quantity);
    Long getPrice(Good good);
    Long getQuantity(Good good);
    Long costBulkPurchase(Good good, Long quantity);
    Long costBulkSale(Good good, Long quantity);
}
