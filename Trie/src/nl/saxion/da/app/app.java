package nl.saxion.da.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nl.saxion.da.model.Data;
import nl.saxion.da.model.Node;

public class app {
	public static void main(String args[]) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("File.txt"));
		Node boom = new Node();
		int positie = 0;
		while (scanner.hasNext()) {

			String woord = scanner.next().toLowerCase();
			ArrayList<Integer> posities = new ArrayList<Integer>();
			for (int counter = 0; counter < woord.length(); counter++) {
				posities.add(positie++);
			}
			System.out.println(posities.toString());

			boom.insert(woord, new Data(posities));
			System.out.print(woord + "\r\n");
			positie++;
		}
		scanner.close();
		boom.prettyPrint();

		System.out.println("Start Search");

		/* Search op de boom naar een string en verkrijg de data array terug */
		List<Data> search = boom.search("ozgur");
		
		/* Check of string bestaat methode */
		System.out.println(boom.bestaatWoord("ozgur"));

		if (search != null) {
			for (Data data : search) {
				System.out.println(data.getPositions());
			}
			System.out.println("Woord gevonden");
		} else {
			System.out.println("Woord niet gevonden");
		}

		System.out.print(" \r programming is done");
	}
}
