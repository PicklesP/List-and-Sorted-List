package cs240;

public class ListArray<T> implements ListInterface<T> {

	T[] list;
	int numElements;
	
	public ListArray(){
		list = (T[]) new Object[10];
		numElements = 0;
	}
	
	@Override
	public void add(T newEntry) {
		if(numElements == 10)
			throw new IndexOutOfBoundsException("The list is currently full.");
		list[numElements] = newEntry;
		numElements++;
	}

	@Override
	public void add(int newPosition, T newEntry) {
		if(numElements == 10)
			throw new IndexOutOfBoundsException("The list is currently full.");
		if(newPosition > 10 || newPosition < 0)
			throw new IndexOutOfBoundsException("The new position doesn't exist in the list");
		for(int i = 9; i >= newPosition; i--){
			list[i] = list[i - 1];
		}
		list[newPosition] = newEntry;
		numElements++;
	}

	@Override
	public T remove(int givenPosition) {
		if(givenPosition > 10 || givenPosition < 0)
			throw new IndexOutOfBoundsException("The new position doesn't exist in the list");
		T item = list[givenPosition];
		for(int i = givenPosition; i < numElements; i++)
			list[i] = list[i + 1];
		list[9] = null;
		numElements--;
		return item;
	}

	@Override
	public void clear() {
		for(int i = 0; i < list.length; i++)
			list[i] = null;
		numElements = 0;
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		if(givenPosition > numElements || givenPosition < 0)
			throw new IndexOutOfBoundsException("The new position doesn't exist in the list");
		T item = list[givenPosition];
		list[givenPosition] = newEntry;
		return item;
	}

	@Override
	public T getEntry(int givenPosition) {
		if(givenPosition > numElements || givenPosition < 0)
			throw new IndexOutOfBoundsException("The new position doesn't exist in the list");
		return list[givenPosition];
	}

	@Override
	public T[] toArray() {
		return list;
	}

	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		for(int i = 0; i < numElements; i++)
			if(list[i].equals(anEntry))
				found = true;
		return found;
	}

	@Override
	public int getLength() {
		return numElements;
	}

	@Override
	public boolean isEmpty() {
		return numElements == 0;
	}

	
}
