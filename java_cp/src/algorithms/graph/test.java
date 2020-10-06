package algorithms.graph;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
public class test {
	static private final String und_graph = "undirected_graph.gv";
	static private final String d_graph = "directed_graph.gv";
	public static void main(String args[]) {
		FileInputStream undstream = null;
		FileInputStream dstream = null;
//		graph undirected = new graph(5,1);
		graph directed = new graph(5,1);
		graph directedT = new graph(5,1);
		//PrintStream console = System.out; // have to set earlier so that System.out gives standard one
		try {
//			undstream = new FileInputStream(und_graph);
			dstream = new FileInputStream(d_graph);
			
//			System.setIn(undstream);
			System.setIn(dstream);
		}
		catch(Exception e) {
			System.err.println("error");
		}
		Scanner sc = new Scanner(System.in);
//		while(sc.hasNext()){
//			if(sc.hasNextInt()) {
//				int x = sc.nextInt();
//				sc.next();
//				int y = sc.nextInt();
//				undirected.addEdge(x, y);
//				undirected.addEdge(y, x);//this is necessary in case of undirected graphs else it becomes directed and algo's wont work properly
//			}
//			sc.next();
//		}
		
		// to read directed graph
		System.setIn(dstream);
		sc = new Scanner(System.in);
		while(sc.hasNext()){
			if(sc.hasNextInt()) {
				int x = sc.nextInt();
				sc.next();
				int y = sc.nextInt();
				directed.addEdge(x, y);
				directedT.addEdge(y, x);
			}
			sc.next();
		}
		
		//for printing to console		
		//System.setOut(console);
		
//		directed.display_adj();
//		directedT.display_adj();
		//directed.display_adj();

		
		//for acyclic digraph
//		System.out.println("-----------Topological Sort-----------");
//		new topological_sort(directed, 1);
//		System.out.println("-----------Strongly Connected Components-----------");
//		new strongly_connected_components(directed,directedT);
		
		
		//undirected graph
//		System.out.println("-----------Bridges----------");
//		new find_bridge(undirected);
//		System.out.println("-----------Articulation Points-----------");
//		new find_articulation(undirected);
//		System.out.println("-----------Connected Components-----------");
//		new connected_components(undirected);
		
		//any graph
//		System.out.println("-----------DFS-----------");
//		new dfs(undirected, 1, true);
//		System.out.println("-----------BFS-----------");
//		new bfs(undirected, 1);
		
//		Shortest path 
		int graph_sp[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
//		System.out.println("-----------SSSP Dijkstra-----------");
//		new dijkstra(graph_sp, 0);
//		System.out.println("-----------SSSP  Bellman ford-----------");
//		System.out.println("-----------SSSP D'esop0-----------");
		
//		All shortest path
		System.out.println("-----------Warshall Floyd-----------");
		new warshall_floyd(graph_sp);
		
		
		
	}

}
