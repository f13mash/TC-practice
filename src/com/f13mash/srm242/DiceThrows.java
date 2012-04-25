package com.f13mash.srm242;

public class DiceThrows {
	static int MAX=0;
	public static void main(String[] args) {
		int numDiceA=200;
		int[] sidesA={1,3,8,18,45,100};
		int numDiceB=200;
		int[] sidesB={1,4,10,21,53,100};
		System.out.println("ans is : "+winProbability(numDiceA, sidesA, numDiceB, sidesB));
	}
	
	public static double winProbability(int numDiceA, int[] sidesA, int numDiceB, int[] sidesB) {

		MAX=Math.max(numDiceB, numDiceA)*100+1;
		double[] pa=eval(numDiceA, sidesA);
		double[] pb=eval(numDiceB, sidesB);
		double sum=0;
		for(int i=0;i<pa.length;i++){
			for(int j=0;j<i;j++){
				sum+=pa[i]*pb[j];
			}
		}
		
		return sum;
	}
	
	static double[] eval(int num, int sides[]){
		double[] probA=new double[MAX];
		probA[0]=1;
		
		for(int i=0;i<num;i++){
			double[] probB=new double[MAX];
			for(int j=0;j<probA.length;j++){
				double p=probA[j];
				if(p<=0)
					continue;
				for(int k=0;k<sides.length;k++){
					probB[j+sides[k]]+=(p/sides.length);
				}
			}
			probA=probB;
		}
		
		
		
		return probA;
	}
	
}
