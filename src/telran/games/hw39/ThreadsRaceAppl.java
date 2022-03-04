package telran.games.hw39;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.IntStream;
public class ThreadsRaceAppl {
	private static final int TRIALS_LIMIT = 5;
	public static void main(String[] args) throws Exception {
		int minThreads = 2;
		int maxThreads = 10;
		int nThreads = 6; // !!
		int minDistance = 100;
		int maxDistance = 10000;
		int distance = 700;  // !!
		
		ThreadRacer.setDistance(distance);
				
		Thread[] threads = startRace(nThreads);
		try {
			waitFinish(nThreads, threads);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayResults();
	}
	
	static private void displayResults() {
		ArrayList<ThreadRacer> racers=ThreadRacer.getRacers();
		System.out.println("           Results Table");
		System.out.println("place\tracer number\ttime");
		IntStream.range(0, racers.size())
		.forEach(i->System.out.printf("  %d\t\t%d\t%d\n",
				i+1,racers.get(i).getTarakanNumber(),racers.get(i)
				.getRunTime()));
	}

	private  static void waitFinish(int nThreads, Thread[] threads) throws InterruptedException {
		for(int i=0; i<nThreads; i++)
			threads[i].join();
	}
	
	private static Thread[] startRace(int nThreads) {
		Thread[]threads=new Thread[nThreads];
		ThreadRacer.setStartTime(LocalTime.now());
		for(int i=0; i<nThreads; i++){
			threads[i]=new Thread(new ThreadRacer(i+1));
			threads[i].start();
		}
		return threads;
	}
		
		
	}

