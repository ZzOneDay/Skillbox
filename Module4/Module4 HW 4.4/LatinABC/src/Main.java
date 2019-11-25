public class Main {
    public static void main(String[] args) {
        String latinABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < latinABC.length(); i++) {
            char a = latinABC.charAt(i);
            System.out.println("Симвал " + a + " имеет код: " + (int) a);
        }
    }
}
