import java.math.BigDecimal;

public class Bank {
    public static BigDecimal bankAccount = BigDecimal.valueOf(999000);

    public static void menegerBankAccount(BigDecimal deposit) {
        bankAccount.subtract(deposit);
    }
    public static void getBankAccount(){
        System.out.println("bankAccount: "+bankAccount);
    }
}
