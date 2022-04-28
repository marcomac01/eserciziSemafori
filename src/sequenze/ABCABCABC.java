package sequenze;

import java.util.concurrent.Semaphore;

public class ABCABCABC {
    private static Semaphore s1 = new Semaphore(1);
    private static Semaphore s2 = new Semaphore(0);
    private static Semaphore s3 = new Semaphore(0);

    private static class A extends Thread {
        @Override
        public void run() {
            try {
               s1.acquire();
                System.out.printf("A");
                s2.release();
            } catch (Exception e) {
            }
        }
    }
    private static class B extends Thread {
        @Override
        public void run() {
            try {
                s2.acquire();
                System.out.printf("B");
                s3.release();
            } catch (Exception e) {
            }
        }
    }
    private static class C extends Thread {
        @Override
        public void run() {
            try {
                s3.acquire();
                System.out.printf("C ");
                s1.release();
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] g1 = {new A(), new A(), new A()};
        Thread[] g2 = {new B(), new B(), new B()};
        Thread[] g3 = {new C(), new C(), new C()};

        Utils.comincia(g1, g2, g3);
    }
}
