import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        ReaderTransactionList readerTransactionList = new ReaderTransactionList();
        String path = "res/movementList.csv";
        ArrayList<String[]> arrayInformation = readerTransactionList.getInformation(path);

        for (String[] transactionInformationArray : arrayInformation) {
            transactions.add(new Transaction(transactionInformationArray));
        }
        ListTransactions listTransactions = new ListTransactions(transactions);

        HashMap<String, ListTransactions> transactionsSortedByGroup = ListTransactions.getNewListTransactionsSortedByGroup(listTransactions);
        Set<String> groupName = transactionsSortedByGroup.keySet();
        for (String oneGroup : groupName) {
            ListTransactions list = transactionsSortedByGroup.get(oneGroup);
            ListTransactions sortedList = ListTransactions.getNewListTransactionsSortByDate(list, true);
            System.out.println("\nГруппа транзакций: " + oneGroup) ;
            for (Transaction transaction : sortedList.getTransactions()) {
                System.out.println("\t" + transaction.getDescriptionOfTransaction());
            }
            System.out.println("Итого \tРасходы: " + list.getFullExpense() + "\tДоходы: " + list.getFullReceipt());
        }
    }
}
