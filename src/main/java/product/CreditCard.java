package product;

import util.Currency;

public class CreditCard extends Card{
    private double interestRate;
    private double initialBalance;

    public CreditCard(String name, Currency currency, double balance, double interestRate) {
        super(name, currency, balance);
        if (currency != Currency.RUB) {
            throw new IllegalArgumentException("Кредитная карта должна иметь валюту RUB");
        }
        this.interestRate = interestRate;
        this.initialBalance = balance;
    }

    @Override
    public  void deposit(double amount){
        balance +=amount;
    }

    @Override
    public boolean withdraw(double amount) {
        balance -= amount;
        return true;
    }

    public double getDebt() {
        return initialBalance > balance ? initialBalance - balance : 0;
    }

    public double getInterestRate() {
        return interestRate;
    }
}