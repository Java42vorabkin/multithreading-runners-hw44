package telran.games.hw44A;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Race {
	private int distance;
	private int minSleep;
	private int maxSleep;
	private int winner = -1;
	private ArrayList<RunnerRecord> resultTable = new ArrayList<>();
	private LocalTime startTime;
	
	public Race(int distance, int minSleep, int maxSleep) {
		this.distance = distance;
		this.minSleep = minSleep;
		this.maxSleep = maxSleep;
	}
	public int getWinner() {
		return winner;
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
	public ArrayList<RunnerRecord> getResultTable() {
		return resultTable;
	}
	public synchronized void addRunner(Runner runner) {
		if (this.winner == -1) {
			this.winner = runner.getRunnerId();
		}
		RunnerRecord record = new RunnerRecord(runner, ChronoUnit.MILLIS.between(startTime, LocalTime.now()));
		resultTable.add(record);
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime() {
		if(startTime==null) {
			startTime = LocalTime.now();
		}
	}
	
}
