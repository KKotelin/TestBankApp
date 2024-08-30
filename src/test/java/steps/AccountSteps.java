package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Account;
import model.Profile;
import service.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountSteps {

    private Profile profile;
    private Account account;
    private AccountService accountService = new AccountService();

    @Given("Создаем профиль с именем {string} и паспортом {string}")
    public void createProfile(String fullName, String passportNumber) {
        profile = new Profile(fullName, passportNumber);
    }

    @When("Создан счет с номером {string}")
    public void openAccount(String accountNumber) {
        account = accountService.openAccount(profile, accountNumber);
    }

    @Then("Счет должен быть привязан к профилю {string}")
    public void checkAccountProfile(String expectedName) {
        assertEquals(expectedName, account.getProfile().getFullName());
    }

    @Then("Номер счета должен быть {string}")
    public void checkAccountNumber(String expectedAccountNumber) {
        assertEquals(expectedAccountNumber, account.getAccountNumber());
    }
}
