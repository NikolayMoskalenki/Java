public class AlternatingPrinter {
    private static final Object MONITOR = new Object();
    private static boolean onesTurn = true;

    public static void main(String[] args) {
        PrinterThread t1 = new PrinterThread(true, "1");
        PrinterThread t2 = new PrinterThread(false, "2");
        t1.start();
        t2.start();

    }

    static void print(boolean isOne, String symbol) {
        while (true) {
            synchronized (MONITOR) {
                while (onesTurn != isOne) {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.print(symbol + " ");
                onesTurn = !isOne;
                MONITOR.notifyAll();
            }
        }
    }
}

class PrinterThread extends Thread {
    private final boolean isOne;
    private final String symbol;

    PrinterThread(boolean isOne, String symbol) {
        this.isOne = isOne;
        this.symbol = symbol;
    }

    @Override
    public void run() {
        AlternatingPrinter.print(isOne, symbol);
    }
}
