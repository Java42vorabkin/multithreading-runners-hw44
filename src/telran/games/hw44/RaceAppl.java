package telran.games.hw44;

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
		Runner[] runners = new Runner[nThreads];
		Race race = Race.getInstance();
		race.setParams(distance, MIN_SLEEP, MAX_SLEEP);
		startRunners(runners, race);
		joinRunners(runners);
		displayWinner(race);
	}

	private static void displayWinner(Race race) {
		System.out.println("Congratulations to runner " + race.getWinner());
		ArrayList<Runner> runners = race.getRunners();
		System.out.println("           Results Table");
		System.out.println("place\tracer number\ttime");
		IntStream.range(0, runners.size())
		.forEach(i->System.out.printf("  %d\t\t%d\t%d\n",
				i+1,runners.get(i).getRunnerId(), runners.get(i)
				.getRunTime()));
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
