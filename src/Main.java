import Controller.BankingSystem;

public class Main {

    public static void main(String[] args) {

        BankingSystem bankingSystem = BankingSystem.getInstance();
        bankingSystem.startBanking();
    }
}
