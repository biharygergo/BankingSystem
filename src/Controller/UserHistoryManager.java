package Controller;

import Model.Customer;
import Model.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Gergo on 2017. 03. 16..
 */
public class UserHistoryManager {
    private static UserHistoryManager ourInstance = new UserHistoryManager();

    public static UserHistoryManager getInstance() {
        return ourInstance;
    }

    private UserHistoryManager() {
    }

    Scanner scanner = new Scanner(System.in);
    Customer customer = null;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void startHistoryManager() {
        System.out.println("Let's see your account details. Your current balance is: " + customer.getBalance());
        System.out.println("Here's what you can do: \n1)List all your transactions\n2)Only deposits\n3)Only withdrawals\n4)Filter by date\n5)Go back");
        String command = scanner.nextLine();

        while (!command.equals("5")) {
            switch (command) {
                case "1":
                    listAllTransactions();
                    break;
                case "2":
                    listOnlyDeposits();
                    break;
                case "3":
                    listOnlyWithdrawals();
                    break;
                case "4":
                    showFilterMenu();
                    break;
                case "5":
                    break;

            }
            System.out.println("Here's what you can do: \n1)List all your transactions\n2)Only deposits\n3)Only withdrawals\n4)Filter by date\n5)Go back");
            command = scanner.nextLine();
        }
    }

    private void listAllTransactions() {

        if (customer.getTransactionHistory().size() == 0) {
            System.out.println("No transactions yet!");
        }
        for (Transaction transaction : customer.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    private void listOnlyDeposits() {
        if (customer.getTransactionHistory().size() == 0) {
            System.out.println("No transactions yet!");
        }
        for (Transaction transaction : customer.getTransactionHistory()) {
            if (transaction.getAmount() > 0)
                System.out.println(transaction);
        }
    }

    private void listOnlyWithdrawals() {
        if (customer.getTransactionHistory().size() == 0) {
            System.out.println("No transactions yet!");
        }
        for (Transaction transaction : customer.getTransactionHistory()) {
            if (transaction.getAmount() < 0)
                System.out.println(transaction);
        }

    }

    private void showFilterMenu() {
        System.out.println("Okay, let's get technical. What's the start date for your search? (Eg.: 2017.01.01");
        String fromFilter = scanner.nextLine();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");

        try {
            Date fromdate = ft.parse(fromFilter);
            System.out.println("What's the end date for your search?");
            fromFilter = scanner.nextLine();
            Date toDate = ft.parse(fromFilter);
            listDateFilteredTransactions(fromdate, toDate);

        } catch (ParseException e) {
            System.out.println("Oops! Couldn't cast that date! :( ");
            return;
        }
    }

    private void listDateFilteredTransactions(Date fromdate, Date toDate) {
        System.out.println("Here are your transactions for the given range:");
        if (customer.getTransactionHistory().size() == 0) {
            System.out.println("No transactions yet!");
        }
        for (Transaction transaction : customer.getTransactionHistory()) {
            if (transaction.getDate().getTime() > fromdate.getTime() && transaction.getDate().getTime() < toDate.getTime())
                System.out.println(transaction);
        }
    }
}
