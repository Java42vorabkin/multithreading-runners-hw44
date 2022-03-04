package telran.games.hw44A;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Runner extends Thread {
private Race race;
private int runnerId;
private long runTime;

public Runner(Race race, int runnerId) {
	this.race = race;
	this.runnerId = runnerId;
}

public long getRunTime() {
	return runTime;
}

public int getRunnerId() {
	return runnerId;
}

@Override
public void run() {
	int sleepRange = race.getMaxSleep() - race.getMinSleep() + 1;
	int minSleep = race.getMinSleep();
	int distance = race.getDistance();
	for (int i = 0; i < distance; i++) {
		try {
			sleep((long) (minSleep + Math.random() * sleepRange));
		} catch (InterruptedException e) {
			throw new IllegalStateException();
		}
		System.out.println(runnerId);
	}
	race.addRunner(this);
}
}
