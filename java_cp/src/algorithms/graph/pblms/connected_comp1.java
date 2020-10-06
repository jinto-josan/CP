package algorithms.graph.pblms;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.*;

public class connected_comp1 {//class has to be public class Main
	/*
	 * CODECHEF GERALD07 time limit exceeded error
	 */
	static class FS {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");

		String next() {
			while (!st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

	public static void main(String[] args) throws java.lang.Exception {
		FS sc = new FS();
		PrintWriter out = new PrintWriter(System.out);
		int test = sc.nextInt();
		LinkedList<Integer> res = new LinkedList();
		for (int i = 1; i <= test; ++i) {
			int v = sc.nextInt();
			int ed = sc.nextInt();
			int q = sc.nextInt();
			EdgeIndex arr[] = new EdgeIndex[ed + 1];
			for (int j = 1; j <= ed; ++j) {
				arr[j] = new EdgeIndex(sc.nextInt(), sc.nextInt());
			}
			for (int l = 1; l <= q; ++l) {
				bellman_ford g = new bellman_ford(v);
				int lx = sc.nextInt();
				int rx = sc.nextInt();
				for (int x = lx; x <= rx; ++x) {
					g.addEdge(arr[x].u, arr[x].v);
					// g.addEdge(arr[x].v, arr[x].u);
				}
				res.add(g.connected_components());
			}

		}
		for (int i : res) {
			out.println(i);
		}
		out.close();
	}

}

class bellman_ford {
	LinkedList<Integer> adj[];
	boolean visited[];
	int noOfVertex;

	bellman_ford(int v) {
		this.noOfVertex = v;
		adj = new LinkedList[v + 1];
		for (int i = 1; i <= v; ++i) {
			adj[i] = new LinkedList<Integer>();
		}
		visited = new boolean[v + 1];

	}

	void addEdge(int u, int v) {
		adj[u].add(v);
	}

	void dfs(int s) {
		Iterator<Integer> i = adj[s].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n]) {
				visited[n] = true;
				dfs(n);
			}
		}
	}

	int connected_components() {
		int con = 0;
		for (int i = 1; i <= noOfVertex; ++i) {
			if (!visited[i]) {
				dfs(i);
				++con;
			}
		}
		return con;
	}

}

class EdgeIndex {
	int u;
	int v;

	EdgeIndex(int u, int v) {
		this.u = u;
		this.v = v;
	}
}
