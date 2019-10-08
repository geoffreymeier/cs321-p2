import java.util.Random;

public class ProcessGenerator {

	Random g;
	double probability;
	boolean nextIsArrival;
	
	/**
	 * Allows the user to manage the creation of processes.
	 * @param probability The probability that a new process is created
	 */
	public ProcessGenerator(double probability) {
		// TODO Auto-generated constructor stub
		g = new Random();
		this.probability = probability;
		nextIsArrival = false;
	}

	/**
	 * Use the probability of the Process Generator to  decide whether or not a process can be created
	 * @return
	 */
	public boolean query() {		
		nextIsArrival = g.nextDouble() <= probability;
		return nextIsArrival;
	}

	/**
	 * Return a new process with randomly generated values based on the provided parameters.
	 * @param currentTime The current time slice.
	 * @param maxProcessTime The maximum required process time allowed.
	 * @param maxLevel The maximum priority level allowed
	 * @return
	 */
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxLevel) {
		// ensure a process can be created
		if (!nextIsArrival)
			return null;
		
		int priorityLevel = g.nextInt(maxLevel-1)+1;
		int requiredProcessingTime = g.nextInt(maxProcessTime-1)+1;
		
		return new Process(currentTime, requiredProcessingTime, priorityLevel);
	}

}
