package data_structure.special;

public class Union_find {
	/*
	 * 
	 * @application Kruskal min spanning tree
	 * 				Job sequencing
	 * 				Cycle detection
	 * @complexity
	 * 		@ Construction	O(n)
	 * 		@ Union			alpha(n)
	 * 		@ find			alpha(n)
	 * 		@ noOfcomponent	alpha(n)
	 * 		@ checkConnecte	alpha(n)
	 * 		@ countComponen	O(1)
	 */

	int id[];// this stores the root of each node so as to identify which group this element
				// exist
	int rank[];// this stores the height of tree representing the set

	
	Union_find(int size) {
		// size has to be preknown
		
		if (size <=0) throw new IllegalArgumentException("Size cant be less than 0");

		id = new int[size];
		rank = new int[size];
		initialize(size);

	}
	
	void initialize(int size) {
		for(int i=0;i<size;++i) {
			id[i] = i; //each element is root of itself
			rank[i] = 1;
		}
	}

	void union(int a, int b) {
		//join to less rank tree to high rank tree
		int aid = find(a);
		int bid = find(b);
		if(aid == bid)
			return;
		int arank = rank[aid];
		int brank = rank[bid];
		if(arank < brank)
			id[aid] = bid;
		else if(brank < arank)
			id[bid] = aid;
		else //when both same
			{id[bid] = aid; 
			rank[aid]+=1;}

	}

	int find(int a) {

		int root = a;
		while (root != id[root]) {// looping till the id of the root is root itself
			root = id[root];
		}

		// path compression this can also be done in recursive

		while (a != root) {
			int temp = id[a];
			id[a] = root;
			a = temp;
		}
		return root;

	}
	
	boolean connected(int a, int b) {
		return find(a) == find(b);
	}

}
