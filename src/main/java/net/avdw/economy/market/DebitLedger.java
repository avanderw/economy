package net.avdw.economy.market;

class DebitLedger {
    private Long balance = 0L;

    DebitLedger(){}

    DebitLedger(long balance) {
        this.balance = balance;
    }

    void deposit(long amount) {
        balance += amount;
    }

    Long queryBalance() {
        return balance;
    }

    void withdraw(long amount) throws LedgerException {
        if (balance <  amount) {
            throw new LedgerException();
        }
        balance -= amount;
    }
}
