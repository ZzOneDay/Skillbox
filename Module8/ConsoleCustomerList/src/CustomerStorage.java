import java.util.HashMap;
import java.util.regex.Pattern;

public class CustomerStorage {
    private HashMap<String, Customer> storage;
    private Pattern thisName = Pattern.compile("[А-Я][а-я]+");
    private Pattern thisEmail = Pattern.compile(".+@.+");
    private Pattern thisNumber = Pattern.compile("\\+[0-9]+");

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IndexOutOfBoundsException("Отсутствие всех данных");
        }
        String name = components[0] + " " + components[1];
        if (!thisIsName(components[0]) || !thisIsName(components[1])) {
            throw new IndexOutOfBoundsException("Не корретный ввод Имя/Фамилия");
        }
        if (!thisIsEmail(components[2])) {
            throw new IndexOutOfBoundsException("Не корретный ввод E-mail");
        }
        if (!thisIsNumber(components[3])) {
            throw new IndexOutOfBoundsException("Не корретный ввод номера телефона");
        }
        System.out.println(name);
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        if (storage.values().size() != 0) {
            storage.values().forEach(System.out::println);
        }
        else {
            throw new IndexOutOfBoundsException("Список пуст");
        }
    }

    public void removeCustomer(String name) {
        if (storage.containsKey(name)) {
            storage.remove(name);
        }
        else {
            throw new RuntimeException("Контакт не найден");
        }
    }

    public int getCount() {
        return storage.size();
    }

    private boolean thisIsName(String string) {
        return thisName.matcher(string).find();
    }

    private boolean thisIsEmail(String string) {
        return thisEmail.matcher(string).find();
    }

    private boolean thisIsNumber(String string) {
        return thisNumber.matcher(string).find();
    }

}