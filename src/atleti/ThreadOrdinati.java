package atleti;

import java.util.concurrent.TimeUnit;

public class ThreadOrdinati { //Definisci semafori e variabili
    private static int N = 20;

    public static class Atleta extends Thread{ private int numMaglia;
        public Atleta(int nMaglia) {
            this.numMaglia = nMaglia; }
        public void run() {
            try {

                System.out.println(numMaglia); TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {} }
    }

    public static void main(String[] args) { Atleta[] atleti = new Atleta[N];
        for (int i = 0; i < N; i++) {
            atleti[i] = new Atleta(i); atleti[i].start(); }
    }
}