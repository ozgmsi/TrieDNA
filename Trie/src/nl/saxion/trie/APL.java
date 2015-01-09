package nl.saxion.trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nl.saxion.trie.model.Data;
import nl.saxion.trie.model.Node;

public class APL {
	public static void main(String args[]) throws FileNotFoundException {
		
		/* Init scanner */
		Scanner scanner = new Scanner(new File("File.txt"));
		
		/* Maak een nieuwe node aan waarmee gewerkt zal worden */
		Node boom = new Node();
		
		/* Om de positites mee te geven */
		int positie = 0;
		
		while (scanner.hasNext()) {
			
			/* Verklein woord */
			String woord = scanner.next().toLowerCase();
			
			/* Nodig om posities op te slaan */
			ArrayList<Integer> posities = new ArrayList<Integer>();
			
			/* Voeg de positites toe in de lijst */
			for (int counter = 0; counter < woord.length(); counter++) {
				posities.add(positie++);
			}
			
			/* Voeg het woord in de trie en geef met het Data object de posities array */
			boom.insert(woord, new Data(posities));
			
			/* Verhoog posities */
			positie++;
		}
		/* Post */
		scanner.close();
		
		/* Print de boom recursief */
		boom.prettyPrint();

		List<Data> search = boom.search("ozgur");
		System.out.println(boom.getWordFrequency("ozgur"));
		System.out.println(boom.getWordFrequency("ralph"));
		System.out.println(boom.bestaatWoord("aidfhawiuodh"));
		
		/* Check of string bestaat methode */
		System.out.println(boom.bestaatWoord("aap"));
		
		System.out.println(boom.search("aap"));
		

		System.out.print(" \r programming is done");
	}
}
