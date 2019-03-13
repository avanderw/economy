package net.avdw.economy.market;

import net.avdw.economy.market.api.ALedger;
import net.avdw.economy.market.api.Audit;
import net.avdw.economy.market.api.LedgerException;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

class DebitLedger implements ALedger {
    private final Set<Audit> audits = new HashSet<>();
    private Long balance = 0L;

    DebitLedger(){}

    DebitLedger(long balance) {
        this.balance = balance;
    }

    public void deposit(long amount) {
        balance += amount;
        audits.add(new Audit(new Date(), amount));
    }

    public long queryBalance() {
        return balance;
    }

    public void withdraw(long amount) throws LedgerException {
        if (balance <  amount) {
            throw new LedgerException();
        }

        balance -= amount;
        audits.add(new Audit(new Date(), -amount));
    }

    @Override
    public Set<Audit> getAudits() {
        return audits;
    }
}
