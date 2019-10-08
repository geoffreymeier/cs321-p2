

/**
 * This class defines the characteristics of a CPU Process
 * @author Geoffrey Meier
 *
 */
public class Process implements Comparable<Process> {

	private int arrivalTime, requiredProcessingTime, priorityLevel, timeNotProcessed, timeRemaining;
	private boolean finish;
	
	public Process(int currentTime, int requiredProcessingTime, int priorityLevel) {
		this.arrivalTime = currentTime;
		this.requiredProcessingTime = requiredProcessingTime;
		this.priorityLevel = priorityLevel;
		this.timeNotProcessed = 0;
		this.timeRemaining = requiredProcessingTime;
		this.finish = false;
	}

	/**
	 * Return the Time Remaining of the process
	 * @return the Time Remaining
	 */
	public int getTimeRemaining() {
		return this.timeRemaining;
	}

	/**
	 * Decrement the amount of time remaining.
	 */
	public void reduceTimeRemaining() {
		timeRemaining--;		
	}

	/**
	 * Returns whether or not the process has finished
	 * @return True if process is done, false if not
	 */
	public boolean finish() {
		if (timeRemaining <= 0)
			finish = true;
		
		return this.finish;
	}

	/**
	 * Return the Arrival Time of the process
	 * @return the Arrival Time
	 */
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	/**
	 * Return the Priority Level of the process
	 * @return The Priority Level
	 */
	public int getPriority() {
		return this.priorityLevel;
	}

	public void incrementPriorityLevel() {
		this.priorityLevel++;
	}

	public int getTimeNotProcessed() {
		return timeNotProcessed;
	}

	public void incrementTimeNotProcessed() {
		this.timeNotProcessed++;
	}
	
	public int getRequiredProcessingTime() {
		return requiredProcessingTime;
	}

	/**
	 * Reset the Time Not Processed of the process to 0
	 */
	public void resetTimeNotProcessed() {
		this.timeNotProcessed = 0;
		
	}
	
	@Override
	public int compareTo(Process o) {
		
		//if there is a tie in priority level, pick whichever arrived first
		if (priorityLevel==o.getPriority())
			return o.getArrivalTime() - arrivalTime;
		
		return priorityLevel - o.getPriority();
	}

}
