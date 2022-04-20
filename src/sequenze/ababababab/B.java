package sequenze.ababababab;

import java.util.concurrent.Semaphore;

public class B extends Thread{
    private Semaphore s1, s2;

    public B(Semaphore s1, Semaphore s2){
        super();
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        try {
            s2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("eseguo B");
        s1.release();
    }
}
