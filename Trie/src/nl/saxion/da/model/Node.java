package nl.saxion.da.model;

import java.util.ArrayList;
import java.util.List;

/**
 * De class node, deze class is niet afhankelijk van andere classes.
 * Omdat deze class op zichzelf werkt, zijn er gebruik gemaakt van recursive methodes om dieper te "graven"
 */
public class Node {
	
	private static int lastassignedNumber = 1;
	
	/* Hoeveel van dit woord komt er voor ? */
	private int frequency;
	
	/* Eventuele nodeID */
	private int nodeID;
	
	/* The value dat behoort tot deze node */
	private String value;
	
	/* Is de value van deze nog een woord(Het eind van een woord ) */
	private boolean isWord = false;
	
	/* De childenodes die deze node heeft */
	public List<Node> children;
	
	/* De data dat bij deze node hoort */
	private List<Data> data;
	
	//TODO REMOVE HELPER
	private Node helper;
	
	/* Constructor */
	public Node() {
		data = new ArrayList<Data>();
		children = new ArrayList<Node>();
		frequency = 0;
		nodeID = lastassignedNumber++;
	}

	/* Contructor with args */
	public Node(String value, boolean isword, ArrayList<Integer> positie) {
		this.value = value;
		this.isWord = isword;
		this.nodeID = lastassignedNumber++;
		this.data = new ArrayList<Data>();
		this.data.add(new Data(positie));
		this.children = new ArrayList<Node>();
	}

	public String getValue() {
		return value;
	}

	public boolean hasChildren() {
		if (children != null) {
			return children.size() > 0;
		} else
			return false;
	}

	public boolean isMatch(String find)
	{
		return value.equals(find);
	}
	
	
	public ArrayList<Data> search(String s){
		return search(s, new ArrayList<Data>());
	}
	
	public ArrayList<Node> getPath(String s){
		return searchPath(s, new ArrayList<Node>());
	}
	
