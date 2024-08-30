package operations;

public interface CreditOperations extends DepositOperation, WithdrawOperation {
    double getDebt();
    double getInterestRate();
}
