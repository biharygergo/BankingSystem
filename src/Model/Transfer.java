package Model;

/**
 * Created by Gergo on 2017. 03. 16..
 */
public class Transfer extends Transaction {
    private Customer fromUser;
    private Customer toUser;

    public Customer getFromUser() {
        return fromUser;
    }

    public void setFromUser(Customer fromUser) throws Exception {

        fromUser.setBalance(fromUser.getBalance() - amount);
        this.fromUser = fromUser;
    }

    public Customer getToUser() {
        return toUser;
    }

    public void setToUser(Customer toUser){
        try {
            toUser.setBalance(toUser.getBalance() + amount);
        }
        catch (Exception e){
            System.out.println("You can't transfer a negative value!");
        }
        this.toUser = toUser;
    }

    public void sendTransfer() {
        toUser.addTransfer(this);
        fromUser.addTransfer(this);
    }

    @Override
    public String toString() {
        return "TRANSFER - " + amount + " - TO: " + toUser.getName() + " - " + date.toString();

    }
}
