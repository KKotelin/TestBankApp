package product;

import util.Currency;

public class CurrencyCard extends Card {

    public CurrencyCard(String name, Currency currency, double balance){
        super(name, currency, balance);
        if (currency != Currency.EUR && currency != Currency.USD ) {
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
            System.out.println("Недостаточно средств. Баланс не может уйти в минус.");
            return false;
        }
    }
}
