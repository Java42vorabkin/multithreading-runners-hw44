package telran.games.hw39;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ThreadRacer implements Runnable {
	private static int distance;
	private static int minSleepingTime = 2;
	private static int maxSleepingTime = 5;
	private static LocalTime startTime;
	private static final ArrayList<ThreadRacer> racers = new ArrayList<>();
	private long runTime;

	public static ArrayList<ThreadRacer> getRacers() {
		return racers;
	}

	public long getRunTime() {
		return runTime;
	}

	public static void setStartTime(LocalTime startTime) {
		ThreadRacer.startTime = startTime;
	}

	private int tarakanNumber;

	public int getTarakanNumber() {
		return tarakanNumber;
	}

	public static void setDistance(int distance) {
		ThreadRacer.distance = distance;
	}

	public static void setMinSleepingTime(int minSleepingTime) {
		ThreadRacer.minSleepingTime = minSleepingTime;
	}

	public ThreadRacer(int tarakanNumber) {
		this.tarakanNumber = tarakanNumber;
	}

	public static void setMaxSleepingTime(int maxSleepingTime) {
		ThreadRacer.maxSleepingTime = maxSleepingTime;
	}

	public void run() {
		int sleepingRange = maxSleepingTime - minSleepingTime + 1;
		for (int i = 0; i < distance; i++) {
			try {
				Thread.sleep(minSleepingTime + (int) (Math.random() * sleepingRange));
			} catch (InterruptedException e) {

			}
			System.out.println(tarakanNumber);

		}

		synchronized (racers) {
			addResults();
		}

	}

	private void addResults() {
		runTime = ChronoUnit.MILLIS.between(startTime, LocalTime.now());
		racers.add(this);
	}

}
