package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Account;
import model.Profile;
import product.ContributionRubYearTerm;
import product.CreditCard;
import product.CurrencyCard;
import product.DebitCard;
import service.AccountService;
import service.ProductFactory;
import util.CardType;
import util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductOperationSteps {

    private Profile profile;
    private Account account;
    private AccountService accountService = new AccountService();
    private DebitCard debitCard;
    private CreditCard creditCard;
    private CurrencyCard currencyCard;
    private ContributionRubYearTerm contribution;

    @Given("Открыт аккаунт с номером {string} и профилем {string}")
    public void openAccountWithProfile(String accountNumber, String profileName) {
        profile = new Profile(profileName, "123456");
        account = accountService.openAccount(profile, accountNumber);
    }

    @When("Создать дебетовую карту с именем {string}, валютой {string}, балансом {double}")
    public void whenCreateDebitCard(String name, String currencyString, double balance) throws IllegalAccessException {
        Currency currency = Currency.valueOf(currencyString.toUpperCase());
        debitCard = (DebitCard) ProductFactory.createCard(CardType.DEBIT_CARD, name, currency, balance, account);
    }

    @When("Создать кредитную карту с именем {string}, валютой {string}, балансом {double} и процентной ставкой {double}")
    public void whenCreateCreditCard(String name, String currencyString, double balance, double interestRate){
        Currency currency = Currency.valueOf(currencyString.toUpperCase());
        creditCard = ProductFactory.createCreditCard(name, currency, balance, interestRate, account);
    }

    @When("Создать валютную карту с именем {string}, валютой {string}, балансом {double}")
    public void whenCreateCurrencyCard(String name, String currencyString, double balance) throws IllegalAccessException {
        Currency currency = Currency.valueOf(currencyString.toUpperCase());
        currencyCard = (CurrencyCard) ProductFactory.createCard(CardType.CURRENCY_CARD, name, currency, balance, account);
    }

    @When("Создать вклад с именем {string}, валютой {string}, балансом {double}, сроком {int} месяцев и процентной ставкой {double}")
    public void createDeposit(String name, String currencyString, double balance, int termInMonths, double interestRate) {
        Currency currency = Currency.valueOf(currencyString.toUpperCase());
        contribution = ProductFactory.createContribution(name, currency, balance, termInMonths, interestRate, account);
    }

    @When("Пополнить дебетовую карту на сумму {double}")
    public void depositToDebitCard(double amount) {
        debitCard.deposit(amount);
    }

    @When("Снять с дебетовой карты сумму {double}")
    public void withdrawFromDebitCard(double amount) {
        boolean success = debitCard.withdraw(amount);
        if (!success) {
            System.out.println("Недостаточно средств. Баланс не может уйти в минус.");
        }
    }

    @When("Пополнить валютную карту на сумму {double}")
    public void depositToCurrencyCard(double amount) {
        currencyCard.deposit(amount);
    }

    @When("Снять с валютной карты сумму {double}")
    public void withdrawFromCurrencyCard(double amount) {
        boolean success = currencyCard.withdraw(amount);
        if (!success) {
            System.out.println("Недостаточно средств. Баланс не может уйти в минус.");
        }
    }

    @When("Пополнить кредитную карту на сумму {double}")
    public void depositToCreditCard(double amount) {
        creditCard.deposit(amount);
    }

    @When("Снять с кредитной карты сумму {double}")
    public void withdrawFromCreditCard(double amount) {
        creditCard.withdraw(amount);
    }

    @When("Запросить баланс кредитной карты")
    public void getCreditCardBalance() {
        creditCard.getBalance();

    }

    @When("Пополнить вклад на сумму {double}")
    public void contributionDeposit(double amount){
        contribution.deposit(amount);
    }

    @When("Рассчитать сумму вклада на весь срок")
    public void calculateMaturityAmount(){
        contribution.calculateMaturityAmount();
    }

    @When("Закрыть вклад")
    public void closeContribution(){
        contribution.close();
    }

    @Then("Кредитная карта должна быть создана с именем {string}")
    public void creditCardShouldBeCreated(String name) {
        assertNotNull(creditCard);
        assertEquals(name, creditCard.getName());
    }

    @Then("Баланс кредитной карты должен быть {double}")
    public void creditCardBalanceShouldBe(double balance) {
        assertEquals(balance, creditCard.getBalance());
    }

    @Then("Процентная ставка кредитной карты должна быть {double}")
    public void creditCardInterestRateShouldBe(double interestRate) {
        assertEquals(interestRate, creditCard.getInterestRate(), 0.01);
    }

    @Then("Дебетовая карта должна быть создана с именем {string}")
    public void debitCardShouldBeCreated(String name) {
        assertNotNull(debitCard);
        assertEquals(name, debitCard.getName());
    }

    @Then("Баланс дебетовой карты должен быть {double}")
    public void debitCardBalanceShouldBe(double balance) {
        assertEquals(balance, debitCard.getBalance());
    }

    @Then("Валютная карта должна быть создана с именем {string}")
    public void currencyCardShouldBeCreated(String name) {
        assertNotNull(currencyCard);
        assertEquals(name, currencyCard.getName());
    }

    @Then("Баланс валютной карты должен быть {double}")
    public void currencyCardBalanceShouldBe(double balance) {
        assertEquals(balance, currencyCard.getBalance());
    }

    @Then("Задолженность должна быть {double}")
    public void checkCreditCardDebt(double debt){
        assertEquals(debt, creditCard.getDebt());
    }

    @Then("Вклад должен быть создан с именем {string}")
    public void depositShouldBeCreated(String name) {
        assertNotNull(contribution);
        assertEquals(name, contribution.getName());
    }

    @Then("Баланс вклада должен быть {double}")
    public void depositBalanceShouldBe(double balance) {
        assertEquals(balance, contribution.getBalance(), 0.01);
    }

    @Then("Срок вклада должен быть {int} месяцев")
    public void depositTermShouldBe(int termInMonths) {
        assertEquals(termInMonths, contribution.getTermInMonths());
    }

    @Then("Процентная ставка вклада должна быть {double}")
    public void depositInterestRateShouldBe(double interestRate) {
        assertEquals(interestRate, contribution.getInterestRate(), 0.01);
    }

    @Then("Баланс вклада , с учетом процентов должен быть {double}")
    public void checkCalculatorMaturityAmount(double balance){
        assertEquals(balance, contribution.calculateMaturityAmount());
    }

    @Then("Вклад должен быть закрыт")
    public void checkCloseContribution(){
        assertTrue(contribution.isClosed());
    }
}
