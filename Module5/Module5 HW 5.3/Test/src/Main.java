public class Main {
    public static void main(String[] args)
    {
        try
        {
            System.err.println(1);
            throw new Exception();

        }
        catch ()
        {
            System.err.println(2);
            try {
                throw new Throwable();
            }
            catch (Error d)
            {
                System.err.println(123);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        finally
        {
            System.err.println(3);

        }
        System.err.println(4);
    }
}
