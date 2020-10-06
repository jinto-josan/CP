package algorithms.graph;

import java.util.Iterator;

public class dfs{
	/*
	 * Start from a vertex go all the way down.
	 * Then come one step up then go to adjacent of that all the way down
	 * and so on till reaching the vertex
	 */
	graph g;
	dfs(graph g,int elem,boolean output_show){
		this.g=g;
		g.reset();
		DFS(elem,output_show);
	}
	
	void DFS(int s,boolean show) {
		g.visited[s] =true;
		Iterator<Integer> i = g.adj[s].listIterator();
		while(i.hasNext()) {
			int n = i.next();
			if(!g.visited[n]) {				
				DFS(n,show);				
			}
		}
		if(show)
			System.out.println(s);
		
	}

}
