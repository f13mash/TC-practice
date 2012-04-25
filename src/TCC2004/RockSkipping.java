package TCC2004;

public class RockSkipping {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String pads="........................X";
		int maxDist=50;
		System.out.println("ans is : "+probability(pads, maxDist));
	}

	public static double probability(String pads, int maxDist) {
		
		char[] arr=pads.trim().toCharArray();
		double[] prob=new double[maxDist*maxDist +1];
		prob[0]=1;
		
		int dist=maxDist;
		
		double sum=0;
		
		while(dist>0){
			double[] nprob=new double[maxDist*maxDist +1];
			
			for(int i=0;i<prob.length;i++){
				if(prob[i]==0)
					continue;
				for(int k=1;k<=dist;k++){
					if(arr[(i+k)%arr.length]=='.'){
						nprob[i+k]+=(prob[i]/dist);
					}
					else{
						sum+=(prob[i]/dist);
					}
				}
			}
			prob=nprob;
			dist--;
		}
		
		return (1-sum)*100;
	}
}
