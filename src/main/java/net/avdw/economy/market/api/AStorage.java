package net.avdw.economy.market.api;

import net.avdw.economy.market.api.Good;

public interface AStorage {
    void store(Good good, Long quantity);
    Long getQuantity(Good good);
    void take(Good good, Long quantity);
    void give(Good good, Long quantity);
}
