import model.Account;
import model.Profile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import product.Contribution;
import product.CreditCard;
import product.CurrencyCard;
import product.DebitCard;
import service.AccountService;
import service.ProductFactory;
import util.CardType;
import util.Currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClass {
    private static Profile profile;
    private static Account account;
    private static AccountService accountService;
    private static DebitCard debitCard;
    private static CurrencyCard currencyCard;
    private static CreditCard creditCard;
    private static Contribution contribution;

    @BeforeAll
    public static void setUp() {
        profile = new Profile("Котелин Кирилл", "123456");
        accountService = new AccountService();
        account = accountService.openAccount(profile, "ACC8965");
    }

    @BeforeEach
    public void setUpProducts() throws IllegalAccessException {
        debitCard = (DebitCard) ProductFactory.createDebitAndCurrencyCard(CardType.DEBIT_CARD, "Дебетовая карта", Currency.RUB, 5000.0, account);
        currencyCard = (CurrencyCard) ProductFactory.createDebitAndCurrencyCard(CardType.CURRENCY_CARD, "Валютная карта", Currency.USD, 1000.0, account);
        creditCard = ProductFactory.createCreditCard("Кредитная карта", Currency.RUB, 2000.0, 0.2, account);
        contribution = ProductFactory.createContribution("Вклад на год", Currency.RUB, 10000.0, 12, 0.05, account);
    }


    @Test
    @DisplayName("Проверка создания профиля")
    public void testCreateProfile() {
        assertEquals("Котелин Кирилл", profile.getFullName());
        assertEquals("123456", profile.getPassportNumber());
    }

    @Test
    @DisplayName("Проверка создания счета и привязки к профилю")
    public void testOpenAccount() {
        Account retrievedAccount = accountService.getAccount("ACC8965");
        assertAll(
                () -> assertEquals(profile, account.getProfile()),
                () -> assertEquals("ACC8965", account.getAccountNumber()),
                () -> assertEquals(account, retrievedAccount)
        );
    }

    @Test
    @DisplayName("Проверка создания и корректности данных дебетовой карты")
    public void testCreateDebitCard() {
        assertAll(
                () -> assertEquals("Дебетовая карта", debitCard.getName()),
                () -> assertEquals("RUB", debitCard.getCurrency()),
                () -> assertEquals(5000.0, debitCard.getBalance()),
                () -> assertTrue(account.getProducts().contains(debitCard))
        );
    }

    @Test
    @DisplayName("Проверка создания и корректности данных валютной карты")
    public void testCreateCurrencyCard() {
        assertAll(
                () -> assertEquals("Валютная карта", currencyCard.getName()),
                () -> assertEquals("USD", currencyCard.getCurrency()),
                () -> assertEquals(1000.0, currencyCard.getBalance()),
                () -> assertTrue(account.getProducts().contains(currencyCard))
        );
    }

    @Test
    @DisplayName("Проверка создания и корректности данных кредитной карты")
    public void testCreateCreditCard() {
        assertAll(
                () -> assertEquals("Кредитная карта", creditCard.getName()),
                () -> assertEquals("RUB", creditCard.getCurrency()),
                () -> assertEquals(2000.0, creditCard.getBalance()),
                () -> assertEquals(0.2, creditCard.getInterestRate()),
                () -> assertTrue(account.getProducts().contains(creditCard))
        );
    }

    @Test
    @DisplayName("Проверка создания и корректности данных вклада")
    public void testCreateDeposit() {
        assertAll(
                () -> assertEquals("Вклад на год", contribution.getName()),
                () -> assertEquals("RUB", contribution.getCurrency()),
                () -> assertEquals(10000.0, contribution.getBalance()),
                () -> assertEquals(12, contribution.getTermInMonths()),
                () -> assertEquals(0.05, contribution.getInterestRate()),
                () -> assertTrue(account.getProducts().contains(contribution))
        );
    }

    @Test
    @DisplayName("Проверка депозита на дебетовую карту")
    public void testDebitCardDeposit() {
        debitCard.deposit(1000.0);
        assertEquals(6000.0, debitCard.getBalance());
    }

    @Test
    @DisplayName("Проверка снятия с дебетовой карты")
    public void testDebitCardWithdraw(){
        debitCard.withdraw(1500.0);
        assertEquals(3500.0, debitCard.getBalance());
    }

    @Test
    @DisplayName("Проверка баланса дебетовой карты")
    public void testDebitCardBalance() {
        assertEquals(5000.0, debitCard.getBalance());
    }

    @Test
    @DisplayName("Депозит на валютную карту")
    public void testCurrencyCardDeposit() {
        currencyCard.deposit(1000.0);
        assertEquals(2000.0, currencyCard.getBalance());
    }

    @Test
    @DisplayName("Снятие с валютной карты")
    public void testCurrencyCardWithdraw() throws IllegalAccessException {
        currencyCard.withdraw(500.0);
        assertEquals(500.0, currencyCard.getBalance());
    }

    @Test
    @DisplayName("Проверка баланса валютной карты")
    public void testCurrencyCardBalance() {
        assertEquals(1000.0, currencyCard.getBalance());
    }

    @Test
    @DisplayName("Депозит на кредитную карту")
    public void testCreditCardDeposit() {
        creditCard.deposit(1000.0);
        assertEquals(3000.0, creditCard.getBalance());
    }

    @Test
    @DisplayName("Снятие с кредитной карты")
    public void testCreditCardWithdraw() {
        creditCard.withdraw(500.0);
        assertEquals(1500.0, creditCard.getBalance());
    }

    @Test
    @DisplayName("Проверка баланса кредитной карты")
    public void testCreditCardBalance() {
        assertEquals(2000.0, creditCard.getBalance());
    }

    @Test
    @DisplayName("Проверка задолженности по кредитной карте")
    public void testCreditCardDebt() {
        assertEquals(0.0, creditCard.getDebt());

        creditCard.withdraw(1500.0);
        assertEquals(1500.0, creditCard.getDebt());

        creditCard.deposit(500.0);
        assertEquals(1000.0, creditCard.getDebt());
    }

    @Test
    @DisplayName("Проверка пополнения вклада")
    public void testContributionDeposit() {
        contribution.deposit(2000.0);
        assertEquals(12000, contribution.getBalance());
    }

    @Test
    @DisplayName("Проверка запроса баланса вклада")
    public void testContributionWithdraw() {
        assertEquals(10000.0, contribution.getBalance());
    }

    @Test
    @DisplayName("Проверка расчета суммы вклада")
    public void testContributionCalculationOfAmount() {
        contribution.calculateMaturityAmount();
        assertEquals(10511.62, contribution.getBalance());
    }

    @Test
    @DisplayName("Проверка закрытия вклада")
    public void testContributionClose() {
        contribution.close();
        assertTrue(contribution.isClosed());
        assertEquals(0.0, contribution.getBalance());
    }
}
