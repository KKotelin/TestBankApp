package product;

import util.Currency;

public abstract class Card extends Product{

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