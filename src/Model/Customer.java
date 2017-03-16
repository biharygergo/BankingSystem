package Model;

import java.util.ArrayList;

/**
 * Created by Gergo on 2017. 03. 16..
 */
public class Customer {
    private long id;
    private double balance;
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();
    private ArrayList<Transfer> transferHistory = new ArrayList<>();

    private String password;
    private String name;

    public Customer() {
        id = System.currentTimeMillis();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) throws Exception {
        if(this.balance+balance < 0){
            throw new Exception("Insufficient funds!");
        }
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(ArrayList<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public ArrayList<Transfer> getTransferHistory() {
        return transferHistory;
    }

    public void setTrasferHistory(ArrayList<Transfer> transferHistory) {
        this.transferHistory = transferHistory;
    }

    public void addTransaction(Transaction transaction) throws Exception{

        if(balance+transaction.getAmount() < 0){
            throw new Exception("Insufficient funds!");
        }
        else{
            balance += transaction.getAmount();
            transactionHistory.add(transaction);
        }
    }

    public void addTransfer(Transfer transfer){
        transferHistory.add(transfer);
        transactionHistory.add(transfer);
    }
}
