package product;

import util.Currency;

public class DebitCard extends Card {

    public DebitCard(String name, Currency currency, double balance) {
        super(name, currency, balance);
        if (currency != Currency.RUB) {
            throw new IllegalArgumentException("Дебетовая карта должна иметь валюту RUB");
        }
    }

    @Override
    public void deposit(double amount){
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
