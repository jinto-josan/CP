package data_structure.tree;

public class Fenwick_tree {

	/*
	 * Both update and query takes O(lg N) this is useful for range queries
	 * 
	 * @application point update and range query range update and point query range
	 * update and range query
	 * 
	 * 
	 */

	//@important 1 based fenwick tree for sum query on a range[1...N]
	
	int fenwick_array[];
	int N; //length of array
	public Fenwick_tree(int arr[]) {
		this.N = arr.length;
		this.fenwick_array = arr.clone();
		
		for(int i=1;i<N+1;++i) {
			int j = i+ LSB(i);
			if(j < N+1) {
				fenwick_array[j] += fenwick_array[i];
			}
		}
	
	
	}
	
	int LSB(int i) {
		return (i & (-i));
//		return Integer.lowestOneBit(i) //also gives LSB
	}
	
	
	
	
	
	
	// The approach is similar to prefix sum
	int sum(int r) {//to get sum from [0,r]
		//if g(i) = i -1 then it is prefix sum which gives sum in constant time but update slow
		//g(i) = i-(i&(-i))
		// if r =7 g(7) = 6
		// g(6) =4
		// g(4) =0
		int res =0;
		while(r > 0) {//here tcascading towards 1
			//res+= t[r]; t[r] is the fenwick array
			//r=g(r); g(r) is the function for 1 indexed array
			res+= fenwick_array[r];
			//r &= ~LSB(r);
			r= r-LSB(r); // for position of lsb
		}
		return res;
	}
	
	int rangeSum(int i, int j) {
		//[i,j]
		return sum(j) - sum(i-1);
	}
	
	
	void update(int ix, int delta) {//here towards upperbound j
		//delta change in val
		while(ix<fenwick_array.length) {
			fenwick_array[ix]+=delta;
			ix += LSB(ix);
		}
	}

}
