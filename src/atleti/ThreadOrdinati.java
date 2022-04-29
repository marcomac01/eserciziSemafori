package atleti;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ThreadOrdinati {

    private static int N = 20;
    private static Semaphore[] ordineDiPartenza;

    public static void main(String[] args) {
        ordineDiPartenza = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            ordineDiPartenza[i] = new Semaphore(i==0?1:0);
        }

        Atleta[] atleti = new Atleta[N];
        for (int i = 0; i < N; i++) {
            atleti[i] = new Atleta(i);
            atleti[i].start();
        }
    }

    public static class Atleta extends Thread{
        private int numMaglia;
        public Atleta(int nMaglia) {
            this.numMaglia = nMaglia;
        }
        public void run() {
            try {
                ordineDiPartenza[numMaglia].acquire();

                System.out.println(numMaglia);
                Thread.sleep(1000);
                if(numMaglia+1<N)
                    ordineDiPartenza[numMaglia+1].release();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
