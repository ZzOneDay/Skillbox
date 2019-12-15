import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
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
            String groupKey = getCorrectNameGroup(transaction.getDescriptionOfTransaction());
            if (!mapArrayTransactionSortedByGroup.containsKey(groupKey)) {
                //List don't have this Key
                ArrayList<Transaction> newList = new ArrayList<>();
                newList.add(transaction);
                mapArrayTransactionSortedByGroup.put(groupKey, newList);
            } else {
                //List have this key
                mapArrayTransactionSortedByGroup.get(groupKey).add(transaction);
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

    private static String getCorrectNameGroup(String information) {
        String needToCorrectPart = information;
        information = information.replaceAll("\\\\","/");
        Pattern r = Pattern.compile("/[-/ _<>A-Z0-9a-zА-Яа-я]+          ");
        Matcher m = r.matcher(information);
        if (m.find()) {
            needToCorrectPart = information.substring(m.start(), m.end());
        }
        return needToCorrectPart.trim();
    }
}
