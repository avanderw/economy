package net.avdw.economy.market.api;

public interface ALedger {
    long queryBalance();

    void deposit(long amount);

    void withdraw(long l) throws LedgerException;
}
