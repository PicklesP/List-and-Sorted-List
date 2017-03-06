package cs240;

public class SortedList<T> implements SortedListInterface<T> {

	Node firstNode;
	Node lastNode;
	int numElements;
	
	@Override
	public void add(T newEntry) {
		if(firstNode == null){
			firstNode = new Node(newEntry, null);
			lastNode = firstNode;
		}
		else{
			Node currentNode = firstNode;
			while((int) currentNode.getData() < newEntry && currentNode != null){
				currentNode = currentNode.getNextNode();
			}
			Node newNode = new Node(newEntry, currentNode.getNextNode());
			currentNode.setNextNode(newNode);
		}
		numElements++;
	}
	
	@Override
	public T remove(int givenPosition) {
		T item;
		if(givenPosition == 0){
			item = (T) firstNode.getData();
			firstNode = firstNode.getNextNode();
		}
		else{
			Node currentNode = firstNode;
			for(int i = 0; i < givenPosition - 1; i++){
				currentNode = currentNode.getNextNode();
			}
			item = (T) currentNode.getNextNode().getData();
			currentNode.setNextNode(currentNode.getNextNode().getNextNode());
			if(givenPosition == numElements - 1){
				lastNode = currentNode;
			}
		}
		numElements--;
		return item;
	}
	

	@Override
	public boolean remove(T anEntry) {
		boolean result = false;
		Node currentNode = firstNode;
		while(!result && currentNode != null)
			if(currentNode.getData().equals(anEntry)){
				currentNode.setNextNode(currentNode.getNextNode().getNextNode());
				result = true;
			}
		return result;
	}

	@Override
	public int getPosition(T anEntry) {
		int position = 0;
		Node currentNode = firstNode;
		while(currentNode.getData() < anEntry && currentNode != null){
			position++;
			if(currentNode.getData() == anEntry)
				position *= -1;
		}
		position *= -1;
		return position;
	}

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
		numElements = 0;
		
	}

	@Override
	public T getEntry(int givenPosition) {
		if(givenPosition >= numElements || givenPosition < 0)
			throw new IndexOutOfBoundsException();
		T item;
		Node currentNode = firstNode;
		for(int i = 0; i < givenPosition; i++)
			currentNode = currentNode.getNextNode();
		item = (T) currentNode.getData();
		return item;
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[numElements];
		Node currentNode = firstNode;
		for(int i = 0; i < numElements; i++){
			result[i] = (T) currentNode.getData();
			currentNode = currentNode.getNextNode();
		}
		return result;
	}

	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		for(int i = 0; i < numElements; i++){
			if(currentNode.getData().equals(anEntry))
				found = true;
			currentNode = currentNode.getNextNode();
		}
		return found;
	}

	@Override
	public int getLength() {
		return numElements;
	}

	@Override
	public boolean isEmpty() {
		if(firstNode == null)
			return true;
		return false;
	}

	private class Node<T> {
		private T data;
		private Node next;
		
		public Node(T item, Node nextNode){
			data = item;
			next = nextNode;
		}
		
		public Node(T item){
			this(item, null);
		}
		
		public T getData(){
			return data;
		}
		
		public void setData(T newData){
			data = newData;
		}
		
		private Node getNextNode(){
			return next;
		}
		
		private void setNextNode(Node nextNode){
			next = nextNode;
		}
	}
}

