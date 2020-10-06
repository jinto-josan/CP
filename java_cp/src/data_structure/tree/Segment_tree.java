package data_structure.tree;

public class Segment_tree {

	/*
	 * This is not actually a tree but the architecture is so right node =
	 * func(middle+1,r) left node = func(l,middle)
	 * 
	 * In simple implementation it takes 4N memory but can be reduced to 2*N -1
	 * using eulerian tour
	 * 
	 */

	int tree[];
	int mainArray[];

	public Segment_tree(int arr[]) {
		mainArray = arr.clone();
		int N = arr.length;
		this.tree = new int[4 * N];
		build_tree(0, 0, N - 1);
	}

	void build_tree(int ix, int l, int r) {
		// if tree is constructed as 0 indexed
		if (l == r) {
			tree[ix] = mainArray[l];
		} else {
			int middle = (l + r) / 2;
			build_tree((ix * 2) + 1, l, middle);
			build_tree((ix * 2) + 2, middle + 1, r);
			tree[ix] = tree[(2 * ix) + 1] + tree[(2 * ix) + 2];// if sum range query
		}
	}

	int query(int ix, int tl, int tr, int l, int r) {
		// l and r is the actual query and tl and tr is the array range
		if (l > r)
			return 0;
		if (tl == l && tr == r)
			return tree[ix];
		else {
			int tm = (tl + tr) / 2;
			return query((ix * 2) + 1, tl, tm, l, Math.min(tm, r))
					+ query((ix * 2) + 2, tm + 1, tr, Math.max(tm + 1, l), r);
		}
	}

	void update(int ix, int tl, int tr, int pos, int new_val) {
		if (tl == tr)
			tree[ix] = new_val;

		else {
			int tm = (tl + tr) / 2;
			if (pos < tm) {
				update((ix * 2) + 1, tl, tm, pos, new_val);
			}
			if (pos > tm) {
				update((ix * 2) + 2, tm + 1, tr, pos, new_val);
			}
			tree[ix] = tree[(ix * 2) + 1] + tree[(ix * 2) + 2];
		}
	}
	
	
	//example of segment query
	//this is lazy propogation
	// it updates the value of segments only. If add 1 to all elements means add 1 to root. As root represents the entire segment.
	// and it doesnot imply propogating this affect downwards
	void updateRange(int ix, int tl, int tr, int l, int r, int add) {
		if (l > r)
			return;
		if (tl == l && tr == r)
			tree[ix]+=add;
		else {
			int tm = (tl + tr) / 2;
			updateRange((ix * 2) + 1, tl, tm, l,Math.min(r, tm), add);
			updateRange((ix * 2) + 2, tm + 1, tr, Math.max(l, tm+1), r, add);
		}
	}
//	//this one while getting add the value downwards till that position.
//	int get(int v, int tl, int tr, int pos) {
//	    if (tl == tr)
//	        return tree[v];
//	    int tm = (tl + tr) / 2;
//	    if (pos <= tm)
//	        return tree[v] + get((v*2)+1, tl, tm, pos);
//	    else
//	        return tree[v] + get((v*2)+2, tm+1, tr, pos);
//	}
	
	
	public static void main(String args[]) {
		int[] values = {1, 2, 3, 2};
	    Segment_tree st = new Segment_tree(values);

	    int l = 0, r = 3;
	    System.out.printf("The sum between indeces [%d, %d] is: %d\n", l, r, st.query(0, 0, 3, l, r));
	}

}
