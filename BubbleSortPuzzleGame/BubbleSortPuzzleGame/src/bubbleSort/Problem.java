package bubbleSort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Problem {
	private StopWatch all = new StopWatch("all");
	private StopWatch possibleMoves = new StopWatch("possibleMoves");
	
	private boolean solutionFound = false;
	int br = 1;
	
	// Tabela transpozicija - kolekcija vec vidjenih/obradjenih pozicija
	private List<String> transpositionTable = new ArrayList<>();
	
	public Problem(String fileName) throws IOException{
		all.start();
		Pozicija pozicija = load(fileName);
		solve(pozicija);
		all.add();
		System.out.println(all.reportDuration());
		System.out.println(possibleMoves.reportDuration());
	}

	private Pozicija load(String fileName) throws IOException {
		Pozicija pozicija = new Pozicija();
		List<String> lines = Files.readAllLines(Paths.get(".", fileName));
		for ( String line : lines ) {
			line = line.trim();
			if ( line.length() == 0 ) continue;
			
			Epruveta epruveta = new Epruveta();
			for ( char c : line.toCharArray() ) {
				if ( c != '.') {
					Kuglica kuglica = new Kuglica(c);
					epruveta.push(kuglica);
				}
			}
			pozicija.add(epruveta);
		}
		return pozicija;
	}
	
	private void solve(Pozicija pozicija) {
		if ( transpositionTable.contains(pozicija.toString())) return;
		transpositionTable.add(pozicija.toString());
		
		System.out.println(pozicija + " - " + br++);
		if ( pozicija.isSolved() ) {
			solutionFound = true;
			System.out.println("Reseno");
			return;
		}
		
		
		possibleMoves.start();
		List<Potez> potezi = pozicija.getPossibleMoves();
		possibleMoves.add();
		if ( potezi.isEmpty() ) return;
		
		for (Potez potez : potezi) {
//			System.out.println(pozicija);
			Pozicija newPosition = pozicija.play(potez);
//			System.out.println(potez);
			solve(newPosition);
			if ( solutionFound ) break;
		}
	}
}
