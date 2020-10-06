package algorithms.graph;

import java.util.Iterator;

public class find_articulation {
	
	//modified dfs to find articulation points and to check if there is a back edge to ancestor
	//here parent is the main part
	
	int tin[] ;// in time of node
	int low[] ;// min (tin[n],tin[p],low[nxt])
	int timer;//a timer to keep time and not level
	graph g;
	find_articulation(graph g){
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
		int children=0;
		g.visited[n] =true;
		tin[n]= low[n] = ++timer;
		Iterator<Integer> i = g.adj[n].listIterator();
		while(i.hasNext()) {
			int nxt = i.next();
			if(nxt == p ) continue;//if self loops
			if(g.visited[nxt]) {
				low[n] = min(low[n],tin[nxt]);
			}
			else {				
				dfs(nxt,n);
				low[n] = min(low[n],low[nxt]);
				if(low[nxt]>=tin[n] && p!=-1) {//here it is >= and p!=-1 necessary as tin and low[1] is the minimum
					System.out.println(n+" is a articulation point");
				}
				++children;
				
			}
		}
		if(p == -1 && children > 1)//this is to check if there is disconnected component to first element when its removed
			System.out.println(n+" is a articulation point");
		
		
	}
}
