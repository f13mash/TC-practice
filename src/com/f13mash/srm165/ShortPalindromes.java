package com.f13mash.srm165;

public class ShortPalindromes {

	/**
	 * @param args
	 */
	static int best;
	static String ans=null;
	public static void main(String[] args) {
		String base="ALRCAGOEUAOEURGCOEUOOIGFA";
		//String base="ALFA";
		System.out.println("ans is : "+shortest(base));
	}
	
	public static String shortest(String base){
		best=base.length()*2+1; 
		recurse(new StringBuilder(base), 0);
		//System.out.println(ans+" : "+best);
		return ans;
	}
	
	public static void recurse(StringBuilder str, int s){
		if(s>=(str.length()/2)){
			//System.out.println("CHECK : "+str.length()+" : "+str+" equal : ");
			if(str.length()<best || (str.length()==best && ans.compareTo(str.toString())>0)){
				
				best=str.length();
				ans=str.toString();
			}
			return;
		}
		
		
		if(str.length()>best){
			return;
		}
		
		if(str.charAt(s)==str.charAt(str.length()-1-s)){
			//System.out.println(str+" : "+str.length()+" : "+s+" : E");
			recurse(str, s+1);
		}
		else{
			//System.out.println(str+" : "+str.length()+" : "+s+" : N");
			//String lins = str.substring(0, s)+str.charAt(str.length()-1-s)+str.substring(s, str.length());
			StringBuilder lins = new StringBuilder(str.toString()).insert(s, str.charAt(str.length()-1-s));
			int ds=str.length()-1-s;
			//String rins = str.substring(0, ds+1)+str.charAt(s)+str.substring(ds+1, str.length());
			StringBuilder rins=new StringBuilder(str.toString()).insert(ds+1, str.charAt(s));
			recurse(lins, s);
			//System.out.println(ds);
			recurse(rins, s);
		}
	}
}
