public class Deadlock {

    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
    }

    private static class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (LOCK_A) {
                System.out.println("Поток 1: захватил A");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                synchronized (LOCK_B) {
                    System.out.println("Поток 1: захватил B");
                }
            }
        }
    }

    private static class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (LOCK_B) {
                System.out.println("Поток 2: захватил B");
                synchronized (LOCK_A) {
                    System.out.println("Поток 2: захватил A");
                }
            }
        }
    }
}
