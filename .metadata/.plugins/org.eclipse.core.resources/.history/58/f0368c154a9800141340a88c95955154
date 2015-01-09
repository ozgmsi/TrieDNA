package nl.saxion.da.model;
import java.util.ArrayList;
public class Trie {



	
		private Node root;

		public Trie() {
			root = new Node(" ");
			root.data = null;
		}

		public void add(String s) {
			root.insert(s);
		}

		private class Node {

			public String data;
			public ArrayList<Node> childeren;

			public Node(String data) {
				childeren = new ArrayList<Node>();
				this.data = data;
			}

			public void insert(String s) {
				insert2(s);
			}

			private void insert2(String s) {

				if (s.length() != 0) {
					String character = s.substring(0, 1);

					if (!hasChild(character)) {
						if(hasChilds())
						{
							System.out.println("has childs, character could be in child " + character);
							Node node = getChild(character);
							if(node==null)
							{
								System.out.println("not found child " + character);
								childeren.add(new Node(character));
								System.out.println("Inserted child " + character);
							}
							else
							{
								System.out.println("already exist" + character);
							}
						}
						else
						{
						childeren.add(new Node(character));
						System.out.println("Inserted child " + character);
						}
					}
					else	
					{System.out.println("not inserted child " + character);}
					Node node = getChild(character);
					String newInsert = s.substring(1, s.length());
					node.insert(newInsert);
				}
			}
			
//			private void insert2Kopie(String s) {
//
//				if (s.length() != 0) {
//					String character = s.substring(0, 1);
//					if(duplicate(character,root))// duplicate detected
//					{
//						Node node = getChild(character);
//						System.out.println("duplicate found rebuild nodes");
//						String newInsert = s.substring(1, s.length());
//					    node.insert(newInsert);
//					}
//					if (!hasChild(character)) {
//						if(this.data != null)
//						{
//							childeren.add(new Node(s));
//							System.out.println("Inserted child " + s);
//							return;
//						}
//						else
//						{
//							childeren.add(new Node(character));
//							System.out.println("Inserted child " + character);
//						}
//					}
//					Node node = getChild(character);
//					if(node !=null)
//					{
//					String newInsert = s.substring(1, s.length());
//				    node.insert(newInsert);
//					}
//				}
//			}

			public boolean hasChilds() {
				return childeren.size() > 0;
			}

			public Node getChild(String s) {
				for (Node node : childeren) {
					if (node.data.equals(s) || node.data.contains(s)) {
						return node;
					}
				}
				return null;
			}
//			public Node getChildDuplicate(String s, Node x) {
//				
//				if(duplicate(s, x))
//				{
//					return x;
//				}
//				return null;
//			}
//			public boolean duplicate(String s,Node root) {
//				if(root.data!=null)
//				{
//					if(root.data.contains(s))
//					{
//						return true;
//					}
//					else
//					{
//						Node helper =null;
//						for (Node node : root.childeren) 
//						{
//							if (node.data.contains(s)) {
//								return true;
//							}
//							helper = node;
//						}
//						if(helper !=null)
//						{
//						  return duplicate(s, helper);
//						}
//						else return false;
//					}
//				}
//				else if(!root.hasChilds())
//				{
//					return false;
//				}
//				else
//				{
//					Node helper =null;
//						for (Node node : root.childeren) 
//						{
//							if (node.data.contains(s)) {
//								helper = null;
//								return true;
//							}
//							helper = node;
//						}
//						if(helper !=null)
//						{
//						 duplicate(s, helper);
//						}
//						return false;
//				}
//				
//			}
				
				
			
		
			public boolean hasChild(String s) {
				for (Node node : childeren) {
					if (node.data.equals(s)) {
						return true;
					}
				}
				return false;
			}

//		private boolean matchWord(String word)
//		{
//			
//		}
		
		private void collapse()
		{
			// rebuild tree 
		}
		}
}


