package product;

import operations.BalanceInquiry;
import operations.DepositOperation;
import operations.WithdrawOperation;
import util.Currency;

public abstract class Card extends Product implements DepositOperation, WithdrawOperation, BalanceInquiry {

    public Card(String name, Currency currency, double balance) {
        super(name, currency, balance);
    }

    public abstract void deposit(double amount);

    public abstract boolean withdraw(double amount);

    @Override
    public double getBalance() {
        return balance;
    }
}
