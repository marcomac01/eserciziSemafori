package cronometro;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CronometroSemaforizzato extends Thread{
	final private Semaphore sem;
	private int numSecondi = 1;
	public CronometroSemaforizzato(){
		super();
		this.sem = new Semaphore(0);
	}

	public void run() {
		while(!isInterrupted()) {
			if (sem.availablePermits() > 0) {
				try {
					TimeUnit.SECONDS.sleep(1); //equivale a Thread.sleep(1000);
				}catch(InterruptedException e) {
					break;
				}
			numSecondi++;
			}
		}
	}
	public void riprendi() {
		System.out.println("Avvio/Riprendo");
		if (sem.availablePermits()>0) System.out.println("già in esecuzione");
			else sem.release();
	}
	public void azzera() {
		System.out.println("cronometro azzerto");
		this.numSecondi = 1;
	}
	public void ferma() throws InterruptedException {
		if(sem.availablePermits()==0) System.out.println("già fermo");
			else {
			sem.acquire();
			System.out.println("secondi passati: "+ numSecondi);
		}
	}

}
