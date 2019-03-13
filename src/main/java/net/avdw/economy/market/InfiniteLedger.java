package net.avdw.economy.market;

import net.avdw.economy.market.api.ALedger;
import net.avdw.economy.market.api.Audit;
import net.avdw.economy.market.api.LedgerException;

import java.util.HashSet;
import java.util.Set;

public class InfiniteLedger implements ALedger {
    private final Set<Audit> audits = new HashSet<>();
    @Override
    public long queryBalance() {
        return Long.MAX_VALUE;
    }

    @Override
    public void deposit(long amount) {

    }

    @Override
    public void withdraw(long l) {

    }

    @Override
    public Set<Audit> getAudits() {
        return audits;
    }
}
