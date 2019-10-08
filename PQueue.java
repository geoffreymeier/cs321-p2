
public class PQueue {

	MaxHeap queue;
	
	public PQueue() {
		queue = new MaxHeap();
	}
	
	public void enPQueue(Process p) {
		queue.insert(p);
		
	}

	public boolean isEmpty() {
		return queue.getHeapSize() == 0;
	}

	public Process dePQueue() {
		return queue.extract();
	}

	/**
	 * Update the priority levels of all elements in the array
	 * @param timeToIncrementLevel
	 * @param maxLevel
	 */
	public void update(int timeToIncrementLevel, int maxLevel) {
		// only update if the array is not empty
		if (queue.getHeapSize() > 0) {
			
			for (int i=0; i<queue.getHeapSize(); i++) {
				Process current = queue.getElement(i);
				current.incrementTimeNotProcessed();

				if (current.getTimeNotProcessed() >= timeToIncrementLevel) {
					current.resetTimeNotProcessed();
					if (current.getPriority() < maxLevel) {
						current.incrementPriorityLevel();
						queue.maxHeapifyUp(i);
					}
				}
			}
		}
	}



}
