package algorithms.graph;

public class connected_components {
	/*
	 * Do bfs or dfs from one node. Then check if all nodes visited.
	 * If univisited node then that is a second component and so on.
	 */
	public connected_components(graph g) {
		// TODO Auto-generated constructor stub
		System.out.println("No of components is "+components(g));
	}
	int components(graph g) {
		int comp =0;
		g.reset();
		for(int i=g.start_index;i<g.array_size;++i) {
			if(!g.visited[i]) {
				new dfs(g,i,false);
				++comp;
			}
			
		}
		
		return comp;
	}

}
