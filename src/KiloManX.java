import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;


public class KiloManX {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] damageChart={"198573618294842",
				 "159819849819205",
				 "698849290010992",
				 "000000000000000",
				 "139581938009384",
				 "158919111891911",
				 "182731827381787",
				 "135788359198718",
				 "187587819218927",
				 "185783759199192",
				 "857819038188122",
				 "897387187472737",
				 "159938981818247",
				 "128974182773177",
				 "135885818282838"};
		int[] bossHealth={157, 1984, 577, 3001, 2003, 2984, 5988, 190003,
				9000, 102930, 5938, 1000000, 1000000, 5892, 38};
		System.out.println("ans is : "+leastShots(damageChart, bossHealth));
	}
	
	public static int leastShots(String[] damageChart, int[] bossHealth){
		int size=bossHealth.length;
		int[][] dm=new int[size][size];
		boolean[] visited=new boolean[1<<size];
		int[] cst=new int[1<<size];
		
		for(int i=0;i<size;i++){
			char[] arr=damageChart[i].trim().toCharArray();
			for(int j=0;j<size;j++){
				dm[i][j]=arr[j]-'0';
			}
		}
		
		Node best = null;
		Node s=new Node(0, 0);
		
		TreeSet<Node> pq=new TreeSet<Node>();
		
		pq.add(s);
		visited[s.weapons]=true;
		cst[s.weapons]=s.cost;
		
		
		
		while(pq.size()>0){
			
			Node n=pq.first();
			pq.remove(pq.first());
			
			if(n.weapons==((1<<size) - 1)){
				best=n;
				System.out.println(n.weapons+" : "+(1<<size));
				break;
			}
			
			
			for(int i=0;i<size;i++){
				if((n.weapons>>i & 1)==1){
					continue;
				}
				int bc=bossHealth[i];
				for(int j=0;j<size;j++){
					if((n.weapons>>j & 1)!=1 || dm[j][i]==0){
						continue;
					}
					int shots=bossHealth[i]/dm[j][i];
					if(bossHealth[i]%dm[j][i]>0)
						shots++;
					if(shots<bc)
						bc=shots;
				}
				Node nxt=new Node(n.weapons | (1<<i), n.cost+bc);
				if(!visited[nxt.weapons] || nxt.cost<cst[nxt.weapons]){
					pq.add(nxt);
					visited[nxt.weapons]=true;
					cst[nxt.weapons]=nxt.cost;
				}
			}
		}
		
		return best.cost;
	}
}

class Node implements Comparable<Node>{
	int weapons;
	int cost;
	public Node(int weapons, int cost){
		this.weapons=weapons;
		this.cost=cost;
	}
	@Override
	public int compareTo(Node o) {
		if(cost>o.cost)
			return 1;
		if(cost<o.cost)
			return -1;
		if(o.weapons>weapons)
			return -1;
		if(o.weapons<weapons)
			return 1;
		return 0;
	}
	
}
