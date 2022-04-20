package sequenze.ababababab;

import sequenze.Utils;

import java.util.concurrent.Semaphore;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Thread[] g1 = new Thread[5];
        Thread[] g2 = new Thread[5];
        for (int i = 0; i < 5; i++) {
            g1[i] = new A(s1,s2);
            g2[i] = new B(s1,s2);
        }
        Utils.comincia(g1, g2);
    }
}
