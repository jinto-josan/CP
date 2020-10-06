package algorithms.graph;

import java.util.Iterator;

public class dijkstra {
	/*
	 * SSSP algo. Graph can be undirected or directerd but only positive weighted
	 * adjency matrix is used to represent the graph
	 * O(n^2 +m) --good for dense where m is no.of edge and n is no of vertex
	 * This n^2 comes from the finding minimum . These can be solved using a rbtree or priority queue
	 * and rather than using visited we can keep on working on minimum and then remove it
	 * In the tree both the index and the distance has to be stored.
	 * This approach gives O(nlogn +mlogn)
	 * Using fibonacci heap we can get O(nlogn +m) but has high hidden constant so better above
	 * 
	 * Interestingly there is thorup's algo for integer weights when queries are repititive, its benificial
	 * as it takes O(m) time
	 */
	int g[][];
	int source;
	int prev_node[];

	dijkstra(int g[][], int source) {
		this.g = g;
		this.source = source;
		solve();
	}

	void solve() {
		int distance[] = new int[g[0].length];// to store SD of ele from a starting point
		for (int i = 0; i < g[0].length; ++i) {
			distance[i] = Integer.MAX_VALUE;
		}
		boolean visited[] = new boolean[g[0].length];// to store if node visited or not
		prev_node = new int[g[0].length]; // to store previous node
		// distance from source to source is 0
		distance[source] = 0;

		// going through all nodes
		for (int i = 0; i < g[0].length; ++i) {
			int n = -1;
			// taking the smallest distant unvisited node
			for (int j = 0; j < g[0].length; ++j) {
				if (!visited[j] && (n == -1 || distance[j] < distance[n])) {
					n = j;
				}
			}
			// if there is no node left which can be reached from source
			if (distance[n] == Integer.MAX_VALUE)
				break;
			// setting the node as visited
			visited[n] = true;

			// adjacent edges to shortest distant node
			for (int j = 0; j < g[0].length; ++j) {
				if (g[n][j] != 0) {//checking if path exists from i to j
					int next = j;
					int new_dist = distance[n] + g[n][j];
					if (new_dist < distance[next]) {
						distance[next] = new_dist;
						prev_node[next] = n;
					}
				}
			}

		}
		display(distance);
	}

	void display(int arr[]) {
		for (int i = 0; i < arr.length; ++i) {
			System.out.println(source + " --> " + i + " = " + arr[i]);
		}

	}

	void restore_path(int s, int t, int prev_node[]) {
		// backtracking to get the node
		for (int i = t; i != s; i = prev_node[i]) {
			System.out.println(i);
		}
	}

}
