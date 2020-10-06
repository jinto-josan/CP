package algorithms.graph;
import java.util.ArrayDeque;
import java.util.Iterator;

public class topological_sort {
	// its like outputting based on exit time in increasing order when dfs done
	// again a variation of dfs
	// basically for an acyclic digraph
	ArrayDeque<Integer> q = new ArrayDeque<Integer>();
	graph g;
	public topological_sort(graph g, int elem) {
		// TODO Auto-generated constructor stub
		this.g = g;
		g.reset();
		sort(elem);
		for(int i : q) {
			System.out.println(q.pop());
		}
	}

	void sort(int s) {
		
		g.visited[s] =true;
		Iterator<Integer> i = g.adj[s].listIterator();
		while(i.hasNext()) {
			int n = i.next();
			if(!g.visited[n]) {sort(n);}
		}
		q.add(s);
		
	}

}
