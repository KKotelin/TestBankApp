package operations;

public interface CreditCardOperations extends DepositOperation, WithdrawOperation, BalanceInquiry {
    double getDebt();

    double getInterestRate();
}
