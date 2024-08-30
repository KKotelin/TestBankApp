package product;

import util.Currency;

public abstract class Contribution extends Product {
    protected double initialBalance;
    protected int termInMonths;
    protected double interestRate;
    protected boolean isClosed;

    public Contribution(String name, Currency currency, double balance, int termInMonths, double interestRate) {
        super(name, currency, balance);
        this.initialBalance = balance;
        this.termInMonths = termInMonths;
        this.interestRate = interestRate;
        this.isClosed = false;
    }

    @Override
    public double getBalance() {
        return isClosed ? 0 : balance;
    }

    public abstract void deposit(double amount);

    public abstract double calculateMaturityAmount();

    public abstract double updateBalance();

    public void close() {
        if (isClosed) {
            throw new IllegalStateException("Вклад уже закрыт.");
        }
        balance = 0;
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
