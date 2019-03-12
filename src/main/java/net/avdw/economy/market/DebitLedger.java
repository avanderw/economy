package net.avdw.economy.market;

import net.avdw.economy.market.api.ALedger;
import net.avdw.economy.market.api.LedgerException;

class DebitLedger implements ALedger {
    private Long balance = 0L;

    DebitLedger(){}

    DebitLedger(long balance) {
        this.balance = balance;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public long queryBalance() {
        return balance;
    }

    public void withdraw(long amount) throws LedgerException {
        if (balance <  amount) {
            throw new LedgerException();
        }
        balance -= amount;
    }
}
