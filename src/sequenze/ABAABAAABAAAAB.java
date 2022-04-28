package sequenze;

import java.util.concurrent.Semaphore;

public class ABAABAAABAAAAB {
    private static Semaphore s1 = new Semaphore(1);
    private static Semaphore s2 = new Semaphore(0);
    private static Semaphore s3 = new Semaphore(1);
    private static Semaphore s4 = new Semaphore(1);
    private static int n = 1;
    private static int m = 1;

    private static class A extends Thread {
        @Override
        public void run() {
            try {
                s1.acquire();
                System.out.printf("A");
                s3.acquire();
                s4.acquire();
                n--;

                if(n==0){
                    m++;
                    n = m;
                    s2.release();
                }
                s3.release();
                s4.release();
            } catch (Exception e) {
            }
        }
    }
    private static class B extends Thread{
        @Override
        public void run(){
            try {
                s2.acquire();
                System.out.printf("B ");

                s1.release(n);

            } catch (Exception e){}
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread[] g1 = {new A(), new A(), new A(), new A(), new A(), new A(), new A(), new A(), new A(), new A()};
        Thread[] g2 = {new B(), new B(), new B(), new B()};

        Utils.comincia(g1, g2);
    }
}
