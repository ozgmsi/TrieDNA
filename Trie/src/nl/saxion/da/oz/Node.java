package nl.saxion.da.oz;

import java.util.ArrayList;

import nl.saxion.da.model.Data;

public class Node {

	private ArrayList<Node> childeren;
	private String data;
	private Data nodeData;
	private boolean isWord;

	public Node() {
		childeren = new ArrayList<Node>();
		data = " ";
	}

	public Node(String newData) {
		data = newData;
		childeren = new ArrayList<Node>();
	}

	public void insert(String input, Data data) {
		if (!input.isEmpty()) {
			String firstChar = input.substring(0, 1);
			if (!hasChild(firstChar)){
								
			}
		}
	}

	private void expand() {
		insert(this.data, this.nodeData);
	}
	
	public void setData(String s){
		this.data = s;
	}

	private boolean expandable() {
		return data.length() > 1;
	}

	private boolean hasChild(String character) {
		for (Node childNode : childeren) {
			if (childNode.data.equals(character)) {
				return true;
			}
		}
		return false;
	}

	private boolean isLeaf() {
		return childeren.isEmpty();
	}

	private Node getChild(String character) {

		for (Node child : childeren) {
			if (child.data.equals(character)) {
				return child;
			}
		}
		return null;
	}

	private void addChild(Node node) {
		childeren.add(node);
	}

	public void print() {

		/* Loop trough childer of this(parent) node */
		String childs = "Parent : " + this.data;
		childs += " Childs : [ ";

		for (Node n : childeren) {
			childs += n.data;
			if (n.isWord) {
				childs += " is een woord ";
			}

		}

		childs += " ]";

		System.out.println(childs);

		for (Node n : childeren) {
			n.print();
		}
	}

}
