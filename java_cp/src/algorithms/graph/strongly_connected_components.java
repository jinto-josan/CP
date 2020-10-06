package algorithms.graph;

import java.util.ArrayDeque;
import java.util.Iterator;

public class strongly_connected_components {
	// This is done on a digraph with loops and multiedge allowed
	// do a dfs to get reverse topological order of nodes(decreasing exit time)
	// start from the nodes from above and do second dfs on transposed graph
	// the vertex so recieved are connected components and nodes on which dfs2 is
	// done are  vertex of condensed graph
	graph g;
	graph gt; // transposed graph in which directions are reversed
	ArrayDeque<Integer> q = new ArrayDeque<Integer>();
	ArrayDeque<Integer> components = new ArrayDeque<Integer>();

	public strongly_connected_components(graph g,graph gt) {
		// TODO Auto-generated constructor stub
		this.g =g;
		this.gt= gt;
		g.reset();
		for (int i = g.start_index; i < g.array_size; ++i) {
			if (!g.visited[i])
				dfs1(i);
		}
		gt.reset();
		while (!q.isEmpty()) {
			int n = q.pop();
			if (!gt.visited[n]) {
				dfs2(n);
				while (!components.isEmpty()) {
					System.out.print(components.pop() + " ");
				}
				System.out.println();
			}
		}

	}

	void dfs1(int s) {
		g.visited[s] = true;
		Iterator<Integer> i = g.adj[s].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!g.visited[n]) {
				dfs1(n);				
			}
		}
		q.push(s);// As this function is called only when it's not visited

	}

	void dfs2(int s) {
		gt.visited[s] = true;
		Iterator<Integer> i = gt.adj[s].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!gt.visited[n]) {
				dfs2(n);
			}
		}
		components.push(s);// as only unvisited calls this function and its the invariant

	}

}
