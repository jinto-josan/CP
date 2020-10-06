package algorithms.graph.pblms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sat2_2 {
	/*
	 * @website :- CODECHEF
	 * 
	 * @code :- VRTXCOVR (VERTEX COVER)
	 * 
	 * @category 2-sat
	 * 
	 * @input
	 * 
	 * @params t :- no of test cases
	 * 
	 * @params :- n - no of vertex and m - no of edges of undirected graph
	 * 
	 * @params :-m lines of a b edges
	 * 
	 * @output
	 * 
	 * @params :- impossible if no partition can be made such that 2*i and 2*i -1
	 * doesnt belong to same set else possible with an example of 1(A) & 0(B)
	 * 
	 * @constraints
	 * 
	 * @ 1 ≤ t ≤ 10^5
	 * 
	 * @ 1 ≤ n ≤ 2.10^5
	 * 
	 * @ 1 ≤ m ≤ 2.10^5
	 * 
	 * @ 1 ≤ a, b ≤ n
	 * 
	 * @ 1<= sum of N over all test cases <=10^6
	 * 
	 * @ 1<= sum of m over all test cases <=10^6
	 * 
	 * @ time limit 3 sec
	 * 
	 */

	// approach 2 sat wher clauses is X(2i) xor X(2i -1) from truth table as both in
	// two sets not allowed
	// for !X(2i) we use X(2i) +n so as to identify
	int noOfVertices, edges;
	LinkedList<Integer> graph[], revgraph[];
	boolean visited[], revvisited[];
	ArrayDeque<Integer> q = new ArrayDeque<Integer>();
	int componentix[];

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
	
	void addEdge(LinkedList<Integer> g[], int u , int v) {
		g[u].add(v);
	}
	
	void xor(LinkedList<Integer> g[], int u, int v) {
		addEdge(graph, u+noOfVertices, v); //~p-> q
		addEdge(graph, v+noOfVertices, u);//~q-> p
		addEdge(graph, u, v+noOfVertices);//p->~ q
		addEdge(graph, v, u+noOfVertices);//q->~ p
	}
	
	void initialize() {
		
		graph = new LinkedList[noOfVertices];
		revgraph = new LinkedList[noOfVertices];
		visited = new boolean[noOfVertices];
		revvisited = new boolean[noOfVertices];
		componentix = new int[noOfVertices];
		for(int i =0;i<noOfVertices;++i) {
			graph[i] = new LinkedList<Integer>();
			revgraph[i] = new LinkedList<Integer>();
		}
	}
	
	void dfs1(int v) {
		visited[v] = true;
		Iterator<Integer> i = graph[v].listIterator();
		while(i.hasNext()) {
			int n = i.next();
			if(!visited[n])
				dfs1(n);
		}
		q.push(v);
	}
	void dfs2(int v, int ix) {
		revvisited[v] = true;
		Iterator<Integer> i = graph[v].listIterator();
		while(i.hasNext()) {
			int n = i.next();
			if(!revvisited[n])
				dfs2(n, ix);			
		}
		componentix[v] = ix;
		
	}
	
	void solve() {
		
		for(int i=0;i<noOfVertices;++i) {
			if(!visited[i]) {
				dfs1(i);
			}
		}
		int count =1;
		while(!q.isEmpty()) {
			int n = q.pop();
			if(!revvisited[n]) {
				dfs2(n, count);
				++count;
			}
		}
		int num = noOfVertices/4;
		for(int i=0;i<4;++i) {
			int a = 2*i;
			int b = (2*i)+1;
			if(componentix[a] == componentix[b]) {
				System.out.println("impossible");
				return;
			}
		}
		System.out.println("possible");
		
		
	}
	
	public static void main(String args[]) {
		FS sc = new FS();
		Sat2_2 obj = new Sat2_2();
		int t = sc.nextInt();
		for(int i=0;i<t;++i) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			obj.noOfVertices = 2*n;// to store both p and ~p
			for(int j =0; j<m ; ++j) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				obj.initialize();
				// so that it becomes 0 indexed
				a--; 
				b--;
				a = 2*a;
				b = (2*a)+1;
				obj.xor(obj.graph,a,b);
				obj.xor(obj.revgraph,a,b);
				
				obj.solve();
				
			}
		}
	}
}
