package TCO03;

import java.util.Arrays;

public class Jewelry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] values={123,217,661,678,796,964,54,111,417,526,917,923};
		System.out.println("ans is : "+howMany(values));
	}
	
	public static long howMany(int[] values){
		
		int[][] comb = new int[values.length + 1][values.length + 1];
		
		genCombination(comb);
		for (int i = 0; i < comb.length; i++) {
			System.out.println(Arrays.toString(comb[i]));
		}
		
		Arrays.sort(values);
		
		int[][] count = new int[values.length][2] ;
		int size = 0;
		count[0][0] = values[0];
		count[0][1] = 1;
		int max_sum = values[0];
		int maxc = 1;
		
		for (int i = 1; i<values.length; i++){
			if(values[i] != values[i-1]){
				size++;
			}
			count[size][0] = values[i];
			count[size][1]++;
			max_sum += values[i];
			if(maxc < count[size][1])
				maxc = count[size][1];
		}
		size++;
		maxc++;
		
		long[][][][] dp = new long[(max_sum / 2 + 2)][size][values.length + 1][2];
		long[][][][] ndp = new long[(max_sum / 2 + 2)][size][values.length + 1][2];
		
		System.out.println(max_sum);
		
		for(int lim = 1; lim < dp.length; lim++){
			for(int j = 0; j < size; j++){
				for(int c = 1; c <= count[j][1]; c++){
					int reqm = lim - c * count[j][0];
					if(reqm < 0)
						break;
					long waysCount = 0;
					for(int k = 0; k < j; k++){
						waysCount += dp[reqm][k][0][0];
					}
					if(reqm == 0)
						waysCount = 1;
					//System.out.println(lim+" : "+reqm+ " : "+waysCount+ " : "+comb[count[j][1]][c]+" : "+count[j][1]+" : "+c);
					dp[lim][j][c][1] = waysCount;
					dp[lim][j][c][0] = comb[count[j][1]][c] * waysCount;
					dp[lim][j][0][0] += dp[lim][j][c][0];
					
					
				}
				
			}
		}
		
		for(int lim = 1; lim < ndp.length; lim++){
			for(int j = size - 1; j >= 0; j--){
				for(int c = 1; c <= count[j][1]; c++){
					int reqm = lim - c * count[j][0];
					if(reqm < 0)
						break;
					long waysCount = 0;
					for(int k = size - 1 ; k > j; k--){
						waysCount += ndp[reqm][k][0][0];
					}
					if(reqm == 0)
						waysCount = 1;
					//System.out.println(lim+" : "+reqm+ " : "+waysCount+ " : "+comb[count[j][1]][c]+" : "+count[j][1]+" : "+c);
					ndp[lim][j][c][1] = waysCount;
					ndp[lim][j][c][0] = comb[count[j][1]][c] * waysCount;
					ndp[lim][j][0][0] += ndp[lim][j][c][0];
				}
			}
		}
		long total = 0;
		for(int lim = 1; lim <=  max_sum / 2; lim++){
			for(int j = 0; j < size; j++){
				long val = 0;
				 for(int k = j + 1; k < size; k++){
					 val += dp[lim][j][0][0] * ndp[lim][k][0][0];
				 }
				 
				 for(int c1 = 1; c1 <=count[j][1]; c1++){
					 for(int c2 = 1; c2 <=(count[j][1] - c1); c2++){
						 val += (dp[lim][j][c1][1] * ndp[lim][j][c2][1] * comb[count[j][1]][c1] * comb[count[j][1] - c1][c2]);
					 }
				 }
				 //System.out.println(lim+" : "+j+" : "+val);
				 total+=val;
			}
		}
		
		return total;
	}
	
	public static void genCombination(int[][] arr){
		arr[0][0]=1;
		for(int i=1;i<arr.length;i++){
			arr[i][0]=1;
			for(int j=1;j<=i;j++){
				arr[i][j]=arr[i-1][j]+arr[i-1][j-1];
			}
		}
	}

}
