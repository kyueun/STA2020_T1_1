package testpackage;

public class TestClass {
    private static int value = 0;

    public static int getValue()
    {
        return value*2;
    }

    public static void setValue(int a)
    {
        value = a;
    }

    public static void main(String args[]) {
        System.out.println("Hellhho World");
    }
}
