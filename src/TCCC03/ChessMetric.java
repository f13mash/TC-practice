package TCCC03;

import java.util.Arrays;

public class ChessMetric {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int size=100;
		int[] start={0,0};
		int[] end={0,99};
		int numMoves=50;
		System.out.println("ans is : "+howMany(size, start, end, numMoves));
	}
	
	public static long howMany(int size, int[] start, int[] end, int numMoves){
		
		long[][][] dp=new long[2][size][size];
		
		dp[0][start[0]][start[1]]=1;
		
		for(int i=0;i<numMoves;i++){
			int f=i%2;
			for(int a=0;a<size;a++){
				for(int b=0;b<size;b++){
					if(dp[f][a][b]<=0)
						continue;
					//king moves
					for(int dx=-1;dx<=1;dx++){
						for(int dy=-1;dy<=1;dy++){
							if(dx==0 && dy==0)
								continue;
							int c=a+dx;
							int d=b+dy;
							if(c>=0 && c<size && d>=0 && d<size){
								dp[(f+1)%2][c][d]+=dp[f][a][b];
							}
						}
					}
					
					//knight moves
					for(int dx=-2;dx<=2;dx++){
						if(dx==0){
							continue;
						}
						int dy=2/dx;
						int c=a+dx;
						int d=b+dy;
						if(c>=0 && c<size && d>=0 && d<size){
							dp[(f+1)%2][c][d]+=dp[f][a][b];
						}
						c=a+dx;
						d=b-dy;
						if(c>=0 && c<size && d>=0 && d<size){
							dp[(f+1)%2][c][d]+=dp[f][a][b];
						}	
					}
				}
			}
			for(int a=0;a<size;a++){
				Arrays.fill(dp[f][a], 0);
			}
		}
		
		return dp[(numMoves)%2][end[0]][end[1]];
	}

}
