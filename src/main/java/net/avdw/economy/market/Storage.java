package net.avdw.economy.market;

import net.avdw.economy.market.api.Good;

import java.util.HashMap;
import java.util.Map;

class Storage {
    private Map<Good, Long> store = new HashMap<>();

    void store(Good good, Long quantity) {
        store.putIfAbsent(good, quantity);
    }

    Long getQuantity(Good good) {
        return store.get(good);
    }

    void take(Good good, Long quantity) {
        store.computeIfPresent(good, (k, v) -> v - quantity);
    }

    void give(Good good, Long quantity) {
        store.computeIfPresent(good, (k, v) -> v + quantity);
    }
}
