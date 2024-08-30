package product;

import util.Currency;

public abstract class Product {
    protected String name;
    protected String currency;
    protected double balance;

    public Product(String name, Currency currency, double balance) {
        this.name = name;
        this.currency = String.valueOf(currency);
        this.balance = balance;
    }

    public abstract double getBalance();

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }
}
