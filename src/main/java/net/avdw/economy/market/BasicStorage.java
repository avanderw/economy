package net.avdw.economy.market;

import net.avdw.economy.market.api.AStorage;
import net.avdw.economy.market.api.Good;

import java.util.HashMap;
import java.util.Map;

class BasicStorage implements AStorage {
    private Map<Good, Long> store = new HashMap<>();

    @Override
    public void store(Good good, Long quantity) {
        store.putIfAbsent(good, quantity);
    }

    @Override
    public long getQuantity(Good good) {
        return store.get(good);
    }

    @Override
    public void take(Good good, Long quantity) throws StorageException {
        if (store.containsKey(good)) {
            if (store.get(good) < quantity) {
                throw new StorageException();
            }

            store.put(good, store.get(good) - quantity);
        } else {
            throw new StorageException();
        }
    }

    @Override
    public void give(Good good, Long quantity) {
        store.computeIfPresent(good, (k, v) -> v + quantity);
    }
}
