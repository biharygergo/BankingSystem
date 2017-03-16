package Controller;

import Model.Customer;
import Model.Exchange;
import Model.Transfer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Created by Gergo on 2017. 03. 16..
 */
public class BankingSystem {
    private static BankingSystem ourInstance = new BankingSystem();

    public static BankingSystem getInstance() {
        return ourInstance;
    }

    private BankingSystem() {
    }

    Scanner scanner = new Scanner(System.in);
    Customer customer = null;

    public void startBanking() {
        String command = "";
        System.out.println("Welcome to the Superbank!\n1)Login\n2)Add user\n3)Exit");
        command = scanner.nextLine();
        while (!command.equals("3")) {
            switch (command) {
                case "1":
                    tryLogin();
                    break;
                case "2":
                    addUser();
                    break;
                case "3":
                    System.exit(1);
                    break;
            }
            System.out.println("\n\n1)Login\n2)Add user\n3)Exit");
            command = scanner.nextLine();

        }

    }

    private void addUser() {
        System.out.println("Good decision! This is the best bank ever!");
        System.out.println("How should we call you?");
        String username = scanner.nextLine();
        System.out.println("What's your secret password?");
        String pass = scanner.nextLine();
        System.out.println("How much is your starting balance?");
        String amountString = scanner.nextLine();
        double amount = Double.parseDouble(amountString);
        Customer customer = new Customer();
        customer.setName(username);
        customer.setPassword(pass);
        try {
            customer.setBalance(amount);
        } catch (Exception e) {
            System.out.println("A negative value can't be a starting balance!");
            return;
        }
        UserAuthenticator.getInstance().addCustomer(customer);
        System.out.println("Okay, you have been added to the database!");
    }

    private void tryLogin() {
        System.out.println("Please authenticate yourself!");
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password");
        String pass = scanner.nextLine();

        try {
            UserAuthenticator authenticator = UserAuthenticator.getInstance();
            customer = authenticator.tryLogin(username, pass);
            if (customer != null) {
                showLoggedInMenu();
            } else {
                System.out.println("The password was incorrect!");
            }
        } catch (Exception e) {
            System.out.println("Couldn't find given username in the database!");
        }
    }

    private void showLoggedInMenu() {
        System.out.println("Hello there, " + customer.getName() + "!\n1)Deposit\n2)Withdraw\n3)Transfer\n4)History\n5)Logout");
        String command = scanner.nextLine();

        while (!command.equals("5")) {
            switch (command) {
                case "1":
                    handleDepositAction();
                    break;
                case "2":
                    handleWithdrawAction();
                    break;
                case "3":
                    handleTransferAction();
                    break;
                case "4":
                    handleHistoryAction();
                    break;
                case "5":
                    customer = null;
                    break;
            }
            System.out.println("What's next?\n1)Deposit\n2)Withdraw\n3)Transfer\n4)History\n5)Logout");
            command = scanner.nextLine();

        }


    }

    private void handleDepositAction() {
        System.out.println("Okay, how much do you want to deposit?");
        String amountString = scanner.nextLine();
        double amount = Double.parseDouble(amountString);

        if(amount< 0){
            System.out.println("You can't deposit a negative value, sorry!");
            return;
        }
        Exchange deposit = new Exchange();
        deposit.setAmount(amount);
        try {
            customer.addTransaction(deposit);
        } catch (Exception e) {
            System.out.println("Couldn't complete your request! Error: " + e.getMessage());
            return;
        }
        System.out.println("Deposit successful! Your current balance is: " + customer.getBalance());


    }

    private void handleWithdrawAction() {
        System.out.println("Okay, how much do you want to withdraw?");
        String amountString = scanner.nextLine();
        double amount = Double.parseDouble(amountString);
        if (amount >= 0) {
            amount *= -1;
        }

        Exchange withdrawal = new Exchange();
        withdrawal.setAmount(amount);
        try {
            customer.addTransaction(withdrawal);
        } catch (Exception e) {
            System.out.println("Couldn't complete your request! Error: " + e.getMessage());
            return;
        }
        System.out.println("Withdrawal successful! Your current balance is: " + customer.getBalance());

    }

    private void handleTransferAction() {
        System.out.println("Nice, let's do this transfer! Who are you transferring to?");
        UserAuthenticator userAuthenticator = UserAuthenticator.getInstance();
        userAuthenticator.listCustomers();
        System.out.println("Please type in their account number:");
        String accountNo = scanner.nextLine();
        long account = Long.parseLong(accountNo);
        try {
            Customer transferTo = userAuthenticator.getCustomerFromId(account);
            System.out.println("Okay! How much should we transfer?");
            String amountString = scanner.nextLine();
            double amount = Double.parseDouble(amountString);
            if (amount > 0) {
                Transfer transfer = new Transfer();
                transfer.setAmount(amount);
                transfer.setFromUser(customer);
                transfer.setToUser(transferTo);
                transfer.sendTransfer();
            } else {
                System.out.println("You can't transfer a debt! That's not how this works! ");
                return;
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private void handleHistoryAction() {
        UserHistoryManager manager = UserHistoryManager.getInstance();
        manager.setCustomer(customer);
        manager.startHistoryManager();
    }


}
