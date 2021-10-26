package bubbleSort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pozicija {
	private List<Epruveta> epruvete;

	public Pozicija() {
		epruvete = new ArrayList<>();
	}
	
	public void add(Epruveta epruveta) {
		epruvete.add(epruveta);
	}
	
	public Epruveta getEpruveta(int index) {
		return epruvete.get(index);
	}
	
	public int size() {
		return epruvete.size();
	}
	
	@Override
	public String toString() {
		return epruvete.stream().map(e -> e.toString()).collect(Collectors.joining("/"));
	}
	
	public List<Potez> getPossibleMoves() {
		List<Potez> potezi = new ArrayList<>();
		for (int from = 0; from < epruvete.size(); from++) {
			if( epruvete.get(from).isEmpty() ) continue;
			// potkresivanje stabla
			if ( epruvete.get(from).isSolved() ) continue;
			
			// ako ima 3 kuglice iste boje onda se ne dira
			if ( epruvete.get(from).size() == 3 && epruvete.get(from).isJednobojna() ) continue;

			for (int to = 0; to < epruvete.size(); to++) {
				if ( from == to ) continue;
				if ( epruvete.get(to).isFull() ) continue;
				
				
				// ako je na primer raspored kuglica u epruveti from cpp. a u to epruveti zzp, ne prebacivati p iz from u to 
				if ( epruvete.get(from).size() > 1 && epruvete.get(from).numberKuglicaInARow() > 1 && epruvete.get(to).size() > epruvete.get(to).capacity - epruvete.get(from).numberKuglicaInARow() ) continue;
				
				// ako epruveta nije prazna, onda boja kuglica from mora biti ista kao top kuglica iz to
				if ( !epruvete.get(to).isEmpty() ) {
					if ( epruvete.get(from).getTopKuglica().getBoja() != epruvete.get(to).getTopKuglica().getBoja() ) continue;
				}
				
				potezi.add(new Potez(from, to));
			}
		}
		return potezi;
	}
	
	public Pozicija play(Potez potez) {
		Pozicija newPosition = new Pozicija();
		for (int i = 0; i < epruvete.size(); i++) {
			Epruveta nova = new Epruveta();
			newPosition.add(nova);
		}
		
		for (int epruvetaIndex = 0; epruvetaIndex < epruvete.size(); epruvetaIndex++) {
			for (int kuglicaIndex = 0; kuglicaIndex < epruvete.get(epruvetaIndex).size(); kuglicaIndex++) {
				newPosition.getEpruveta(epruvetaIndex).push(epruvete.get(epruvetaIndex).get(kuglicaIndex));
			}
		}
		newPosition.epruvete.get(potez.getTo()).push(newPosition.epruvete.get(potez.getFrom()).pop());
		return newPosition;
	}
	
	public boolean isSolved() {
		return !epruvete.stream().anyMatch(e -> !e.isSolved());
	}
}
