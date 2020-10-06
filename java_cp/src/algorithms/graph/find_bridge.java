package algorithms.graph;

import java.util.Iterator;

public class find_bridge {
	
	//modified dfs to find bridges for undirected graphs where back edges are looked
	//lowest time of all parents and children stored in that node
	
	int tin[] ;// in time of node
	int low[] ;// min (tin[n],tin[p],low[nxt])
	int timer;//a timer to keep time and not level
	graph g;
	find_bridge(graph g){
		this.g=g;
		g.reset();
		tin = new int[g.array_size];
		low = new int[g.array_size];
	    for (int i = g.start_index; i < g.array_size; ++i) {//for each component
	        if (!g.visited[i])
	        	timer = 0;
	            dfs(i,-1);
	    }
	}
	int min(int x, int y) {return x<y?x:y;}
	void dfs(int n, int p) {
		g.visited[n] =true;
		tin[n]= low[n] = ++timer;
		Iterator<Integer> i = g.adj[n].listIterator();
		while(i.hasNext()) {
			int nxt = i.next();
			if(nxt == p ) continue;//if cycles
			if(g.visited[nxt]) {
				low[n] = min(low[n],tin[nxt]);//here tin will be lower has it is a node for a backedge from n
			}
			else {
				
				dfs(nxt,n);
				low[n] = min(low[n],low[nxt]);//here taking the smallest time of decedant
				if(low[nxt]>tin[n]) {// here it is > //so it means no backedge
					System.out.println(n+"---"+nxt+" is a bridge");
				}
			}
		}
		
	}

}
