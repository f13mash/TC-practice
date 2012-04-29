package TCO04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneticCrossover {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String p1a="XyMpdnVsbinDvqBpcWGDLfsmQtZpeirvTmoRmBASfqqrFS";
		String p1b="xYmpdnVsBINdvQBPCwgDlFSmQTzpEIrVtmoRmbaSfqQRfS";
		String p2a="XYMpdnvsBINdVQbpCWgDlfSMqTzPeIrVTMOrmbaSfQqrFs";
		String p2b="XYMpDnVSBIndvQBPCWGDlFsMqtzpEiRVTMORMBASFqQrfS";
		int[] dom={-82,-35,-51,52,87,25,8,27,-12,-10,-63,-36,-95,-35,-98,95,-76,7,36,-35,92,23,-94,
				 -31,-30,36,51,-49,-19,-3,19,32,58,82,-36,-87,-54,-17,-40,32,-91,-56,0,-16,70,42};
		int[] rec={-36,77,90,50,83,66,-94,-66,-22,-83,-86,-89,-55,-3,-51,18,-41,-91,91,32,-25,-60,
				 5,79,100,85,60,98,55,95,-67,-46,-26,48,-64,16,-36,-95,-54,19,96,79,78,-91,-12,35};
		int[] dependencies={-1,-1,1,-1,3,-1,-1,1,3,5,4,0,-1,-1,2,8,5,4,-1,10,11,14,3,-1,
				15,-1,7,-1,8,-1,-1,15,-1,-1,30,-1,26,26,-1,-1,-1,-1,-1,31,0,3};
		System.out.println("ans is : "+cross(p1a, p1b, p2a, p2b, dom, rec, dependencies));
	}
	
	public static double cross(String p1a, String p1b, String p2a, String p2b, int[] dom, int[] rec, int[] dependencies) {
		
		char[] p1aArr=p1a.toCharArray();
		char[] p1bArr=p1b.toCharArray();
		char[] p2aArr=p2a.toCharArray();
		char[] p2bArr=p2b.toCharArray();

		int size=dom.length;
		double[] probD=new double[size];
		double[] probR=new double[size];
		
		int[] depc=dependencies.clone();
		
		int count=0;
		
		boolean[] vis=new boolean[size];
		
		while(count<size){
			int id=-1;
			for(int i=0;i<size;i++){
				if(vis[i])
					continue;
				if(depc[i]!=-1)
					continue;
				id=i;
				break;
			}
			if(id==-1)
				break;
			
			count++;
			vis[id]=true;
			
			int l1c=0, l2c=0;
			l1c+=(Character.isLowerCase(p1aArr[id])?1:0);
			l1c+=(Character.isLowerCase(p1bArr[id])?1:0);
			l2c+=(Character.isLowerCase(p2aArr[id])?1:0);
			l2c+=(Character.isLowerCase(p2bArr[id])?1:0);
			double p1=(double)l1c/2;
			double p2=(double)l2c/2;
			double rp=p1*p2;
			
			if(dependencies[id]==-1){
				probR[id]=rp;
				probD[id]=1-rp;
			}
			else{
				probD[id]=(1-rp)*probD[dependencies[id]];
				probR[id]=1-probD[id];
			}
			
			
			
			for(int i=0;i<size;i++){
				if(depc[i]==id)
					depc[i]=-1;
			}
			
		}
		double sum=0;
		System.out.println(Arrays.toString(probD));
		System.out.println(Arrays.toString(probR));
		for(int i=0;i<size;i++){
			sum+=probD[i]*dom[i];
			sum+=probR[i]*rec[i];
		}
		return sum;
	}

}
