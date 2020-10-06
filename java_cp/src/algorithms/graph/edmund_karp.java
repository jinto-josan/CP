package algorithms.graph;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;

public class edmund_karp {

	/*
	 * For Directed  and multigraph same concept 
	 * based on ford fulkerson for maximum flow
	 * 
	 * Important concept
	 * The method uses residual method. In this for every edge in 
	 * the graph a reverse edge is stored which stores how many of flown value
	 * can be reversed.
	 * 
	 * bfs method and is independent of max flow
	 * complexity o[VE^2]
	 * 
	 */
	class Vertex{
		//this stores the min flow till this vertes
		int v,flow;
		Vertex(int v ,int flow){
			this.v = v;
			this.flow =flow;
		}
	}
	int capacity[][];
	static int V;
	static LinkedList<Integer> adj[];
	int parent[];	
	static final int INF = Integer.MAX_VALUE;
	
	public edmund_karp(int capacity[][]) {
		// TODO Auto-generated constructor stub
		this.capacity = capacity;
		parent = new int[V];
	}
	
	void reset_parent() {
		for(int i =0;i<V;++i) {
			parent[i]=-1;
		}
	}
	int bfs(int source, int target) {
		//in this bfs is used to find shortest path to target
		//reseting parent array so that the branch which reached target from
		//source first can be retracked
		reset_parent();
		
		//this q stores the Vertex and flow till the vertex for each branch using bfs
		ArrayDeque<Vertex> q = new ArrayDeque<Vertex>();
		//as this is the source it's put as infinity
		q.addLast(new Vertex(source,INF));
		
		while(!q.isEmpty()) {
			Vertex el = q.pollFirst();
			int node = el.v;
			int flow =el.flow;
			Iterator<Integer>  i = adj[node].listIterator();
			while(i.hasNext()) {
				int n = i.next();
				//checking if this edge has reached limit or not
				//also it hasnt been visited till now
				if(parent[n]==-1 && capacity[node][n]>0) {
					//Checking if current flow can go through
					int new_flow= Math.min(flow, capacity[node][n]);
					//changing parent so as to keep track
					parent[n] = node;
					if(n == target) {
						return new_flow;
					}
					q.addLast(new Vertex(n,new_flow));
					
				}
				
			}
			
		}
		return 0;
	}
	void solve(int s, int t) {
		
		int new_flow= bfs(s,t);
		int flow =0;
		while(new_flow>0) {		
		flow+=new_flow;
		int cur = t;
		//bactracking and changing the capacity matrix
		while(cur != s) {
			int prev_node = parent[cur];
			//flow is added in reverse order , so that if necessary we can send flow backward
			//this is the main point of residual graph method. 
			//flow is subtracted in s to t direction
			capacity[prev_node][cur]-=new_flow;
			capacity[cur][prev_node]+=new_flow;
			cur = prev_node;
		}
		new_flow = bfs(s,t);
		}
		System.out.println(flow);
		
	}
	void populate_adj(int arr[][]) {
		adj = new LinkedList[V];
		for(int i =0;i<V;++i) {
			adj[i] = new LinkedList<Integer>();
			for(int j=0;j<V;++j) {
				if(arr[i][j]!= 0) {					
					adj[i].add(j);
				}
			}
		}
//		for(int i =0;i<V;++i) {
//			Iterator<Integer> j = adj[i].listIterator();
//			System.out.print(i+" ");
//			while(j.hasNext()) {
//				System.out.print(j.next()+" ");
//				
//			}
//			System.out.println();
//			
//		}
		
		
		
	}
	public static void main(String args[]) {
//		int capacity[][] =new int[][] { {0, 16, 13, 0, 0, 0}, 
//            {0, 0, 10, 12, 0, 0}, 
//            {0, 4, 0, 0, 14, 0}, 
//            {0, 0, 9, 0, 0, 20}, 
//            {0, 0, 0, 7, 0, 4}, 
//            {0, 0, 0, 0, 0, 0} 
//          }; 
		int capacity[][] = new int[][] {{0,3,2,0},
			{0,0,5,2},{0,0,0,3},{0,0,0,0}};
		V = capacity[0].length;
		edmund_karp obj = new edmund_karp(capacity);
		obj.populate_adj(capacity);
		
		obj.solve(0,3);
	}
}
