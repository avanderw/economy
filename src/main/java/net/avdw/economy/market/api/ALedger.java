package net.avdw.economy.market.api;

import java.util.Set;

public interface ALedger {
    long queryBalance();

    void deposit(long amount);

    void withdraw(long l) throws LedgerException;

    Set<Audit> getAudits();
}
