package sequenze.ababababab;

import java.util.concurrent.Semaphore;

public class A extends Thread{
    private Semaphore s1, s2;

    public A(Semaphore s1, Semaphore s2){
        super();
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        try {
            s1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("eseguo A");
        s2.release();
    }
}
