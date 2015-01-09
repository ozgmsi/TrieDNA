package nl.saxion.da.model;

public class Tree {
	
	private Node root;
	
	public Tree()
	{
		
	}
	
	public void insert(String woord,Data d)
	{
		System.out.println(" woord insert: " + woord );
		for(int i=0; i< woord.length(); i++)
		{
			String letter = woord.substring(i);
			if(root.isMatch(letter))
			{
				if(root.hasChildren())
				{
					for(Node child : root.children)
					{
						if(child.isMatch(letter));
						{
							
						}
						
					}
				}
			}
			else 
			{
			 //	root.children.add(new Node(woord,true));
			}
		}
		
		
		
	}
	public Data[] search(String s)
	{
		Data[] data = new Data[5];
		return data;
	}
	public  void delete(String s)
	{
		
	}
	
	public boolean isLeaf()
	{
		return !root.hasChildren();
	}
	

}
