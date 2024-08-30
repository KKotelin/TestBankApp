package service;

import model.Account;
import model.Profile;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private Map<String, Account> accounts;

    public AccountService() {
        this.accounts = new HashMap<>();
    }

    public Account openAccount(Profile profile, String accountNumber) {
        Account account = new Account(profile, accountNumber);
        accounts.put(accountNumber, account);
        return account;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}
