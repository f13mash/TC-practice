package com.f13mash.srm156;

import java.util.LinkedList;
import java.util.List;

public class PathFinding {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] board={"XXXXXXXXX",
				 "A.......B",
		 "XXXXXXXXX"};
		System.out.println("ans is  : "+minTurns(board));
	}
	
	public static int minTurns(String[] board){
		
		int h=board.length, w=board[0].length();
		boolean[][] fill=new boolean[h][w];
		boolean[][][][] visited=new boolean[h][w][h][w];
		
		
		
		int x1=0,y1=0,x2=0,y2=0;
		
		for(int i=0;i<h;i++){
			char[] arr=board[i].toCharArray();
			for(int j=0;j<w;j++){
				switch(arr[j]){
				case '.':
					fill[i][j]=true;
					break;
				case 'A':
					fill[i][j]=true;
					x1=i;
					y1=j;
					break;
				case 'B':
					fill[i][j]=true;
					x2=i;
					y2=j;
					break;
				}
			}
		}
		
		Node s=new Node(x1, y1, x2, y2, 0);
		Node ans=null;
		List<Node> q=new LinkedList<Node>();
		
		q.add(s);
		visited[x1][y1][x2][y2]=true;
		
		while(q.size()>0){
			
			Node t=q.remove(0);
			
			if(t.x1==s.x2 && t.x2==s.x1 && t.y1==s.y2 && t.y2==s.y1){
				ans=t;
				break;
			}
			
			for(int dx1=-1;dx1<=1;dx1++){
				for(int dy1=-1;dy1<=1;dy1++){
					for(int dx2=-1;dx2<=1;dx2++){
						for(int dy2=-1;dy2<=1;dy2++){
							
							
							Node nn=new Node(t.x1+dx1, t.y1+dy1, t.x2+dx2, t.y2+dy2, t.tm+1);
							if(nn.x1==t.x2 && nn.x2==t.x1 && nn.y1==t.y2 && nn.y2==t.y1){
								continue;
							}
							if(nn.x1==nn.x2 && nn.y1==nn.y2)
								continue;
							
							if(nn.x1>=h || nn.x1<0 || nn.x2>=h || nn.x2<0 || nn.y1>=w || nn.y1<0 || nn.y2>=w || nn.y2<0 )
								continue;
							
							
							if(fill[nn.x1][nn.y1] && fill[nn.x2][nn.y2]){
								if(!visited[nn.x1][nn.y1][nn.x2][nn.y2]){
									q.add(nn);
									visited[nn.x1][nn.y1][nn.x2][nn.y2]=true;
								}
							}
						}
					}
				}
			}
			
		}
		
		if(ans==null)
			return -1;
		else
			return (int) ans.tm;
	}
	
	

}

class Node{
	int x1, y1;
	int x2, y2;
	long tm;
	public Node(int x1, int y1, int x2, int y2, long tm){
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.tm=tm;
	}
}