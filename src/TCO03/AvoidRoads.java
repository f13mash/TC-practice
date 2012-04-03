package TCO03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AvoidRoads {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int width=100;
		int height=100;
		String[] bad={"0 0 0 1", "0 0 1 0", "0 1 0 0"};
		System.out.println("ans is  : "+numWays(width, height, bad));
	}
	
	public static long numWays(int width, int height, String[] bad){
		
		int size=(width+1)*(height+1);
		
		//boolean[][] bmap=new boolean[size][size];
		Map<String, Boolean> mp=new HashMap<String, Boolean>();
		long[][] sum=new long[2][Math.max(width, height)+1];
		
		
		for(String str : bad){
			String[] splts=str.trim().split(" ");
			int a=Integer.parseInt(splts[0]);
			int b=Integer.parseInt(splts[1]);
			int p1=b*(width+1)+a;
			int c=Integer.parseInt(splts[2]);
			int d=Integer.parseInt(splts[3]);
			int p2=d*(width+1)+c;
			mp.put(p1+" "+p2, true);
			mp.put(p2+" "+p1, true);
			//bmap[p1][p2]=bmap[p2][p1]=true;
		}
		
		
		int x=0, y=0;
		sum[0][0]=1;
		
		for(x=0;x<(width+1);x++){
			System.out.println("x : "+x+" y : "+y+" ==>> "+Arrays.toString(sum[(x+y)%2]));
			int dx=-1, dy=1;
			
			int i=x, j=y;
			
			while(i>=0 && j<=height){
				
				int p1=j*(width+1)+i;
				
				if((i+1)<=width){
					int p2=j*(width+1)+i+1;
					if(!mp.containsKey(p1+" "+p2)){
						//sum[i+1][j]+=sum[i][j];
						sum[(x+1)%2][j]+=sum[x%2][j];
					}
						
				}
				if((j+1)<=height){
					int p2=(j+1)*(width+1)+i;
					if(!mp.containsKey(p1+" "+p2)){
						//sum[i][j+1]+=sum[i][j];
						sum[(x+1)%2][j+1]+=sum[x%2][j];
					}
				}
				
				i+=dx;
				j+=dy;
			}
			Arrays.fill(sum[x%2], 0);
		}
		
		x--;
		
		for(y=1;y<=(height);y++){
			//System.out.println("x : "+x+" y : "+y+" ==>> "+Arrays.toString(sum[(x+y)%2]));
			int dx=-1, dy=1;
			
			int i=x, j=y;
			
			while(i>=0 && j<=height){
				
				int p1=j*(width+1)+i;
				
				if((i+1)<=width){
					int p2=j*(width+1)+i+1;
					if(!mp.containsKey(p1+" "+p2)){
						//sum[i+1][j]+=sum[i][j];
						sum[(x+1+y)%2][j]+=sum[(x+y)%2][j];
					}
						
				}
				if((j+1)<=height){
					int p2=(j+1)*(width+1)+i;
					if(!mp.containsKey(p1+" "+p2)){
						sum[(x+1+y)%2][j+1]+=sum[(x+y)%2][j];
					}
				}
				
				i+=dx;
				j+=dy;
			}
			if(y!=height)
				Arrays.fill(sum[(x+y)%2], 0);
		}
		
		System.out.println(Arrays.toString(sum[0]));
		System.out.println(Arrays.toString(sum[1]));
		
		return sum[(x+y-1)%2][height];
	}

}
