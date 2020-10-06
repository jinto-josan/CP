package algorithms.graph;

import java.util.Iterator;
import java.util.LinkedList;

class graph {
	int noOfVertices;
	int start_index;
	int array_size;
	LinkedList<Integer> adj[];
	boolean visited[];

	graph(int v, int start_index) {
		noOfVertices = v;
		this.start_index = start_index;
		array_size = noOfVertices + start_index; // this value has to be taken when arrays considered
		adj = new LinkedList[array_size];// for easy propogation
		for (int i = 0 + start_index; i <= v; ++i) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	void addEdge(int u, int v) {
		adj[u].add(v);
	}

	void display_adj() {
		for (int i = start_index; i < array_size; ++i) {
			System.out.print(i + " -->");
			Iterator<Integer> ele = adj[i].listIterator();
			while (ele.hasNext()) {
				System.out.print(ele.next() + " ");
			}
			System.out.println();
		}
	}
	void reset() {
		visited = new boolean[array_size];
	}

}
