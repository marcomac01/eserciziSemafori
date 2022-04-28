package sequenze;

public class Utils {
    private Utils(){} // privatizzo il costruttore affinchè non so possano creare istanze della classe

    public static void comincia(Thread[]... lista ) throws InterruptedException {
        for (Thread[] t : lista) {
            for(Thread thread : t) thread.start();
        }
    }
}
