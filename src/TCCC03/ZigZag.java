package TCCC03;

import java.util.Arrays;

public class ZigZag {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] sequence={ 1, 7, 4, 9, 2, 5 };
		System.out.println("ans is : "+longestZigZag(sequence));
	}
	
	public static int longestZigZag(int[] sequence){
		
		int[][] ans=new int[sequence.length][2];

		Arrays.fill(ans[0], 1);
		for(int i=1;i<sequence.length;i++){
			Arrays.fill(ans[i], 1);
			System.out.println("i : "+i);
			for(int j=0;j<(i);j++){
				System.out.println(" j : "+j);
				if(sequence[j]>sequence[i]){
					ans[i][1]=Math.max(ans[j][0]+1, ans[i][1]);
				}
				else{
					if(sequence[j]<sequence[i]){
						ans[i][0]=Math.max(ans[j][1]+1, ans[i][0]);
					}
				}
			}
		}
		
		int max=0;
		
		for(int i=0;i<ans.length;i++){
			for(int j=0;j<ans[i].length;j++){
				max=Math.max(max, ans[i][j]);
			}

			System.out.println(sequence[i]);
			System.out.println(Arrays.toString(ans[i]));
			
		}
		return max;
	}

}
