package sequenze;

public class Utils {
    public static void comincia(Thread[]... lista ) throws InterruptedException {
        for (Thread[] t : lista) {
            for(Thread thread : t) thread.start();
        }
    }
}
