import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {

    private static final Lock LOCK_A = new ReentrantLock();
    private static final Lock LOCK_B = new ReentrantLock();

    // Флаги «я тоже хочу замки» — видны обоим потокам (volatile обязателен)
    private static volatile boolean wantsA = false;
    private static volatile boolean wantsB = false;

    public static void main(String[] args) throws InterruptedException {
        WorkerA workerA = new WorkerA();
        WorkerB workerB = new WorkerB();
        workerA.start();
        workerB.start();

        Thread.sleep(10_000); // 10 секунд любуемся livelock'ом
        workerA.interrupt();
        workerB.interrupt();
        System.out.println("Главный поток: хватит вежливости, завершаемся");
    }

    private static class WorkerA extends Thread {
        @Override
        public void run() {
            int attempt = 0;
            while (true) {
                wantsA = true;
                LOCK_A.lock();
                try {
                    attempt++;
                    if (LOCK_B.tryLock()) {
                        try {
                            System.out.println("Поток 1: получил оба замка — работа сделана");
                        } finally {
                            LOCK_B.unlock();
                        }
                        return;
                    }
                    if (wantsB) {
                        System.out.println(
                                "Поток 1: поток 2 тоже хочет замки — вежливо уступаю (попытка " + attempt + ")");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            return; // корректный выход по прерыванию
                        }
                    }
                } finally {
                    LOCK_A.unlock();
                }
            }
        }
    }

    private static class WorkerB extends Thread {
        @Override
        public void run() {
            int attempt = 0;
            while (true) {
                wantsB = true;
                LOCK_B.lock();
                try {
                    attempt++;
                    if (LOCK_A.tryLock()) {
                        try {
                            System.out.println("Поток 2: получил оба замка — работа сделана");
                        } finally {
                            LOCK_A.unlock();
                        }
                        return;
                    }
                    if (wantsA) {
                        System.out.println(
                                "Поток 2: поток 1 тоже хочет замки — вежливо уступаю (попытка " + attempt + ")");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                } finally {
                    LOCK_B.unlock();
                }
            }
        }
    }
}
