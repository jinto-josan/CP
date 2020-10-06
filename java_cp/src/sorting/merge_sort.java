package sorting;

public class merge_sort {
	
	merge_sort(int arr[]){
		sort(arr);
	}

	void sort(int arr[]) {

		partition(0, arr.length - 1, arr);

	}

	void merge(int l, int m, int r, int arr[]) {

		int neleml = m - l + 1; // as inclusive of m and l so m-l+1
		int nelemr = r - m;// as inclusive of m+1 and r so r-m-1 +1 = r-m
		int larr[] = new int[neleml + 1];
		int rarr[] = new int[nelemr + 1];
		for (int i = l; i <= m; i++)
			larr[i - l] = arr[i];
		for (int j = m + 1; j <= r; ++j)
			rarr[j - m - 1] = arr[j];
		larr[larr.length - 1] = Integer.MAX_VALUE;
		rarr[rarr.length - 1] = Integer.MAX_VALUE;
		int lix = 0;
		int rix = 0;
		for (int i = l; i <= r; ++i) {
			if (larr[lix] < rarr[rix]) {
				arr[i] = larr[lix];
				++lix;
			} else {
				arr[i] = rarr[rix];
				++rix;
			}
		}

	}

	void partition(int l, int r, int arr[]) {
		if (l >= r)
			return;
		int m = (r - l) / 2;
		partition(l, l+m, arr);// [l,m]
		partition(m + 1+l, r, arr);// [m+1,r]
		merge(l, l+m, r, arr);

	}

}
