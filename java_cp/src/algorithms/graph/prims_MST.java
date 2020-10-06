package algorithms.graph;

public class prims_MST {
	
	/*
	 * It works well for connected directed or undirected weighted graph
	 * If the graph is no connected then mst cant be built
	 * Time complexity :- O(n^2)
	 * Adjacency matrix is used
	 * A vertex which is unselected and is having minimum value in mwa is taken
	 * Minimum edge array(mwa) is updated if the weight of edge from the selected vertex
	 * to another vertex is less than the value of that vertex in mwa
	 * For sparse array rb tree can be used so that finding min completes in nlogn time
	 */
	class Edge{
		int to,w;
		Edge(int to , int w){
			this.to = to;
			this.w = w;
		}
	}
	static int INF = Integer.MAX_VALUE;
	int adj[][];
	int v;
	boolean visited[];
	Edge min_e[];
	public prims_MST(int adj[][]) {
		// TODO Auto-generated constructor stub
		this.adj = adj;
		v= adj[0].length;
		visited = new boolean[v];
		min_e = new Edge[v];
		for(int i=0;i<v;++i) {
			min_e[i] = new Edge(-1,INF);
		}
		solve();
	}
	void solve() {
		
		min_e[0].w =0;// so that the first element can be worked
		
		for(int i=0;i<v;++i) {
			//selecting vertex with minimum weight in the min_e
			int n =-1;
			for(int j=0;j<v;++j) {
				if(!visited[j]&& (n ==-1 || min_e[j].w<min_e[n].w))
					n = j;
			}
			
			//checking if this node is connected to anything
			if(min_e[n].w == INF) {
				System.out.println("No MST exist");
				break;
			}
			
			
			//updating min_e
			visited[n]= true;
			for(int j=0;j<v;++j) {
				if(adj[n][j]<min_e[j].w) {
					min_e[j].w = adj[n][j];
					min_e[j].to = n;//this be careful  as it stores from
				}
			}
		}
		display();
		
	}
	void display() {
		for(int i=0;i<v;++i) {
			System.out.println(i+" ---> "+min_e[i].to+"  "+ min_e[i].w);
		}
	}
	public static void main(String args[]) {
		int adj[][]={ { INF, 2, INF, 6, INF }, 
            { 2, INF, 3, 8, 5 }, 
            { INF, 3, INF, INF, 7 }, 
            { 6, 8, INF, INF, 9 }, 
            { INF, 5, 7, 9, INF } }; 
		new prims_MST(adj);
		
	}

}
