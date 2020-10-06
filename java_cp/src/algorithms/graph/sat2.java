package algorithms.graph;

public class sat2 {
	/*
	 * Problem will be given in CNF (a V B) ^ (c V !B)..
	 * Convert it into a V B = !a => b ^ !b => a for all other
	 * Then create directed graph using implication
	 * Then check strongly connected groups. If same group has !a and a then no solution
	 * Other wise solution exists
	 */

}
