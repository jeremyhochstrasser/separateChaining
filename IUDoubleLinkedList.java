package seperateChaining;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
	private LinearNode<T> head, tail;
	private int size;
	private int modCount;

	public IUDoubleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;

	}

	/*
	 * adds the @param element to the front of the list
	 * 
	 * @param is the element to be added to the front
	 */
	@Override
	public void addToFront(T element) {
		// TODO Auto-generated method stub
		LinearNode<T> newNode = new LinearNode<T>(element);
		newNode.setNext(head);
		head = newNode;
		if (tail == null) {
			tail = head;
		}
		size++;
		modCount++;

	}

	/** @param element to be added to the end of the Double Linked List */
	@Override
	public void addToRear(T element) {
		// TODO Auto-generated method stub
		LinearNode<T> newNode = new LinearNode<T>(element);
		if (isEmpty()) {
			head = newNode;
		} else {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
		}
		tail = newNode;
		size++;
		modCount++;

	}

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the rear of the list
	 */
	@Override
	public void add(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
		}
		tail = newNode;
		size++;
		modCount++;

	}

	/**
	 * Adds the specified element after the specified target.
	 *
	 * @param element the element to be added after the target
	 * @param target  the target is the item that the element will be added after
	 * @throws NoSuchElementException if target element is not in this list
	 */
	@Override
	public void addAfter(T element, T target) {
		LinearNode<T> targetNode = head;
		while (targetNode != null && !targetNode.getElement().equals(target)) {
			targetNode = targetNode.getNext();
		}
		if (targetNode == null) {// no such element
			throw new NoSuchElementException();
		}
		LinearNode<T> newNode = new LinearNode<T>(element);
		newNode.setNext(targetNode.getNext());
		newNode.setPrevious(targetNode);
		targetNode.setNext(newNode);
		if (targetNode != tail) {
			newNode.getNext().setPrevious(newNode);
		}
	}

	/**
	 * Inserts the specified element at the specified index.
	 * 
	 * @param index   the index into the array to which the element is to be
	 *                inserted.
	 * @param element the element to be inserted into the array
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index > size)
	 */
	@Override
	public void add(int index, T element) {
		// without using the iterator to make this method work

//		LinearNode<T> newNode = head;
//		newNode.setElement(element);
//		if (index < 0 || index > size) {
//			throw new NoSuchElementException();
//		}
//		if (index == 0) {
//			if (isEmpty()) {
//				tail = newNode;
//			} else {
//				head.setPrevious(newNode);
//				newNode.setPrevious(head);
//			}
//			head = newNode;
//		} else {
//			LinearNode<T> current = head;
//			int i = 0;
//			while (i < index) {
//				current.getNext();
//				i++;
//			}
//			current.setNext(newNode);
//			newNode.getNext().setPrevious(newNode);
//
//			newNode.setPrevious(current);
//			if (current != tail) {
//				newNode.setNext(current.getNext());
//			} else {
//				tail = newNode;
//			}
//		}

		// using the iterator
		ListIterator<T> lit = this.listIterator(index);
		lit.add(element);
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		if (head == null) {
			throw new NoSuchElementException();
		}
		LinearNode<T> retVal = new LinearNode<T>(head.getElement());
		head = head.getNext();
		size--;
		modCount++;

		return retVal.getElement();
	}

	/**
	 * Removes and returns the first element from this list.
	 * 
	 * @return the first element from this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = tail.getElement();
		tail.setElement(null);
		tail = tail.getPrevious();

		if (tail != null) {
			tail.setNext(null);
		} else {
			head = null;
		}
		size--;
		modCount++;

		return retVal;
	}

	/**
	 * Removes and returns the first element from the list matching the specified
	 * element.
	 *
	 * @param element the element to be removed from the list
	 * @return removed element
	 * @throws NoSuchElementException if element is not in this list
	 */
	@Override
	public T remove(T element) {
		// TODO Auto-generated method stub no list iterator
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		int currentIndex = 0;
		T retVal = null;
		boolean found = false;
		LinearNode<T> currentNode = head;
		while (found == false && currentIndex != size) {
			if (!currentNode.getElement().equals(element)) {
				currentNode = currentNode.getNext();
				currentIndex++;
			} else {
				found = true;
				retVal = currentNode.getElement();
			}
		}
		if (found == false) {
			throw new NoSuchElementException();
		}
//		if (head == tail) {
//			head = tail = null;
//		} else if (currentNode == head) {
//			currentNode.setPrevious(null);
//			currentNode.setNext(head);
//			head = currentNode.getNext();
//		} else if (currentNode == tail) {
//			
//			currentNode.setNext(null);
//			currentNode.setPrevious(tail);
//			tail = currentNode;
//		} else {
//			currentNode.getNext().setPrevious(currentNode.getPrevious());
//			currentNode.getPrevious().setNext(currentNode.getNext());
//		}
		///////////////////////////////////////////////////////////////////////////////////
//		if (currentNode == head) {
//			currentNode.getNext().setPrevious(null);
//			head = currentNode.getNext();
//		} else if (currentNode == tail) {
//			currentNode.getPrevious().setNext(null);
//			currentNode = null;
//		} else {
//			currentNode.getPrevious().setNext(currentNode.getNext());
//			currentNode.getNext().setPrevious(currentNode.getPrevious());
//			currentNode = null;
//		}
		return retVal;

	}

	/**
	 * Removes and returns the element at the specified index.
	 *
	 * @param index the index of the element to be retrieved
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size)
	 */
	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		LinearNode<T> current = head;
		LinearNode<T> returnNode = head;

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (head == null || tail == null) {
			throw new NoSuchElementException();
		}
		if (head == tail) {
			returnNode = head;
			head = tail = null;
			return returnNode.getElement();
		}

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		returnNode = current;
		if (head == tail) {
			head = tail = null;
		} else if (current == head) {
			current.setPrevious(null);
			current.setNext(head);
			head = current.getNext();
		} else if (current == tail || current.getPrevious() == null) {

			current.setNext(null);
			current.setPrevious(tail);
			tail = current;
		} else {
			current.getNext().setPrevious(current.getPrevious());
			current.getPrevious().setNext(current.getNext());
		}
		size--;
		modCount++;
		return returnNode.getElement();

