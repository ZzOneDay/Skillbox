public class Main {
    public static void main(String[] args) {
        String text = "Java is a general-purpose programming language that is class-based, object-oriented " +
                "(although not a pure object-oriented language, as it contains primitive types), " +
                "and designed to have as few implementation dependencies as possible. It is intended to " +
                "let application developers write once, run anywhere (WORA), meaning that compiled " +
                "Java code can run on all platforms that support Java without the need for recompilation." +
                "Java applications are typically compiled to bytecode that can run on any Java virtual " +
                "machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar " +
                "to C and C++, but it has fewer low-level facilities than either of them. As of 2018, Java was one " +
                "of the most popular programming languages in use according to GitHub, particularly for " +
                "client-server web applications, with a reported 9 million developers.";

        text = text.replaceAll("[().,]" , "");

        String[] fragments = text.split("[\\s* \\-]");

        for (int i = 0; i < fragments.length; i++) {
            System.out.println(fragments[i]);
        }


    }
}
