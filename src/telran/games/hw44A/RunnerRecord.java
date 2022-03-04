package telran.games.hw44A;

public class RunnerRecord {
	private Runner runner;
	private long runTime;

	public RunnerRecord(Runner runner, long runTime) {
		this.runner = runner;
		this.runTime = runTime;
	}
	public Runner getRunner() {
		return runner;
	}	
	public long getRunTime() {
		return runTime;
	}
}