//		//using the iterator would work as such

//		if (index < 0 || index >= size) {
//			throw new IndexOutOfBoundsException();
//		}
//		ListIterator<T> listIter = this.listIterator(index);
//		T rom = listIter.next();
//		listIter.remove();
//		
//		modCount++;
//		size--;
//		return rom;
//		

	}

	/**
	 * Replace the element at the specified index with the given element.
	 *
	 * @param index   the index of the element to replace
	 * @param element the replacement element to be set into the list
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size)
	 */
	@Override
	public void set(int index, T element) {
		// TODO Auto-generated method stub
		LinearNode<T> current = head;

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (head == tail) {
			head = current;
			tail = current;

		} else {
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			current.setElement(element);
		}

		modCount++;

	}

	/**
	 * Returns a reference to the element at the specified index.
	 *
	 * @param index the index to which the reference is to be retrieved from
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size)
	 */
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		LinearNode<T> current = head;
		if ((index < 0 || index >= size)) {
			throw new IndexOutOfBoundsException();
		}

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		return current.getElement();
	}

	/**
	 * Returns a reference to the first element in this list.
	 *
	 * @return a reference to the first element in this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public int indexOf(T element) {
		// TODO Auto-generated method stub
		LinearNode<T> current = head;
		int xindex = 0;
		while (current != null && !current.getElement().equals(element)) {
			xindex++;
			current = current.getNext();
		}
		if (current == null) {
			return -1;
		}
		return xindex;
	}

	/**
	 * Returns a reference to the first element in this list.
	 *
	 * @return a reference to the first element in this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T first() {
		// TODO Auto-generated method stub
		if (head == null) {
			throw new NoSuchElementException();

		}
		return head.getElement();
	}

	/**
	 * Returns a reference to the last element in this list.
	 *
	 * @return a reference to the last element in this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T last() {
		// TODO Auto-generated method stub
		if (head == null) {
			throw new NoSuchElementException();

		}
		return tail.getElement();
	}

	/**
	 * Returns true if this list contains the specified target element.
	 *
	 * @param target the target that is being sought in the list
	 * @return true if the list contains this element, else false
	 */
	@Override
	public boolean contains(T target) {
		// TODO Auto-generated method stub
		boolean isFound = false;
		LinearNode<T> current = head;

		if (head == null) {
			return isFound;
		}
		while (current.getNext() != null && current.getElement() != target) {
			current = current.getNext();
		}
		if (current.getElement() == target) {
			isFound = true;
		}

		return isFound;
	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub

		return (head == null);
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the integer representation of number of elements in this list
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub

		return size;
	}

	/**
	 * Returns a string representation of this list.
	 *
	 * @return a string representation of this list
	 */
	public String toString() {
		StringBuilder strB = new StringBuilder();
		strB.append("[");
		for (T element : this) {
			strB.append(element.toString());
			strB.append(", ");
		}
		if (!isEmpty()) {
			strB.delete(strB.length() - 2, strB.length());
		}
		strB.append("]");
		return strB.toString();
	}

	/**
	 * Returns an Iterator for the elements in this list.
	 *
	 * @return an Iterator over the elements in this list
	 */
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return this.listIterator();
	}

	/**
	 * Returns a ListIterator for the elements in this list.
	 *
	 * @return a ListIterator over the elements in this list
	 *
	 * @throws UnsupportedOperationException if not implemented
	 */
	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return new DLLIterator();
	}

	/**
	 * Returns a ListIterator for the elements in this list, with the iterator
	 * positioned before the specified index.
	 *
	 * @return a ListIterator over the elements in this list
	 *
	 * @throws UnsupportedOperationException if not implemented
	 */
	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		// TODO Auto-generated method stub
		return new DLLIterator(startingIndex);
	}

	// Double Linked List Iterator

	/**
	 * This is the DLL Iterator class it is implementing the ListIterator methods it
	 * also contains some class variables to help with the functionality
	 * 
	 * @author Jeremy Hochstrasser
	 * @course cs221 - 1
	 */
	private class DLLIterator implements ListIterator<T> {
		private LinearNode<T> nextNode;
		private int iterModCount;
		private int nextIndex;
		private LinearNode<T> lastReturned;

		// make constructor
		public DLLIterator() {
//			iterModCount = 0;
//			nextIndex = 0;
//			iterModCount = modCount;
			this(0);
			// this is a special case of DLLiterator(index)?
		}

		public DLLIterator(int startingIndex) {
			// TODO how do we start the iterator somewhere other than in front of head?
			// do we care what starting index is passed in?
			if (startingIndex < 0 || startingIndex > size) {
				throw new IndexOutOfBoundsException();
			}
			lastReturned = null;
			nextNode = head;
			for (int i = 0; i < startingIndex; i++) {
				nextNode = nextNode.getNext();
			}
			nextIndex = startingIndex;
			iterModCount = modCount;
		}

		/**
		 * Returns a boolean if the current Iterator has a next element in the DLL in
		 * other words, returns true if the current iterator is NOT located at the tail
		 * of the DLL
		 * 
		 * @throws concurrentModification Exception if the DLL is modified while there
		 *                                is and active iterator on the DLL
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextNode != null;
		}

		/**
		 * Returns the next element to be returned from the current iterator located in
		 * the current DLL iterator.
		 * 
		 * @throws concurrentModification Exception if the DLL is modified while there
		 *                                is and active iterator on the DLL
		 */
		@Override
		public T next() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T temp = nextNode.getElement();
			lastReturned = nextNode;
			nextNode = nextNode.getNext();
			return temp;
		}

		/**
		 * Returns a boolean if the current Iterator has a previous element in the DLL
		 * in other words, returns true if the current iterator is NOT located at the
		 * head of the DLL
		 * 
		 * @throws concurrentModification Exception if the DLL is modified while there
		 *                                is and active iterator on the DLL
		 */
		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextNode != head;
		}

		/**
		 * Returns the previous index returned from the current iterator located in the
		 * current DLL iterator.
		 * 
		 * @throws concurrentModification Exception if the DLL is modified while there
		 *                                is and active iterator on the DLL
		 */
		@Override
		public T previous() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if (nextNode == null) {
				nextNode = tail;
			} else {
				nextNode = nextNode.getPrevious();
			}
			lastReturned = nextNode;
			return nextNode.getElement();
		}

		/**
		 * Returns the next index to be returned from the current iterator located in
		 * the current DLL iterator.
		 * 
		 * @throws concurrentModification Exception if the DLL is modified while there
		 *                                is and active iterator on the DLL
		 */
		@Override
		public int nextIndex() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex - 1;
		}

		/**
		 * removes the element that was most recently returned from the Iterator from
		 * the DLL
		 * 
		 * @throws concurrentModification Exception if the DLL is modified while there
		 *                                is and active iterator on the DLL
		 */
		@Override
		public void remove() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (lastReturned == null) {
				throw new IllegalStateException();
			}
			if (lastReturned == tail) {
				tail = lastReturned.getPrevious();
			} else {
				lastReturned.getNext().setPrevious(lastReturned.getPrevious());
			}

			if (lastReturned == head) {
				head = lastReturned.getNext();

			} else {
				lastReturned.getPrevious().setNext(lastReturned.getNext());
			}
			if (nextNode == lastReturned) {
				nextNode = nextNode.getNext();
			}
			lastReturned = null;
			size--;
			modCount++;
			iterModCount++;
			nextIndex--;

		}

		/**
		 * sets the specified element at the specified index of the iterator.
		 * 
		 * @param e the new element to change the element at the index too
		 * @throws concurrentModification Exception if the DLL is modified while there
		 *                                is and active iterator on the DLL
		 */
		@Override
		public void set(T e) {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (lastReturned == null) {
				throw new IllegalStateException();
			}
			lastReturned.setElement(e);
			modCount++;
			iterModCount++;

		}

		/**
		 * Inserts the specified element at the specified index of the iterator.
		 * 
		 * @param e the element to be inserted into the array
		 * @throws concurrentModification Exeption if the DLL is modified while there is
		 *                                and active iterator on the DLL
		 */
		@Override
		public void add(T e) {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			LinearNode<T> newNode = new LinearNode<T>(e);
			// give the newnode the correct adresses
			newNode.setNext(nextNode);

			// make the other nodes notice the new node

			if (nextNode != null) {
				newNode.setPrevious(nextNode.getPrevious());
				newNode.setPrevious(newNode);
			} else {
				tail = newNode;
			}
			if (newNode != head && nextNode != null) {
				newNode.getPrevious().setNext(newNode);
			} else {
				head = newNode;
			}

			nextIndex++;
			size++;
			modCount++;
			iterModCount++;

		}

	}

}
