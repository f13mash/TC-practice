package TCO04;

import java.util.Arrays;

import javax.xml.ws.handler.MessageContext.Scope;

public class VolleyBall {
	
	static int max=10001;
	
	public static void main(String[] args) {
		int sScore=1;
		int rScore=14;
		double probWinServe=0.01;
		System.out.println("ans is : "+win(sScore, rScore, probWinServe));
	}
	
	public static double win(int sScore, int rScore, double probWinServe) {
		if(sScore>=15 && (sScore-rScore)>=2){
			return 1;
		}
		if(rScore>=15 && (rScore-sScore)>=2){
			return 0;
		}
		
		double[][] dpr=new double[2][max];
		double[][] dps=new double[2][max];
		
		dps[sScore%2][rScore]=1;
		double sum=0;
		for(int i=sScore;i<max;i++){

			int rin=i%2;
			int rin2=(i+1)%2;
			Arrays.fill(dpr[rin2], 0);
			Arrays.fill(dps[rin2], 0);
			for(int j=rScore;j<max;j++){
				
				
				if(i>=15 && (i-j)>=2){
					sum+=dps[rin][j];
				}
				
				if((i+1)<max && !(i>=15 && (i-j)>=2) && !(j>=15 && (j-i)>=2)){
					dps[rin2][j]+=dps[rin][j]*probWinServe;
					dps[rin2][j]+=dpr[rin][j]*(1-probWinServe);
				}
				if((j+1)<max && !(j>=15 && (j-i)>=2) && !(i>=15 && (i-j)>=2)){
					dpr[rin][j+1]+=dps[rin][j]*(1-probWinServe);
					dpr[rin][j+1]+=dpr[rin][j]*(probWinServe);
				}
			}
		}
		
		return sum;
	}
}
