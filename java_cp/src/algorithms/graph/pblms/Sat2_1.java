package algorithms.graph.pblms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Sat2_1 {
	/*
	 * @website :- CODECHEF
	 * 
	 * @code :- COMAPR06 (Chess championship)
	 * 
	 * @category 2-sat
	 * 
	 * @input
	 * 
	 * @params t :- no of test cases
	 * 
	 * @params :- n - no of players and m - no of practice matches
	 * 
	 * @params :-m lines of a b practice matchup
	 * 
	 * @output
	 * 
	 * @params :- boring if played between two dull player else interesting
	 * 
	 * @constraints
	 * 
	 * @ 1 ≤ t ≤ 50
	 * 
	 * @ 2 ≤ n ≤ 100000
	 * 
	 * @ 1 ≤ m ≤ 100000
	 * 
	 * @ 1 ≤ a, b ≤ 2*n
	 * 
	 * @ time limit 1 sec
	 * 
	 * if ith is smart then his friend who is dull will be at m+ith number
	 */

	// Naive approach
	// Find all combination of 1 & 0 of m players and check if two 0's come in
	// matchup
	
	//using 2sat approach:-
	/*
	 * In this we have to find variables and the conditions are the m lines which is acyclic and
	 * we have to see if both a & a' lie in same strongly connected components
	 * We have to have 2 sat clauses connected by and based on two literals
	 */
	
	static LinkedList<Integer> graph[];
	static LinkedList<Integer> revgraph[];
	static boolean visited[];
	static boolean revvisited[];
	static int componentix[]; //to store the component number in each vertex;
	static int noOfMembers;
	static int noOfVertices ;
	static Stack<Integer> q = new Stack<Integer>();
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
	
	static void initialize() {
		//initializing for each test case
		// as it is 1 index based
		graph = new LinkedList[noOfVertices+1];
		revgraph = new LinkedList[noOfVertices+1];
		visited = new boolean[noOfVertices+1];
		revvisited = new boolean[noOfVertices+1];
		componentix = new int[noOfVertices+1];
		for(int i =1;i<noOfVertices+1;++i) {
			graph[i] = new LinkedList<Integer>();//adjacency list for first graph
			revgraph[i] = new LinkedList<Integer>();//reversed graph
		}
	}
	
	static void addEdge(LinkedList<Integer> n[],int u, int v) {
		n[u].add(v);
	}
	
	static void dfs1(int v) {
		//for storing elements in reverse topographical order
		visited[v] = true;
		Iterator<Integer> i = graph[v].listIterator();
		while(i.hasNext()) {
			int num = i.next();
			if(!visited[num])
				dfs1(num);
		}
		q.push(v);//now the q will have decreasing elements in order of reverse topological sort
	}
	
	static void dfs2(int v, int index) {
		revvisited[v] = true;
		Iterator<Integer> i = revgraph[v].listIterator();
		while(i.hasNext()) {
			int num = i.next();
			if(!revvisited[num]) {
				dfs2(num, index);
				
			}
			
		}
		componentix[v] = index;//putting all members of a component with same value
	}
	
	static int friend(int i){
		if(i>noOfMembers)
			return i-noOfMembers;
		return i+noOfMembers;
	}
	
	static void solve() {
		// here clause (p or q) which turns into !p ->q and !q -> p edges in implication graph
		// here no i and m+i shouldnot be in same strongly connected components for interesting game
		for(int i=1; i<noOfVertices+1;++i) {
			if(!visited[i])
				dfs1(i);
		}
		int ix =0;
		while(!q.isEmpty()) {
			int n = q.pop();
			if(!revvisited[n]) {
				++ix;
				dfs2(n, ix);
			}			
				
		}
		for(int i=1;i<=noOfMembers;++i) {
			if(componentix[i] == componentix[friend(i)]) {
				System.out.println("boring");
				return;
			}
		}
		System.out.println("Interesting");
	}
	
	
	
	public static void main(String args[]) {
		FS sc = new FS();
		int t = sc.nextInt();
		while (t > 0) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			noOfMembers = n;
			noOfVertices = 2*n; //as both the participant and friend participates
			initialize();
			for(int i=1;i<=m;++i) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				//condition is p V q
				addEdge(graph, friend(a), b); // ~p ->q
				addEdge(graph, friend(b), a);// ~q -> p
				addEdge(revgraph, b, friend(a));
				addEdge(revgraph, a, friend(b));
			}
			solve();
			--t;

		}
	}

}
