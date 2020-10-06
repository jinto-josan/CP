package optimization;

public class SQRT_decomposition {
	
	/*
	 * Doing queries in a subarray takes O( sqrt(n) )
	 * Value that goes beyond block but not till end can be added
	 * Here the block size and noOfblocks depend on operation
	 *
	 *@application
	 *		Min/Max, count elements which satisy a property
	 *		zero elements,etc for a subarray
	 *		Update array elements on intervals
	 */
	int blocks[];
	int blockSize; //Assumption number of block and block size same
	int noOfBlocks;//number of blocks
	int mainArr[];
	SQRT_decomposition(int arr[]){
		// 0 index based
		mainArr = arr.clone();
		int N = arr.length;
		blockSize= (int)Math.ceil(Math.sqrt(N));//Number of blocks optimal
		noOfBlocks = blockSize; //assumption for sum operation
		blocks = new int[noOfBlocks];
		for(int i=0; i< N; ++i) {
			blocks[i/blockSize]+= arr[i];
		}
		
	}
	void query(int l, int r) {
		// speed can be further optimized by replacing division to arthimetic operation c_l, c_r
		int sum =0;
		for(int i=l; i<=r;) {
			if(i%blockSize ==0 && i+blockSize-1 <= r) {//here -1 as it is index based
				sum+=blocks[i/blockSize];
				i+= blockSize;
			}
			else {
				//summing tailing elements
				sum+=mainArr[i];
				++i;
			}
		}
	}
	
	void update(int i, int val) {
		// if this is sum operation
		blocks[i/blockSize] += val - mainArr[i]; 
		mainArr[i] = val;
		// but if it min max then it will take O(sqrt N) time
		// as we have to go through entire block 
	}
	
	
	//MO's algo is general algo for offline query of mode like operation  using a single ds	
//	void mo(int l, int r) {
//		// It is an algorithm which can be used for mode(count of distinct and so on)
//		// It is an offline algo and takes (Q*sqrtN + N*sqrtN)
//		// Sort queries based on l such that l is considered as l/sqrt(N) and  if tie use r
//		// Then use a datastructure in which add and remove is done as per need
//		Queries query[];
//		query.sort();// so that queries remain in a block of size
//		int cl =0 ,cr =-1;
//		for(Queries q: query) {
//			if(q[l]<cl) {
//				cl--;
//				add[cl];
//			}
//			else if(q[r]>cr) {
//				cr++;
//				add[cr];
//				
//			}
//			else if(q[l]>cl){
//				remove(cl);
//				cl++;
//				
//			}
//			else if(q[r] < cr) {
//				remove(cr);
//				cr--;
//			}
//		}
//		
//	}

}