	private void setFrequency(int frequency){
		this.frequency = frequency;
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	/** TODO IMPLEMENT SEARCH PATH **/
	public ArrayList<Node> searchPath(String s, ArrayList<Node> output){
		
		if (isWord){
			this.setFrequency(this.getFrequency()+1);
			return output;
		}
		
		if (!s.isEmpty()){
			
			if (isWord){
				frequency++;
			}
			
			if (hasChild(s.substring(0, 1))){
				
				Node child = getChild(s.substring(0, 1));
				
				output.add(child);
				
				/* Ga diepen in de trie en doe hetzelfde ook voor de child nodes */
				return child.searchPath(s.substring(1, s.length()), output);
			}
		}
		/* Als het woord niet is gevonden wordt er een lege lijst gegevens */
		/* Het woord is pas gevonden als isWord true is, zo is het niet mogelijk om niet op woorden te zoeken die niet bestaan */
		return null;
	}
	
	private ArrayList<Data> search(String s, ArrayList<Data> output){
		/* Als het punt is bereikt of dit een woord is, geef de output met posities terug */
		if (isWord){
			return output;
		}
		
		/* Recursive : De input s wordt steeds verkleind, dit wordt gedaan zolang de lengte van s niet gelijk is aan 0 */
		if (!s.isEmpty()){
			/* Is er een childnode met de eerste character van de input string */
			if (hasChild(s.substring(0, 1))){
				/* Haal dan het kind op */
				Node child = getChild(s.substring(0, 1));
				
				/* Voeg child childnode data (nodig voor positites) dan toe aan de output */
				output.addAll(child.data);
				
				/* Doe een recursive call naar de childnode en voer het bovenste uit */
				return child.search(s.substring(1, s.length()), output);
			}
		}
		/* Null als het woord niet is gevonden */
		return null;
	}
	
	
	public  void delete(String s)
	{
		ArrayList<Node> nodes;
		// zoek of de string er is
		ArrayList<Data> positions = (ArrayList<Data>) search(s, new ArrayList<Data>());
		if(positions!=null)
		{
			nodes = new ArrayList<Node>();
			
			for(Data d : positions)
			{
				ArrayList<Integer> posities = d.getPositions();
				for(Integer i : posities)
				{
				  nodes.add(getNodebyPosition(i));
				}
			}
		}
		else
		{
			System.out.println("woord kan je niet deleten want bestaat niet");
		}
	}


	
	private Node getChildbyId(int ID)
	{
		if(nodeID == ID)
		{
			return this;
		}
		
		for(Node child : children)
		{
			return child.getChildbyId(ID);
			
		}
		return null;
	}
	
	public void insert(String s , Data d)
	{
		if (search(s) != null){
			searchPath(s, new ArrayList<Node>());
			
		}
		insertReal(s,d);
	}
	
	/**
	 * Bekijkt of het woordt in de trie opgeslagen staat
	 * @param s het woord waar naar gezocht wordt
	 * @return true als woord bestaat in de trie
	 */
	public boolean bestaatWoord(String s){
		return !search(s).isEmpty();
	}
	
	/**
	 * De methode dat een string in de trie opslaat
	 * Door middel van een substring(recursief) wordt van elk letter gekeken of het letter(child node) bestaat of niet
	 * Als de childnode niet bestaat wordt de rest van de string in één node opgeslagen (Reduced trie)
	 * @param s De input string
	 * @param d De data dat over de string gaat, in dit geval de positites er van
	 */
	private void insertReal(String s, Data d) {
		if(s.isEmpty())
		{
			return;
		}
		if (s.length() != 0) {
			String character = s.substring(0, 1);
			Integer positie = d.getPositions().remove(0);
			ArrayList<Integer> posities = new ArrayList<Integer>(); posities.add(positie);
			if (!hasChild(character)) {
				if(hasChilds())
				{
					System.out.println("has childs, character could be in child " + character);
					Node node = getChild(character);
					
					if(node==null && children.size()==1 && children.get(0).value.equals(character)) //collision
					{
						String alles="";
						for(Node child : children)
						{
							if(child!=null)
							{
							  	
							 alles +=  getValue(child);
							}
						}
						System.out.println("collision detected split edit these words first " + alles);
						//hasCollapse(alles);
						collapse(this);
						insertReal(s,d);
						
				
					}
					else if(node==null)
					{
						System.out.println("not found child " + character);
						children.add(new Node(character,false,posities));
						System.out.println("Inserted child " + character);
					}
					else
					{
						System.out.println("already exist" + character);
					}
				}
				else
				{
				if(s.length()==1){children.add(new Node(character,true, posities));}
				else
				{
					children.add(new Node(character,false,posities));
				}
				System.out.println("Inserted child " + character);
				}
			}
			else	
			{
			System.out.println("not inserted child " + character);}
			Node node = getChild(character);
			String newInsert = s.substring(1, s.length());
			if(node!=null)
			{
			node.insertReal(newInsert,d);
			}
		}
	
	}
	
	
	/**
	 * Bekijkt of deze node child nodes heeft 
	 * @see insert()
	 * @see getChild()
	 * @return true wanneer 1 of meer childnodes aanwezig zijn van deze node
	 */
	public boolean hasChilds() {
		/* Check of de childeren array niet null is */
		if (children != null) {
			/* Base case => zijn er chil nodes? */
			return children.size() > 0;
		} else {
			return false;
		}
	}

	/**
	 * Haal een child op van deze node
	 * @param s De string waarmee gewerkt wordt, de eerste character zal worden gecheckt
	 * @See hasChild(), dekt deze methode
	 * @return De childnode van deze class
	 */
	public Node getChild(String s) {
		/* Doorloop de childeren van deze node */
		for (Node node : children) {
			/* Als value gelijk is aan de character met die van de childnode, geef dan deze child terug */
			if (node.value.equals(s) || node.value.charAt(0) == s.toCharArray()[0]) {
				return node;
			}
		}
		return null;
	}
	
	
	public boolean hasChild(String s) {
		if(children != null)
		{
		for (Node node : children) {
			if (node.value.equals(s)) {
				return true;
			}
		}
		}
		return false;
	}
	
	 
	/**
	 * Geeft een representatie van de node class en zijn child nodes 
	 */
	public void prettyPrint() {
		/* Loop trough childer of this(parent) node */
		String childs = "Parent : " + this.value;
		
		/* Concat the childnode values */
		childs += " Childs : [ ";
		if (children != null) {
			
			for (Node n : children) {
				if (n != null) {
					
					/* Geef de value mee */
					childs += n.value;
					
					/* Geef aan of het een woord is */
					if (n.isWord) {
						childs += " is een woord ";
					}
				}
			}
			childs += " ]";

			/* Print actie */
			System.out.println(childs);

			/* Print recursive de childnodes */
			for (Node n : children) {
				n.prettyPrint();
			}
		}

	}
	
	 private void hasCollapse(String s)
	 {
		 
//		 int first_occur_position = -1;
//			BoyerMooreAlgoritme bm = new BoyerMooreAlgoritme(s);
//			 first_occur_position = bm.search("");
//			System.out.println("The text '" + s + "' is first found after the " 
//	                                    + first_occur_position + " position.");
//			if(first_occur_position==-1)
//			{
//				return false;
//			}
//			else{ return true;}
	 }
	 private void collapse(Node node)
	 {
		 if(node.children.size() ==1 && isLeaf(node.children.get(0)))
		 {
			 node.value += node.children.get(0).value;
			// this.data.add(arg0)
			 node.children = null;
			 node.isWord=true;
			 System.out.println("gevonden");
			
		 }
		 else
		 {
			 if(node.hasChildren())
			 {
				 for(Node child : node.children)
				 {
					collapse(child);
					
				 }
				 
			 }
		 }
	 }
	 
	 private String getValue(Node data)
	 {
		 String status = "";
		 if(data.value !=null)
		 {
			  status = data.value;
		 }
		 if(data.hasChildren())
		 {
			for(Node node : data.children)
			{
				status = getValue(status,node);
			}
		 }
//		 if(data.value!= null)
//		 {
//			 System.out.println("tekst die gewijzigd moet worden " + data.value); 
//			 return getValue(data.value,data);
//		 }
	     return status;
	 }
	 
	 private String getValue(String status,Node data)
	 {
		 if(data.value!=null)
		 {
			 status += data.value;
		 }
		 if(data.hasChildren())
		 {
			for(Node node : data.children)
			{
			  status = getValue(status ,node);
			}
		 }
	     return status;
	 }
	 
	 /**
	  * Geeft aan of deze node een blad is
	  * @param de node waarvan informatie nodig is
	  * @return true als de de argument node een leaf is
	  */
	 public boolean isLeaf(Node node)
	 {
		 if(node.children != null)
		 {
		 return node.children.size()==0;
		 }
		 else return true;
	 }
	 
	 public Node getNodebyPosition(int position)
	 {
		 return null;
	 }
	 public boolean isWord()
	 {
		 return this.isWord;
	 }
}
