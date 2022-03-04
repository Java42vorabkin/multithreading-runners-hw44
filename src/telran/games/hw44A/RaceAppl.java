package telran.games.hw44A;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.IntStream;
import telran.view.*;

public class RaceAppl {

	private static final int MAX_THREADS = 20;
	private static final int MIN_DISTANCE = 10;
	private static final int MAX_DISTANCE = 1000;
	private static final int MIN_SLEEP = 2;
	private static final int MAX_SLEEP = 5;
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Item[] items = getItems();
		Menu menu = new Menu("Race Game", items);
		menu.perform(io);
	}

	private static Item[] getItems() {
		Item[] res = {
				Item.of("Start new game", RaceAppl::startGame),
				Item.exit()
		};
		return res;
	}
	static void startGame(InputOutput io) {
		int nThreads = io.readInt(String.format("Enter number of the runners from 2 to %d", MAX_THREADS), 
																		2, MAX_THREADS);
		int distance = io.readInt(String.format("Enter distance from %d to %d", MIN_DISTANCE, MAX_DISTANCE), 
																		MIN_DISTANCE, MAX_DISTANCE);
		Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
		Runner[] runners = new Runner[nThreads];
		startRunners(runners, race);
		joinRunners(runners);
		displayWinner(race);
	}

	private static void displayWinner(Race race) {
		System.out.println("Congratulations to runner " + race.getWinner());
		System.out.println("           Results Table");
		System.out.println("place\tracer number\ttime");
		ArrayList<RunnerRecord> results = race.getResultTable();
		int placeNumber = 1;
		Iterator<RunnerRecord> itr = results.iterator();
		while(itr.hasNext()) {
			RunnerRecord record = itr.next();
			System.out.printf("  %d\t\t%d\t%d\n", 
					placeNumber++, record.getRunner().getRunnerId(), record.getRunTime());
		}
	}
	

	private static void joinRunners(Runner[] runners) {
		IntStream.range(0, runners.length).forEach(i -> {
			try {
				runners[i].join();
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
		});
		
	}

	private static void startRunners(Runner[] runners, Race race) {
		race.setStartTime();
		IntStream.range(0, runners.length).forEach(i -> {
			runners[i] = new Runner(race, i + 1);
			runners[i].start();
		});
		
	}

}
