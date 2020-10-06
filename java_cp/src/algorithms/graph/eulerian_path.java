package algorithms.graph;

public class eulerian_path {
	
	/*
	 * It is undirected multi edge graph
	 * Using adjency matrix
	 * Check if eulerian path or cycle possible by even degree or atleast two odd
	 * If two odd degree then add an edge to v1 to v2 then choose a start
	 * Then use stack and backtrack to find the path
	 * Using dfs or stack
	 * while(stack empty)
	 * 	Let V be the top at stack
	 * 	if V has 0 degree then pop it and add to result
	 * 	If V not 0 degree then remove that edge and add the other end to the stack
	 *
	 *At the end remove the edge v1 v2
	 */

}
