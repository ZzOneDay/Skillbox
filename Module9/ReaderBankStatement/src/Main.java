import java.util.*;

public class Main {
    public static void main(String[] args) {
        String information = "548673++++++1028    809216  /RU/CARD2CARD ALFA_MOBILE>MOSCOW          23.04.17 23.04.17 1000.00       RUR MCC6536";
        System.out.println(ListTransactions.getCorrectNameGroup(information));
        //нужно дописать метод, чтобы правильно формировались ключи

    }
}
//        ArrayList<Transaction> transactions = new ArrayList<>();
//        ReaderTransactionList readerTransactionList = new ReaderTransactionList();
//        String path = "res/movementList.csv";
//        ArrayList<String[]> arrayList = readerTransactionList.getInformation(path);
//        System.out.println("size" + arrayList.size());
//
//        for (String[] arrayTransaction : arrayList) {
//            transactions.add(new Transaction(arrayTransaction));
//        };
//        ListTransactions listTransactions = new ListTransactions(transactions);
////        System.out.println(listTransactions.getFullReceipt());
////        System.out.println(listTransactions.getFullExpense());
////        ListTransactions.printList(listTransactions);
////        ListTransactions sorted = ListTransactions.getNewListTransactionsSortByDate(listTransactions, true);
////        ListTransactions.printList(sorted);
//
//
//        HashMap<String, ListTransactions> transactionsSortedByGroup = ListTransactions.getNewListTransactionsSortedByGroup(listTransactions);
//        Set<String> groupName = transactionsSortedByGroup.keySet();
//        for (String oneGroup : groupName) {
//            ListTransactions list = transactionsSortedByGroup.get(oneGroup);
//            System.out.println(list.getTransactions().get(0).getDescriptionOfTransaction() + " Full Expensive " +
//                    + list.getFullExpense() + " Full Receipt " + list.getFullReceipt() ) ;
//        }
//    }
//}
