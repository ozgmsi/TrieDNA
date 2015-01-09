package nl.saxion.trie.model;

import java.util.ArrayList;

public class Data {

	private ArrayList<Integer> data;

	public Data(ArrayList<Integer> posities)
	{
		data = new ArrayList<Integer>();
		data.addAll(posities);	
	}
	
	public ArrayList<Integer> getPositions()
	{
		return this.data;
	}
	
}
