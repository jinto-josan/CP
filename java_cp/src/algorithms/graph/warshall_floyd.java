package algorithms.graph;

import java.util.ArrayDeque;

public class warshall_floyd {
	/*
	 * Before kth phase the path from i to j consists of only internal vertices
	 * which are less than k(Invariant)
	 * 
	 * And this is done using a matrix .At kth time we check for a condition if
	 * d[i][j]>d[i][k]+d[k][j] then modify d[i][j] and this is done for k =1 to N
	 * 
	 * This is used for both directed and undirected with negitive edges and no
	 * negitive cycles For real value always the error has to be subtracted as it
	 * gets accumulated
	 */
	int dist[][];
	static int max = Integer.MAX_VALUE;
	int prev[][];

	warshall_floyd(int g[][]) {
		dist = g;
		prev =new int[g[0].length][g[0].length];
		//setting no edges as infinity
		initialize();
		solve();
	}

	void solve() {
		int v = dist[0].length;
		
		
		for (int k = 0; k < v; ++k) {
			for (int i = 0; i < v; ++i) {
				for (int j = 0; j < v; ++j) {
					// for negitive weighted edges
					if (dist[i][k] != max && dist[k][j] != max) {
						int sum = dist[i][k] + dist[k][j];
						// check if any path from i to j having {1..k as internal}
						if (dist[i][j] > sum) {
							dist[i][j] = sum;
							prev[i][j] =k;
						}
					}
				}
			}
		}
		display();

	}
	void initialize() {
		for(int i =0;i<dist[0].length;++i) {
			for(int j =0; j<dist[0].length;++j) {
				prev[i][j] =j;
				if(dist[i][j]==0) {
					prev[i][j] =-1;
					dist[i][j]=max;		
					
				}
				if(i==j) {
					dist[i][j]=0;
					prev[i][j] =i;
				}
			}
		}
	}
	ArrayDeque<Integer> retrieve_path(int u, int v) {
		ArrayDeque<Integer> path = new ArrayDeque<Integer>();
		if(prev[u][v]==-1)
			return null;
		path.addLast(u);
		while(u!=v) {
			u = prev[u][v];
			path.addLast(u);
		}
		return path;
	}
	void display() {
		for(int i =0;i<dist[0].length;++i) {
			for(int j =0; j<dist[0].length;++j) {
				System.out.print(i+" to " +j+" ");
				System.out.print(dist[i][j]+"   ");
				ArrayDeque<Integer> path = retrieve_path(i, j);
				if(path ==null) {
					System.out.println("--> no path");
					continue;
					
				}
				while(!path.isEmpty()) {
					System.out.print("-->"+path.pollFirst());
				}
				System.out.println();
			}
			
		}
	}
	public static void main(String args[]) {
		int graph[][] = { {0,   5,  0, 10}, 
                {0, 0,   3, 0}, 
                {0, 0, 0,   1}, 
                {0, 0, 0, 0} 
              };
		new warshall_floyd(graph);
	}

}
