package TCC2004;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FlowerGarden {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] height={5,4,3,2,1};
		int[] bloom={1,5,10,15,20};
		int[] wilt={5,10,15,20,25};
		System.out.println(Arrays.toString(getOrdering(height, bloom, wilt)));
	}
	
	public static int[] getOrdering(int[] height, int[] bloom, int[] wilt){
		
		ArrayList<Node> nodes=new ArrayList<Node>();
		
		for(int i=0;i<height.length;i++){
			nodes.add(new Node(height[i], bloom[i], wilt[i]));
		}
		
		for(int i=0;i<height.length;i++){
			Node a=nodes.get(i);
			for(int j=0;j<i;j++){
				Node b=nodes.get(j);
				if(a.wilt<b.bloom || b.wilt<a.bloom){
					/*if(a.height>b.height){
						b.parents.add(a);
						a.children.add(b);
					}
					else{
						a.parents.add(b);
						b.children.add(a);
					}*/
				}
				else{
					if(a.height>b.height){
						a.parents.add(b);
						b.children.add(a);
					}
					else{
						b.parents.add(a);
						a.children.add(b);
					}
				}
			}
		}
		
		int[] ret=new int[height.length];
		int ind=0;
		
		TreeSet<Node> ts=new TreeSet<Node>(new Comparator<Node>() {
			public int compare(Node arg0, Node arg1) {
				if(arg0.height>arg1.height)
					return -1;
				if(arg0.height<arg1.height)
					return 1;
				return 0;
				
			}
		});
		
		
		while(ind<ret.length){
			for(int i=0;i<nodes.size();i++){
				if(nodes.get(i).parents.size()==0){
					ts.add(nodes.get(i));
				}
			}
			
			Node n=ts.first();
			ts.remove(n);
			nodes.remove(nodes.indexOf(n));
			ret[ind++]=n.height;
			for(Node c : n.children){
				c.parents.remove(c.parents.indexOf(n));
			}
		}
		
		return ret;
	}

}

class Node{
	int height;
	int bloom;
	int wilt;
	
	ArrayList<Node> parents, children;
	
	public Node(int height, int bloom, int wilt){
		this.height=height;
		this.bloom=bloom;
		this.wilt=wilt;
		parents=new ArrayList<Node>();
		children=new ArrayList<Node>();
	}
}
