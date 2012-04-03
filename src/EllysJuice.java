import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EllysJuice {

	/**
	 * @param args
	 */
	
	static Map<String, Integer> count=new HashMap<String, Integer>();
	static int ind=0;
	static int[] total;
	static int[] playerID;
	static int max=0;
	
	static boolean[] wins;
	
	public static void main(String[] args) {
		
		String[] players = {"torb", "elly", "stancho", "kriss"};
		
		System.out.println("ans is : "+Arrays.toString(getWinners(players)));

	}
	
	public static String[] getWinners(String[] players) {
		
		playerID=new int[players.length];
		
		for(int i=0;i<players.length;i++){
			if(!count.containsKey(players[i])){
				count.put(players[i], ind++);
			}
			playerID[i]=count.get(players[i]);
		}
		
		total = new int[ind];
		wins=new boolean[ind];
		boolean[] used=new boolean[players.length];
		
		max=players.length/2 + players.length%2;
		
		
		recur(used, 0);
		List<String> retList=new ArrayList<String>();
		
		for(String pl : count.keySet()){
			int id = count.get(pl);
			if(wins[id])
				retList.add(pl);
		}
		String[] ret=new String[retList.size()];
		Collections.sort(retList);
		int k=0;
		for(String str : retList){
			ret[k++]=str;
		}
		
		return ret;
	}
	
	public static void recur(boolean[] state, int count){
		if(count == state.length){
			int max=-1;
			int loc =-1;
			boolean matched=false;
			
			for(int j=0;j<total.length;j++){
				if(max<total[j]){
					max=total[j];
					loc=j;
					matched=false;
					continue;
				}
				if(max==total[j]){
					matched=true;
				}
			}
			if(loc!=-1 && !matched){
				wins[loc]=true;
			}
			System.out.println("str : "+Arrays.toString(total));
			
		}
		for(int i=0;i<state.length;i++){
			if(state[i])
				continue;
			else{
				state[i]=true;
				total[playerID[i]]+=(1<<(max-count/2-1));
				//System.out.println("count : "+count+" : "+(max-count/2));
				recur(state, count+1);
				total[playerID[i]]-=(1<<(max-count/2-1));
				state[i]=false;
			}
		}
	}

}
