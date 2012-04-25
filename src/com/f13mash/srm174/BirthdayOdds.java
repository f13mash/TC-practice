package com.f13mash.srm174;

public class BirthdayOdds {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int minOdds=75;
		int daysInYear=2;
		System.out.println("ans is : "+minPeople(minOdds, daysInYear));
	}

	public static int minPeople(int minOdds, int daysInYear) {
		double v = (double)(100-minOdds)/100;
		double oth=1;
		int ans=0;
		System.out.println("v : "+v);
		for(int i=0;i<=daysInYear;i++){
			oth*=(double)(daysInYear-i)/daysInYear;
			ans=i+1;
			if(oth<=v){
				break;
			}
		}
		if(daysInYear==1)
			return 2;
		return ans;
	}
}
