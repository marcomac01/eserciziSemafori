package sequenze;

import java.util.concurrent.Semaphore;

public class ABABCABABCABABC {
    private static Semaphore s1 = new Semaphore(1);
    private static Semaphore s2 = new Semaphore(0);
    private static Semaphore s3 = new Semaphore(0);
    private static Semaphore s4 = new Semaphore(1);
    private static int n = 0;

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
                s4.acquire();
                n++;
                if (n == 2) {
                    n = 0;
                    s3.release();
                } else s1.release();
                s4.release();
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
        Thread[] g1 = {new A(), new A(), new A(), new A(), new A(), new A(), new A(), new A(), new A(), new A(), new B(), new B(), new B()};
        Thread[] g2 = {new B(), new B(), new B(), new B(), new B(), new B(), new B(), new B(), new B(), new B(), new B(), new B(), new B()};
        Thread[] g3 = {new C(), new C(), new C(),  new C(), new C()};

        Utils.comincia(g1, g2, g3);
    }
}
