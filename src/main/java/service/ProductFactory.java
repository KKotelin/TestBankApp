package service;

import model.Account;
import product.Card;
import product.Contribution;
import product.CreditCard;
import product.CurrencyCard;
import product.DebitCard;
import util.CardType;
import util.Currency;

public class ProductFactory {

    public static Card createDebitAndCurrencyCard(CardType type, String name, Currency currency, double balance, Account account) throws IllegalAccessException {
        Card card;
        switch (type) {
            case DEBIT_CARD:
                card = new DebitCard(name, currency, balance);
                break;
            case CURRENCY_CARD:
                card = new CurrencyCard(name, currency, balance);
                break;
            default:
                throw new IllegalAccessException("Неизвестный тип карты");
        }
        account.addProduct(card);
        return card;
    }

    public static CreditCard createCreditCard(String name, Currency currency, double balance, double interestRate, Account account){
        if (currency != Currency.RUB) {
            throw new IllegalArgumentException("Кредитная карта должна иметь валюту RUB");
        }
        CreditCard creditCard = new CreditCard(name, currency, balance, interestRate);
        account.addProduct(creditCard);
        return creditCard;
    }

    public static Contribution createContribution(String name, Currency currency, double balance, int termInMonths, double interestRate, Account account) {
        Contribution contribution = new Contribution(name, currency, balance, termInMonths, interestRate);
        account.addProduct(contribution);
        return contribution;
    }
}
