package algorithms.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class dinics {
	
	//This works in O(v2E) and is faster than edmund karp
	
	class Edge {
		// this is a edge which holds to and capacity info
		int from, to, capacity,flow;
		Edge residual;

		Edge(int from, int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
			this.from = from;
		}

		void augment(int flow) {
			
			//which will cause to reduce capacity
			this.flow += flow;
			
			//as flow is in opposite direction for residual edge
			this.residual.flow -= flow;
		}

		int remaining_capacity() {
			return this.capacity  - flow;
		}
	}

	final static int INF = Integer.MAX_VALUE;
	int level[];
	int V;
	int s, t;
	LinkedList<Edge> adj[];

	dinics(int nofV, int s, int t) {
		this.V = nofV;
		this.s = s;
		this.t = t;
		this.level = new int[V];
		adj = new LinkedList[V];
		for(int i=0; i<V; ++i) {
			adj[i] = new LinkedList<Edge>();
		}
	}

	int solve() {

		int next[] = new int[V];
		int max_flow = 0;

		while (bfs()) {
			// this is a part of Shimon and Itai optimization to prune dead ends
			Arrays.fill(next, 0);

			for (int f = dfs(s, next, INF); f != 0; f = dfs(s, next, INF)) {
				// loop is necessary because dfs doesnt go all the way it stops when it reaches
				// t
				max_flow += f;
			}

		}
		return max_flow;

	}

	boolean bfs() {

		// Assigning levels
		Arrays.fill(level, -1);
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.addLast(s);
		level[s] = 0;

		while (!q.isEmpty()) {
			int from = q.pollFirst();

			Iterator<Edge> i = adj[from].listIterator();
			while (i.hasNext()) {
				Edge e = i.next();
				// if level not assigned earlier and capacity of edge to the TO node is >0
				if (level[e.to] == -1 && e.remaining_capacity() > 0) {
					level[e.to] = level[from] + 1;
					q.addLast(e.to);
				}
			}

		}
		// to check bfs reaches the target node
		return level[t] != -1;
	}

	int dfs(int at, int next[], int flow) {
		// to get number of edges at the 'at' node
		if (at == t)
			return flow;
		int noOfEdges = adj[at].size();
		for (; next[at] < noOfEdges; ++next[at]) {// ++next[at] make sure that same edge is not repeated in a different
													// path for this current bfs
			Edge e = adj[at].get(next[at]);
			int cap = e.remaining_capacity();
			if (cap > 0 && level[e.to] == level[at]+1) {
				int bottleneck = dfs(e.to, next, Math.min(flow, cap));
				if (bottleneck > 0) {
					e.augment(bottleneck);
					return bottleneck;
				}
			}

		}

		return 0;
	}
	
	void addEdge(int from, int to, int capacity) {
		Edge e1 = new Edge(from, to , capacity);
		Edge e2 = new Edge(to,from,0);
		e1.residual = e2;
		e2.residual =e1;		
		adj[from].add(e1);
		adj[to].add(e2);
		
	}

	public static void main(String args[]) {

		int n = 6;
		int s = n - 1;
		int t = n - 2;

		dinics solver = new dinics(n, s, t);

		// Source edges
		solver.addEdge(s, 0, 10);
		solver.addEdge(s, 1, 10);

		// Sink edges
		solver.addEdge(2, t, 10);
		solver.addEdge(3, t, 10);

		// Middle edges
		solver.addEdge(0, 1, 2);
		solver.addEdge(0, 2, 4);
		solver.addEdge(0, 3, 8);
		solver.addEdge(1, 3, 9);
		solver.addEdge(3, 2, 6);

		System.out.println(solver.solve());
	}

}




