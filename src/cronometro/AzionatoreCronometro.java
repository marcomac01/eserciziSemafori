package cronometro;

import java.util.Scanner;

public class AzionatoreCronometro {

	public static void main(String[] args) throws InterruptedException {
		boolean esecuzione = true;
		Scanner in = new Scanner(System.in);
		CronometroSemaforizzato cronometro = new CronometroSemaforizzato();
		cronometro.start();
		System.out.println("Premi; A = avvia, F = ferma, R = reset, E = esci.");
		while (esecuzione) {
			switch (in.nextLine()) {
				case "A" : cronometro.riprendi(); break;
				case "F" : cronometro.ferma(); break;
				case "R" : cronometro.azzera(); break;
				case "E" : cronometro.interrupt(); esecuzione = false; break;
				default:
					System.out.println("comando non valido");
			}
		}
	}
}
