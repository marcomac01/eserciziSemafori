package tifoseria;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Tifoseria {
    private static Random r = new Random();
    private static boolean selettore = true;
    private static boolean selettore2;
    private static int n = 6;
    private static LinkedList<Tifoso> tifosiS1 = new LinkedList<>();
    private static LinkedList<Tifoso> tifosiS2 = new LinkedList<>();
    private static Semaphore sem1 = new Semaphore(1);
    private static Semaphore sem2 = new Semaphore(2);
    private static Semaphore sem3 = new Semaphore(1);

    public static class Tifoso extends Thread {
        private final String squadra;

        public Tifoso(String squadra) {
            this.squadra = squadra;
        }
        @Override
        public void run() {
            System.out.println("Trasportato tifoso squadra " + squadra);}
    }

    public static class Autobus1 extends Thread {
        @Override
        public void run() {
            try {
                sem3.acquire();
                if (selettore) {
                    sem1.acquire();
                    for (int i = 0; i < n; i++) {
                        if(tifosiS1.getFirst() == null) break;
                        tifosiS1.getFirst().run();
                        tifosiS1.removeFirst();
                    }
                    sem1.release();
                } else {
                    sem2.acquire();
                    for (int i = 0; i < n; i++) {
                        if(tifosiS2.getFirst() == null) break;
                        tifosiS2.getFirst().run();
                        tifosiS2.removeFirst();
                    }
                    sem2.release();
                }
                System.out.println("____________");
                selettore = !selettore;
                sem3.release();
            } catch (Exception e) {}
        }
    }



    public static class Autobus2 extends Thread {
        @Override
        public void run() {
            if(tifosiS1.size()>tifosiS2.size()) selettore2 = true;
            else if (tifosiS1.size()<tifosiS2.size()) selettore2 = false;
            else selettore2 = r.nextBoolean();
            try {
                sem3.acquire();
                if (selettore) {
                    sem1.acquire();
                    for (int i = 0; i < n; i++) {
                        if(tifosiS1.getFirst() == null) break;
                        tifosiS1.getFirst().run();
                        tifosiS1.removeFirst();
                    }
                    sem1.release();
                } else {
                    sem2.acquire();
                    for (int i = 0; i < n; i++) {
                        if(tifosiS2.getFirst() == null) break;
                        tifosiS2.getFirst().run();
                        tifosiS2.removeFirst();
                    }
                    sem2.release();
                }
                System.out.println("____________");
                selettore = !selettore;
                sem3.release();
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < n*3; i++) {
            tifosiS1.addLast(new Tifoso("S1"));
            tifosiS2.addLast(new Tifoso("S2"));
        }
        while(!tifosiS1.isEmpty() | !tifosiS2.isEmpty())new Autobus1().run();
    }
}

