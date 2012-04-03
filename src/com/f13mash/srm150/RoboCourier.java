package com.f13mash.srm150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class RoboCourier {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] path={ "LLFLFRLRRLRFFLRRRRFFFLRFFRRRLLFLFLLRLRFFLFRRFFFLFL",
				  "RLFFRRLRLRRFFFLLLRFRLLRFFLFRLFRRFRRRFRLRLRLFFLLFLF",
				  "FRFLRFRRLLLRFFRRRLRFLFRRFLFFRLFLFLFRLLLLFRLLRFLLLF",
				  "FFLFRFRRFLLFFLLLFFRLLFLRRFRLFFFRRFFFLLRFFLRFRRRLLR",
				  "FFFRRLLFLLRLFRRLRLLFFFLFLRFFRLRLLFLRLFFLLFFLLFFFRR",
				  "LRFRRFLRRLRRLRFFFLLLLRRLRFFLFRFFRLLRFLFRRFLFLFFLFR",
				  "RFRRLRRFLFFFLLRFLFRRFRFLRLRLLLLFLFFFLFRLLRFRLFRLFR",
				  "LLFLFRLFFFFFFFRRLRLRLLRFLRLRRRRRRRRLFLFLFLRFLFRLFF",
				  "RLFRRLLRRRRFFFRRRLLLLRRLFFLLLLLRFFFFRFRRLRRRFFFLLF",
				  "FFFFLRRLRFLLRRLRLRFRRRRLFLLRFLRRFFFRFRLFFRLLFFRRLL" };
		
		System.out.println("ans : "+timeToDeliver(path));
		
	}
	
	public static int timeToDeliver(String[] path){
		
		List<Node> nodeList=new ArrayList<Node>();
		int size=0;
		int currX=0, currY=0, orient=0; 
		Node init=new Node(size++,currX, currY);
		Node dest = init;
		
		String initKey=init.x+" : "+init.y;
		Map<String, Node> nmap=new HashMap<String, Node>();
		Map<String, Boolean> edgeMap=new HashMap<String, Boolean>();
		
		nodeList.add(init);
		nmap.put(initKey, init);
		
		
		for(int i=0;i<path.length;i++){
			
			
			char[] arr=path[i].trim().toCharArray();
			for(int j=0;j<arr.length;j++){

				switch (arr[j]) {
				case 'L':
					orient-=1;
					orient=(orient+6)%6;
					break;
				case 'R':
					orient+=1;
					orient%=6;
					break;
				case 'F':
					switch (orient) {
					case 0:
						currY+=2;
						break;
					case 1:
						currX++;
						currY++;
						break;
					case 2:
						currX++;
						currY--;
						break;
					case 3:
						currY-=2;
						break;
					case 4:
						currX--;
						currY--;
						break;
					case 5:
						currX--;
						currY++;
						break;
					}
					
					String key=currX+" : "+currY;
					if(!nmap.containsKey(key)){
						Node n=new Node(size++, currX, currY);
						nodeList.add(n);
						nmap.put(key, n);
					}
					
					
					Node prev=dest;
					dest=nmap.get(key);
					int s, e;
					if(prev.id>dest.id){
						s=prev.id;
						e=dest.id;
					}
					else{
						s=dest.id;
						e=prev.id;
					}
					edgeMap.put(s+" : "+e, true);
					break;	
				}
			}
		}
		
		boolean[] visited=new boolean[size];
		List<Node> unvisited=new ArrayList<Node>();
		unvisited.addAll(nodeList);
		
		TreeSet<Node> ts=new TreeSet<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node arg0, Node arg1) {
				// TODO Auto-generated method stub
				if(arg0.cost>arg1.cost)
					return 1;
				if(arg1.cost>arg0.cost)
					return -1;
				if(arg0.id>arg1.id)
					return 1;
				if(arg1.id>arg0.id)
					return -1;
				return 0;
			}
		});
		
		init.cost=0;
		init.appOrient=0;
		ts.add(init);
		
		
		while(ts.size()>0){
			Node proc=ts.first();
			ts.remove(proc);
			
			if(visited[proc.id])
				continue;
			
			if(proc.id==dest.id)
				break;
			
			visited[proc.id]=true;
			unvisited.remove(proc);
			
			for(Node n :  unvisited){
				if(visited[n.id])
					continue;
				
				int dx=n.x-proc.x;
				int dy=n.y-proc.y;

				//check is adjacent
				if((Math.abs(dx)+Math.abs(dy))!=2)
					continue;
				
				int s, e;
				if(n.id>proc.id){
					s=n.id;
					e=proc.id;
				}
				else{
					s=proc.id;
					e=n.id;
				}
				
				if(!edgeMap.containsKey(s+" : "+e))
					continue;
				
				int or=-1;
				
				switch (dy) {
				case -2:
					or=3;
					break;
				case 2:
					or=0;
					break;
				case -1:
					if(dx==1){
						or=2;
					}
					else{
						or=4;
					}
					break;
				case 1:
					if(dx==1)
						or=1;
					else
						or=5;
					break;
				}
				
				int newCost=0;
				if(proc.ao==or && proc.parent!=null && proc.parent.ao==or){
					newCost=2;
				}
				else{
					int turn=(or-proc.appOrient+6)%6;
					turn=Math.min(6-turn, turn);
					newCost=4+turn*3;
				}
				
				if((proc.cost+newCost)<n.cost){
					
					
					
					if(ts.contains(n))
						ts.remove(n);
					n.cost=proc.cost+newCost;
					n.parent=proc;
					n.appOrient=or;
					n.ao=or;
					ts.add(n);					
				}
			}
			
			
		}
		
		
		System.out.println("size : "+size+" : "+nodeList.size());
		if(dest!=null){
			Node n=dest;
			while(n!=null){
				System.out.println("node #"+n.id+" cost : "+n.cost );
				n=n.parent;
			}
		}
		
		return (int) dest.cost;
	}

}


class Node{
	int id;
	
	int x, y;
	
	Node[] conList;
	
	long cost=Long.MAX_VALUE;
	int appOrient=-1, ao=-1;
	Node parent;
	
	public Node(int id, int x, int y){
		this.id=id;
		this.x=x;
		this.y=y;
		conList=new Node[6];
	}
}