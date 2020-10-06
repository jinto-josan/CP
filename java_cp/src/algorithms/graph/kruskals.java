package algorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class kruskals {
	/*
	 * This is faster for sparse graphs
	 * Works in Mlogn +n ^2 time
	 * Keep all vertex initially as seperate trees id
	 * Sort the edge by weight in increasing order
	 * Take each edge(u,v) one by one if edge joins two tree then change the 
	 * tree id of all vertex having u as id to v as id
	 */
	ArrayList<Edge> edges = new ArrayList<Edge>();
	int V=4;
	LinkedList<Edge> result = new LinkedList<Edge>();
	static class Edge implements Comparable<Edge>{
		int u,v,w;
		Edge(int u, int v, int w){
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge e) {
			// TODO Auto-generated method stub
			return  this.w -e.w ;//for asscending order
		}
	}	
	void solve() {
		int tree_id[] = new int[V];
		for(int i=0;i<V;++i) {
			tree_id[i]=i;
		}
		
		//sorting the edges
		Collections.sort(edges);
		for(Edge e : edges) {
			//joining the trees when edges with vertex lying in two subtree appear
			if(tree_id[e.u] != tree_id[e.v]) {
				result.push(e);				
				int old_id = tree_id[e.u], new_id = tree_id[e.v];
				for(int i =0;i<V;++i) {
					if(tree_id[i]==old_id) {
						tree_id[i] = new_id;
					}
				}
			}
		}
		while(!result.isEmpty()) {
			Edge e = result.pop();
			System.out.println(e.u+" --> "+e.v+ "  "+e.w);
		}
	}
	public static void main(String args[]) {
		kruskals obj = new kruskals();
		obj.edges.add(new Edge(0,1,10));
		obj.edges.add(new Edge(0,2,6));
		obj.edges.add(new Edge(0,3,5));
		obj.edges.add(new Edge(1,3,15));
		obj.edges.add(new Edge(2,3,4));
		obj.solve();
		
	}

}

