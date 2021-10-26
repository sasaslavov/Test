package bubbleSort;

import java.util.Stack;

public class Epruveta {
	private Stack<Kuglica> kuglice;
	public int capacity = 4;
	
	public Epruveta() {
		kuglice = new Stack<>();
	}
	
	public void push(Kuglica kuglica) {
		if ( kuglice.size() < capacity ) {
			kuglice.push(kuglica);
		}
	}
	
	public Kuglica pop() {
		return kuglice.pop();
	}
	
	public Kuglica getTopKuglica() {
		return kuglice.peek();
	}
	
	public int size() {
		return kuglice.size();
	}
	
	public Kuglica get(int index) {
		return kuglice.get(index);
	}
	
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < size(); i++) {
			text.append(kuglice.get(i).getBoja());
		}
		for (int i = size(); i < capacity; i++) {
			text.append('.');
		}
		return text.toString();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public boolean isFull() {
		return size() == capacity;
	}
	
	public boolean isSolved() {
		if ( isEmpty() ) return true;
		if ( isFull() && isJednobojna() ) return true;
		return false;
	}

	public boolean isJednobojna() {
		for (int i = 0; i < size(); i++) {
			if ( kuglice.get(i).getBoja() != kuglice.get(0).getBoja() ) {
				return false;
			}
		}
		return true;
	}
	
	public int numberKuglicaInARow() {
		int counter = 0;
		for (int i = size() - 1; i >= 0; i--) {
			if (kuglice.get(i).getBoja() == kuglice.get(size() - 1).getBoja()) {
				counter++;
			} else {
				break;
			}
		}
		return counter;
	}
}
