package com.f13mash.srm223;

import java.util.HashSet;

public class QuizShow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] scores={ 10, 50, 60 };
		int wager1=30;
		int wager2=41;
		System.out.println("ans is : "+wager(scores, wager1, wager2));

	}
	
	public static int wager(int[] scores, int wager1, int wager2){
		HashSet<Integer> hs=new HashSet<Integer>();
		int bestv=0, bt=0;
		for(int l1=0;l1<2;l1++){
			int amt1=scores[1]+(l1==0?-wager1:wager1);
			for(int l2=0;l2<2;l2++){
				int amt2=scores[2]+(l2==0?-wager2:wager2);
				for(int l0=0;l0<2;l0++){
					int mamt=Math.max(amt1, amt2)+1;
					int bet=0;
					if(l0==0){
						bet=scores[0]-mamt;
						if(bet<0)
							bet=0;
						if(scores[0]>mamt)
							bet=0;
					}
					else{
						bet=mamt-scores[0];
						if(bet<0)
							bet=0;
						if(scores[0]>mamt)
							bet=0;
					}
					if(bet>scores[0])
						bet=0;
					hs.add(bet);
				}
			}
		}
		
		for(int wager : hs){
			int fav=0;
			for(int l1=0;l1<2;l1++){
				int amt1=scores[1]+(l1==0?-wager1:wager1);
				for(int l2=0;l2<2;l2++){
					int amt2=scores[2]+(l2==0?-wager2:wager2);
					int mamt=Math.max(amt1, amt2)+1;
					for(int l0=0;l0<2;l0++){
						if(l0==0){
							//System.out.println(wager+" : "+(scores[0]-wager)+" : "+mamt);
							if((scores[0]-wager)>=mamt)
								fav++;
						}
						else{
							//System.out.println(wager+" : "+(scores[0]+wager)+" : "+mamt);
							if((scores[0]+wager)>=mamt)
								fav++;
						}
					}
				}
			}
			System.out.println("bet : "+wager+" : fav :"+fav );
			if(fav>bestv || (fav==bestv && bt>wager) ){
				bestv=fav;
				bt=wager;
			}
		}
		
		return bt;
	}

}
