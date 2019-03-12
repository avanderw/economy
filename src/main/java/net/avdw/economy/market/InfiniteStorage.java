package net.avdw.economy.market;

import net.avdw.economy.market.api.AStorage;
import net.avdw.economy.market.api.Good;

public class InfiniteStorage implements AStorage {
    @Override
    public void store(Good good, Long quantity) {

    }

    @Override
    public long getQuantity(Good good) {
        return Long.MAX_VALUE;
    }

    @Override
    public void take(Good good, Long quantity) {

    }

    @Override
    public void give(Good good, Long quantity) {

    }
}
