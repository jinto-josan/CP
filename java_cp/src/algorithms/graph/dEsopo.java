package algorithms.graph;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;

public class dEsopo {
	/*
	 * It is comparatively faster than dikjstra and bellman ford in most case Works
	 * for negitive edges but not negitive cycles
	 * 
	 * M0 > containing vertex whose distance has been calculated M1 > a dequeue
	 * contains vertices which has to be calculated M2 > contains vertices whose
	 * distance hasn't been calculated
	 * 
	 * Take a vertex from front of M1 let it be u . 
	 * and do for all edges comming out of u let one such be v 
	 * If v belongs to M2 then put in back of M1 and d[v] = d[u]+w 
	 * if v belongs to M1 then d1 = min(dv,d[u]+w) 
	 * if v belong to M0 then if d1>d[u]+w then put v to front and update d[v]
	 */
	LinkedList<Edge> adj[];
	int V = 5;

	class Edge {
		int to, w;

		Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}

	class Graph {
		//in this adjacency list are having edges with next node and weight
		Graph() {
			adj = new LinkedList[V];
			for (int i = 0; i < V; ++i) {
				adj[i] = new LinkedList<Edge>();
			}

		}

		void addEdge(int from, int to, int w) {
			adj[from].add(new Edge(to, w));

		}
	}

	int M[] = new int[V];
	ArrayDeque<Integer> q = new ArrayDeque<Integer>();
	int distance[] = new int[V];

	void initialize() {
		for (int i = 0; i < V; ++i) {
			M[i] = 2; // implies all nodes are in M2
			distance[i] = Integer.MAX_VALUE;// putting all value to infinity
		}
	}

	void find(int source) {
		q.addLast(source);
		distance[source] = 0;
		while (!q.isEmpty()) {
			int u = q.pollFirst();
			// now for all adjacent elements of u
			M[u] =0;
			Iterator<Edge> i = adj[u].listIterator();
			while (i.hasNext()) {
				Edge n = i.next();
				if (distance[n.to] > (distance[u] + n.w)) {
					
					distance[n.to] = distance[u] + n.w;
//					if(M[n.to]==1) {
//						
//					}
					if (M[n.to] == 2) {
						M[n.to] = 1;
						q.addLast(n.to);
					} else if (M[n.to] == 0) {
						M[n.to] = 1;
						q.addFirst(n.to);

					}

				}
			}
		}
		for(int i=0;i<V;++i) {
			System.out.println("0 --> "+i+" "+distance[i]);
		}
	}
	public static void main(String args[]) {
		dEsopo obj = new dEsopo();
		Graph g = obj.new Graph();
		obj.initialize();
		g.addEdge(0, 1, -1);
		g.addEdge(0, 2, 4);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 2);
		g.addEdge(1, 4, 2);
		g.addEdge(3, 2, 5);
		g.addEdge(3, 1, 1);
		g.addEdge(4, 3, -3);
		obj.find(0);
  		
	}

}
