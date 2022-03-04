package telran.games.hw44;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Race {
	private static Race instance;
	private static Object mutex = new Object();
	private int distance;
	private int minSleep;
	private int maxSleep;
	private int winner = -1;
	private LocalTime startTime;
	private ArrayList<Runner> runners = new ArrayList<>();
	
	public static Race getInstance() {
		if(instance==null) {
			synchronized(mutex) {
				if(instance==null) {
					instance = new Race();
				}
			}
		}
		return instance;
	}

	private Race() {
		
	}
	public void setParams(int distance, int minSleep, int maxSleep) {
		this.distance = distance;
		this.minSleep = minSleep;
		this.maxSleep = maxSleep;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		if (this.winner == -1) {
			this.winner = winner;
		}
	}
	public int getDistance() {
		return distance;
	}
	public int getMinSleep() {
		return minSleep;
	}
	public int getMaxSleep() {
		return maxSleep;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime() {
		if(startTime==null) {
			startTime = LocalTime.now();
		}
	}
	public ArrayList<Runner> getRunners() {
		return runners;
	}
}
