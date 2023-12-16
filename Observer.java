public class Observer {
    private static int actionCounter = 0;

    public static int getActionCounter() {
        return ++actionCounter;
    }
}
