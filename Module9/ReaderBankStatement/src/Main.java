import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        ReaderTransactionList readerTransactionList = new ReaderTransactionList();
        String path = "C:\\Users\\Admin\\Desktop\\Skillbox\\Module9\\ReaderBankStatement\\res\\movementList.csv";
        ArrayList<String[]> arrayList = readerTransactionList.getInformation(path);
        System.out.println("size" + arrayList.size());

        for (String[] arrayTransaction : arrayList) {
            transactions.add(new Transaction(arrayTransaction));
        };

        ListTransactions listTransactions = new ListTransactions(transactions);
        System.out.println(listTransactions.getFullReceipt());
        System.out.println(listTransactions.getFullExpense());
        ListTransactions.printList(listTransactions);
        listTransactions.sortTransactionsByDate(false);
        ListTransactions.printList(listTransactions);
    }
}
