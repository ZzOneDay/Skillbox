import java.util.ArrayList;

public class ListTransactions
{
    private ArrayList<Transaction> transactions;


    ListTransactions(ArrayList<Transaction> transactions) {
        this.transactions = new ArrayList<>(transactions);
    }

    public Double getFullReceipt(){
        Double receipt = 0.0;
        for (Transaction transaction : transactions) {
            receipt += transaction.getReceipt();
        }
        return receipt;
    }

    public Double getFullExpense(){
        Double expanse = 0.0;
        for (Transaction transaction : transactions) {
            expanse += transaction.getExpense();
        }
        return expanse;
    }

    public void sortTransactionsByDate (boolean inOrder) {
        if (inOrder) {
            transactions.sort((o1, o2) -> o1.getDateOfTransaction().compareTo(o2.getDateOfTransaction()));
        }
        else {
            transactions.sort((o1, o2) -> o2.getDateOfTransaction().compareTo(o1.getDateOfTransaction()));
        }
    }

    public static void printList (ListTransactions listTransactions) {
        for (Transaction transaction : listTransactions.transactions) {
            System.out.println("-------------------------------------------------------");
            System.out.print(transaction.getAccountNumber());
            System.out.print(transaction.getAccountType());
            System.out.print(transaction.getCurrency());
            System.out.print(transaction.getDateOfTransaction());
            System.out.print(transaction.getDescriptionOfTransaction());
            System.out.print(transaction.getReceipt());
            System.out.println(transaction.getExpense());
        }
    }
}
