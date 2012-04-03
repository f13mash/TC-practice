package TCC2004;

import java.util.Arrays;

public class BadNeighbors {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] donations=	
			{ 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,  
				  6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
				  52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 };
		System.out.println("ans is : "+maxDonations(donations));
	}
	
	public static int maxDonations(int[] donations){
		int[][] sums=new int[donations.length][2];
		sums[0][0]=donations[0];
		int max=0;
		
		for(int i=1;i<donations.length;i++){
			if(i!=(donations.length-1)){
				int b0=0;
				for(int j=0;j<(i-1);j++){
					if(sums[j][0]>b0){
						b0=sums[j][0];
					}
				}
				sums[i][0]=b0+donations[i];
				if(max<sums[i][0]){
					max=sums[i][0];
				}
			}
			
			int b1=0;
			for(int j=1;j<(i-1);j++){
				if(sums[j][1]>b1){
					b1=sums[j][1];
				}
			}
			sums[i][1]=b1+donations[i];
			if(max<sums[i][1]){
				max=sums[i][1];
			}
			
		}
		
		return max;
		
	}

}
