package model;

import product.Product;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private Profile profile;
    private List<Product> products;
    private String accountNumber;

    public Account(Profile profile, String accountNumber){
        this.profile = profile;
        this.products = new ArrayList<>();
        this.accountNumber = accountNumber;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Profile getProfile() {
        return profile;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
