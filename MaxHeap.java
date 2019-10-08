import java.util.Arrays;

public class MaxHeap {

	private Process[] heap;
	private int count;
	private final int STARTING_CAPACITY = 100;
	
	/**
	 * Construct a new MaxHeap
	 */
	public MaxHeap() {
		// TODO Auto-generated constructor stub
		heap = new Process[STARTING_CAPACITY];
		count = 0;
	}
	
	
	/**
	 * Heapify upwards based on a given index.
	 * @param index The index from which to heapify upwards
	 */
	public void maxHeapifyUp(int index) {
		//TODO
		if (index > 0 && index < count && heap[index].compareTo(heap[parent(index)])> 0) {
			Process tmp = heap[index];
			heap[index] = heap[parent(index)];
			heap[parent(index)] = tmp;
			maxHeapifyUp(parent(index));
		}
	}
	
	/**
	 * Heapify down based on a given root value, under the precondition that both
	 * children are already max heaps. To convert an array into a full MaxHeap, use the buildMaxHeap() 
	 * method.
	 * @param rootIndex Index at which to heapify down
	 */
	public void maxHeapifyDown(int rootIndex) {
		int left = leftChild(rootIndex);
		int right = rightChild(rootIndex);
		int largest; //index of the largest value 

		// if one or both children are out of bounds, set largest to whichever is not (rootIndex if both are)
		if (count>0) {
			if (left == -1 && right == -1)
				largest = rootIndex;
			else if (left == -1 || right == -1) {
				if (left==-1)
					largest = right;
				else
					largest = left;
			}
			else {
				if (heap[left].compareTo(heap[right])>0) 
					largest = left;
				else 
					largest = right;
			}

			//compare largest to rootIndex, and swap if necessary
			if (heap[largest].compareTo(heap[rootIndex])>0) {
				Process tmp = heap[rootIndex];
				heap[rootIndex] = heap[largest];
				heap[largest] = tmp;

				maxHeapifyDown(largest);
			}
		}
		
	}
	
	/**
	 * Take the array and convert it into a MaxHeap.
	 */
	public void buildMaxHeap() {
		for (int i=(count-2)/2; i>=0; i--)
			maxHeapifyDown(i);
	}
	
	/**
	 * Return the number of elements in the heap
	 * @return
	 */
	public int getHeapSize() {
		return this.count;
	}
	
	/**
	 * Insert an element into the heap.
	 * @param element Element to insert
	 */
	public void insert(Process element) {
		count++;
		
		//resize array if needed
		if (count>=heap.length)
			resize();
		
		heap[count-1] = element;
//		maxHeapifyUp(count-1);
		buildMaxHeap();
	}
	
	/**
	 * Extract and return the max element (root) from the heap, and resort it.
	 * @return The max element (root) in the heap.
	 */
	public Process extract() {
		Process max = heap[0];
		heap[0] = heap[count-1];
		heap[count-1] = null;
		count--;
		maxHeapifyDown(0);
		
		return max;
	}
	
	/**
	 * Return the element at the index.
	 * @param index Index of desired element
	 * @return The desired element (null if out of bounds)
	 */
	public Process getElement(int index) {
		if (index < 0 || index >= count)
			return null;
		
		return heap[index];
	}
	
	
/*-------PRIVATE HELPER METHODS-------*/
	
	/**
	 * Resizes the array
	 */
	private void resize() {
		heap = Arrays.copyOf(heap, heap.length*2);
	}
	
	/**
	 * Returns the index of the left child of a given element.
	 * @param index The index of the parent.
	 * @return The index of the left child (-1 if index is out of bounds)
	 */
	private int leftChild(int index) {
		int leftChildIndex = 2*index+1;
		return (leftChildIndex < this.count && leftChildIndex >= 0) ? leftChildIndex : -1;
		
	}
	
	/**
	 * Returns the index of the right child of a given element
	 * @param index The index of the parent.
	 * @return The index of the right child (-1 if index is out of bounds)
	 */
	private int rightChild(int index) {
		int rightChildIndex = 2*index+2;
		return (rightChildIndex < this.count && rightChildIndex >= 0) ? rightChildIndex : -1;
	}
	
	/**
	 * Returns the index of the parent of a given element
	 * @param index The index of the child
	 * @return The index of the parent (-1 if index is out of bounds)
	 */
	private int parent(int index) {
		int parentIndex = (index-1)/2; //integer division is the same as taking the floor
		return (parentIndex < this.count && parentIndex >= 0) ? parentIndex : -1;
	}

}
