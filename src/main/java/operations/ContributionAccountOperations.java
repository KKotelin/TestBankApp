package operations;

public interface ContributionAccountOperations extends DepositOperation, BalanceInquiry {
    void close();
    double calculateMaturityAmount();
    boolean isClosed();
}
