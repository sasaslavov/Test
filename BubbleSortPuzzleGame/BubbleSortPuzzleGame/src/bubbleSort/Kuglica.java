package bubbleSort;

public class Kuglica {
	private char boja;

	public Kuglica(char boja) {
		super();
		this.boja = boja;
	}

	public char getBoja() {
		return boja;
	}
	
	@Override
	public String toString() {
		return String.valueOf(boja);
	}
}
