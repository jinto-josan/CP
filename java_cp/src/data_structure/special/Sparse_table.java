package data_structure.special;

public class Sparse_table {
	/*
	 * @application used for range queries in static  arrays
	 * @ intiution :- [5,7] = [5,5+2^3] union [13,13+2^2] union[17,17+2^0]
	 * @ In sparse table if the query function is associative then query can be done in logarathmic time
	 * @ If the query function is overlap friendly(f(f(a,b),f(b,c) = f(a,(b,c)) then query can be done in constant time
	 *  
	 * Procedure
	 * 		@ Make a k+1 x N matrix where k is floor(log2 N). 
	 * 			@ In this each row specifies the range of length 2^0 ,2^1 ,2^2, 
	 * 			@ f([j,j+2^i)) = [i][j] 
	 * 			@ The current cell represents the range [j,j+2^i) which splits evenly into [j,j+2^(i-1)] and [j+2^(i-1),j+2^i]
	 * 		@ Each cell[i,j] is filled as f([i-1,j), [i-1,j+2^(i-1)) ) where i goes from 0 to (2^j)-1
	 * 		@ For overlapping friendly function :- To find function value in [l,r] find f([p,l],[p,r-k+1]) where k is p =floor(log2 r-l+1)) and k =2^p 
	 *  	@ For associative function :- We start from power 2^k and only process that power if 2^k <= r-l+1 
	 *  									(which is for range [l,l+2^k+1] and is in cell [k,l])
	 *  									 and then l changes to l+2^k+1 and so on continue till k =0
	 *  
	 */
	
	int dp[][], N, K;
	Sparse_table(int arr[]){
		N = arr.length;
		K = (int)Math.floor(Math.log(arr.length)/Math.log(2));
		dp = new int[K+1][N];
		for(int i=0;i<N;++i) {
			dp[0][i] = arr[i];//initialize first row of dp with values of original array
		}
		makeTable();
	}
	void makeTable() {
		
		for(int p=1; p<=K ; ++p) {
			//so that half partitioned can be avoided
			for(int j=0; j+(1<<p) <= N; ++j ) {//1<<i is same as 2^i
				int left = dp[p-1][j];
				int right;
				right = dp[p-1][j+(1<<(p-1))];
//				dp[p][j] = Math.min( left,right );
				dp[p][j] = left * right ;
			}
		}		
	}
	
	int OFretrieve(int l, int r) {
		//for overlapping function so queries in O(1)
		int len = r-l+1;
		int p = (int)Math.floor(Math.log(len)/Math.log(2));
		int k = (int) Math.pow(2, p);
		return Math.min(dp[p][l],dp[p][r-k+1]);
		
	}
	
	int AFretrieve(int l, int r) {
		//for associative function so queries in O(lg N)
		//f[l,r] = f(f[l,2^N) U f[l+2^N,2^N-1)....f[..,2^0))
		int i =K;
		int pdct =1;
		while(i>-1) {
			int len = r-l+1;
			if((1<<i) <= len) {
				int val = dp[i][l]; // sum of [l,l+2^i)
				pdct*= val;
				l = l + (1<<i);
			}
			--i;
		}
		return pdct;
		
	}
	
	public static void main(String args[]) {
//		int arr[] = {4,2,3,7,1,5,3,3,9,6,7,-1,4};
//		Sparse_table tble = new Sparse_table(arr);
//		System.out.println(tble.OFretrieve(2, 7));
		int arr[] = {1,2,-3,2,4,-1,5};
		Sparse_table tble = new Sparse_table(arr);
		System.out.println(tble.AFretrieve(1, 5));
	}
	
	

}
