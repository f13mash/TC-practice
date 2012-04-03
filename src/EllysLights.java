import java.util.Arrays;


public class EllysLights {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String initial = "YNYNNN";
		String[] switches =	{"YNNYNY", "NYYYNN", "YNYNYN", "NNNNYN", "NYNNNY"};
		System.out.println("ans is : "+getMinimum(initial, switches));
	}
	
	public static int getMinimum(String initial, String[] switches) {
		long sinit=0;
		
		char[] arr=initial.toCharArray();
		for(int i=0;i<arr.length;i++){
			if(arr[i]=='Y'){
				sinit = sinit | (1<<(arr.length - i-1));
			}
		}
		
		long[] sw=new long[switches.length];
		
		System.out.println("inti state : "+Long.toBinaryString(sinit));
		
		for(int j=0;j<switches.length;j++){
			arr=switches[j].toCharArray();
			for(int i=0;i<arr.length;i++){
				if(arr[i]=='Y'){
					sw[j] = sw[j] | (1<<(arr.length - i-1));
				}
			}
			System.out.println("switch : "+j+" ==> "+Long.toBinaryString(sw[j]));
		}
		
		int[] store=new int[1<<50];
		Arrays.fill(store, -1);
		System.out.println("In : "+Integer.SIZE);
		
		for(int i=0;i<store.length;i++){
			
		}
		
		
		return 0;
	}

}
