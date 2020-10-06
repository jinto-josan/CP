package algorithms.graph;

public class lca {
	/*
	 * Naive method
	 * 	Do a dfs to store the height
	 * 	Do a euler tour and store it in an array
	 * 	In an array named first store the first occurance in euler tour of the element in First array 
	 * 	Then the lowest height element in between the first occurance of l and r is the lca
	 * 	Segment tree O(lg N) or sqrt decomposition O(sqrtN) can be used and processing in O(N) 
	 *
	 *	Binary Lifting
	 * 		In this there is a matrix also stored which stores the 2nd 4th 8th and so on ancestor in matrix [i][j]		
	 */

}
