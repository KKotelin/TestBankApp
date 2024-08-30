package product;

import operations.ContributionAccountOperations;
import util.Currency;

public class Contribution extends Product implements ContributionAccountOperations {
    private double initialBalance;
    private int termInMonths;
    private double interestRate;
    private boolean isClosed;

    public Contribution(String name, Currency currency, double balance, int termInMonths, double interestRate) {
        super(name, currency, balance);
        this.initialBalance = balance;
        this.termInMonths = termInMonths;
        this.interestRate = interestRate;
        this.isClosed = false;
    }

    @Override
    public double getBalance() {
        if (isClosed) {
            return 0;
        } else {
            return balance;
        }
    }

    public void close() {
        if (isClosed) {
            throw new IllegalStateException("Вклад уже закрыт.");
        }
        balance = 0;
        isClosed = true;
    }

    @Override
    public void deposit(double amount) {
        if (!isClosed) {
            balance += amount;
        }
    }

    public double calculateMaturityAmount() {
        double monthlyRate = interestRate / 12;
        double maturityAmount = initialBalance * Math.pow(1 + monthlyRate, termInMonths);
        maturityAmount = Math.round(maturityAmount * 100.0) / 100.0;
        balance = maturityAmount;
        return balance;
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
