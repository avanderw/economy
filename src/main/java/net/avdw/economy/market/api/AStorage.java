package net.avdw.economy.market.api;

public interface AStorage {
    void store(Good good, Long quantity);
    long getQuantity(Good good);
    void take(Good good, Long quantity) throws StorageException;
    void give(Good good, Long quantity);
}
