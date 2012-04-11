package com.f13mash.srm197;

public class QuickSums {
	static char[] arr=null;
	static int best=Integer.MAX_VALUE;
	static boolean found=false;
	
	public static void main(String[] args) {
		String numbers=	"9230560001";
		int sum=71;
		System.out.println("ans is : "+minSums(numbers, sum));
	}
	
	public static int minSums(String numbers, int sum) {
		arr = numbers.trim().toCharArray();
		int s=0;
		recurse(sum, s, 0, 0);
		if(found)
			return best;
		else
			return -1;
	}
	
	public static void recurse(int sum, int s, long val, int count){
		if(s>=arr.length)
			return;
		long a=Long.parseLong(new String(arr, s, arr.length-s));
		if((val+a)==sum){
			found=true;
			best=Math.min(count, best);
				
		}
		for(int p=s;p<(arr.length-1);p++){
			long add=Long.parseLong(new String(arr, s, p-s+1));
			recurse(sum, p+1, val+add, count+1);
		}
	}
	
	
}
