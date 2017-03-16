package Model;

import java.util.Date;

/**
 * Created by Gergo on 2017. 03. 16..
 */
public abstract class Transaction {
    protected Date date;
    protected long id;
    protected double amount;

    public Transaction() {
        date = new Date();
        id = System.currentTimeMillis();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
