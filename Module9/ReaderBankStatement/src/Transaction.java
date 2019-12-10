import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String AccountType;//Тип Счет
    private String AccountNumber;//Номер счета
    private String Currency; //Валюта
    private Date DateOfTransaction; //Дата операции 08.05.17
    private String PostingReference; //Референс проводки
    private String DescriptionOfTransaction; //Описание транзакции
    private Double Receipt;//Приход
    private Double Expense;//Расход

    Transaction(String []information) {
        AccountType = information[0];
        AccountNumber = information[1];
        Currency = information[2];
        DateOfTransaction = getDateOfTransactionOfString(information[3]);
        PostingReference = information[4];
        DescriptionOfTransaction = information[5];
        Receipt = Double.parseDouble(information[6]);
        Expense = Double.parseDouble(information[7]);
    }

    private Date getDateOfTransactionOfString(String dateInformation) {
        try {
            return new SimpleDateFormat("dd.MM.yy").parse(dateInformation);
        } catch (ParseException e) {
            System.out.println("Не удалось получить корректную дату");
            e.printStackTrace();
        }
        return new Date();
    }

    public String getAccountType() {
        return AccountType;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public String getCurrency() {
        return Currency;
    }

    public Date getDateOfTransaction() {
        return DateOfTransaction;
    }

    public String getPostingReference() {
        return PostingReference;
    }

    public String getDescriptionOfTransaction() {
        return DescriptionOfTransaction;
    }

    public Double getReceipt() {
        return Receipt;
    }

    public Double getExpense() {
        return Expense;
    }
}
