package product;

import util.Currency;

public class CurrencyCard extends Card {

    public CurrencyCard(String name, Currency currency, double balance){
        super(name, currency, balance);
        if (currency == Currency.RUB) {
            throw new IllegalArgumentException("Инностранная карта должна иметь валюту USD или EUR");
        }
    }

    @Override
    public  void deposit(double amount){
        balance +=amount;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            throw new IllegalArgumentException("Недостаточно средств");
        }
    }
}