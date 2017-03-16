package Model;

/**
 * Created by Gergo on 2017. 03. 16..
 */
public class Exchange extends Transaction{

    @Override
    public String toString() {
        if(amount<0){
            return "WITHDRAWAL - " + amount + " - " + date.toString();
        }
        else{
            return "DEPOSIT - " + amount + " - " + date.toString();

        }
    }
}
