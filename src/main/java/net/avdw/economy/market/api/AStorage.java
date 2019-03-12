package net.avdw.economy.market.api;

import net.avdw.economy.market.StorageException;

public interface AStorage {
    void store(Good good, Long quantity);
    long getQuantity(Good good);
    void take(Good good, Long quantity) throws StorageException;
    void give(Good good, Long quantity);
}
