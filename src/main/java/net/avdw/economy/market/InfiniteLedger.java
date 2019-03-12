package net.avdw.economy.market;

import net.avdw.economy.market.api.ALedger;
import net.avdw.economy.market.api.LedgerException;

public class InfiniteLedger implements ALedger {
    @Override
    public long queryBalance() {
        return Long.MAX_VALUE;
    }

    @Override
    public void deposit(long amount) {

    }

    @Override
    public void withdraw(long l) throws LedgerException {

    }
}
