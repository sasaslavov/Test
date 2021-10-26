package bubbleSort;

public class Potez {
	private int from;
	private int to;
	
	public Potez(int from, int to) {
		super();
		this.from = from;
		this.to = to;
	}
	
	public int getFrom() {
		return from;
	}
	
	public int getTo() {
		return to;
	}
	
	@Override
	public String toString() {
		return from + " - " + to;
	}
}
