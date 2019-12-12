import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListTransactions {
    private ArrayList<Transaction> transactions;


    ListTransactions(ArrayList<Transaction> transactions) {
        this.transactions = new ArrayList<>(transactions);
    }

    public Double getFullReceipt() {
        Double receipt = 0.0;
        for (Transaction transaction : transactions) {
            receipt += transaction.getReceipt();
        }
        return receipt;
    }

    public Double getFullExpense() {
        Double expanse = 0.0;
        for (Transaction transaction : transactions) {
            expanse += transaction.getExpense();
        }
        return expanse;
    }

    public static ListTransactions getNewListTransactionsSortByDate(ListTransactions ArrayTransactions, boolean inOrder) {
        ArrayList<Transaction> transactions = new ArrayList<>(ArrayTransactions.getTransactions());
        if (inOrder) {
            transactions.sort((o1, o2) -> o1.getDateOfTransaction().compareTo(o2.getDateOfTransaction()));
        } else {
            transactions.sort((o1, o2) -> o2.getDateOfTransaction().compareTo(o1.getDateOfTransaction()));
        }
        return new ListTransactions(transactions);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public static HashMap<String, ListTransactions> getNewListTransactionsSortedByGroup(ListTransactions listTransactions) {
        HashMap<String, ArrayList<Transaction>> mapArrayTransactionSortedByGroup = new HashMap<>();
        ArrayList<Transaction> transactions = listTransactions.getTransactions();
        for (Transaction transaction : transactions) {
            if (!mapArrayTransactionSortedByGroup.containsKey(transaction.getDescriptionOfTransaction())) {
                //не находит
                ArrayList<Transaction> newList = new ArrayList<>();
                newList.add(transaction);
                mapArrayTransactionSortedByGroup.put(transaction.getDescriptionOfTransaction(), newList);
            } else {
                //находит
                mapArrayTransactionSortedByGroup.get(transaction.getDescriptionOfTransaction()).add(transaction);
            }
        }
        return getNewTransactionsMap(mapArrayTransactionSortedByGroup);
    }

    private static HashMap<String, ListTransactions> getNewTransactionsMap(HashMap<String, ArrayList<Transaction>> mapTransactions) {
        HashMap<String, ListTransactions> listTransactionsSortedByGroup = new HashMap<>();
        Set<String> keys = mapTransactions.keySet();
        for (String key : keys) {
            ListTransactions listTransactions = new ListTransactions(mapTransactions.get(key));
            listTransactionsSortedByGroup.put(key, listTransactions);
        }
        return listTransactionsSortedByGroup;
    }

    public static String getCorrectNameGroup(String information) {
        Pattern r = Pattern.compile("//.+\\t]");
        Matcher m = r.matcher(information);
        String needToCorrectPart = information.substring(m.start() + 1, m.end() - 1).replaceAll(",", ".");
        return needToCorrectPart;
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
