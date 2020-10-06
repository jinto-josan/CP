package sorting;

public class quick_sort {

	int length;
	int arr[];

	public quick_sort(int arr[]) {
		this.length = arr.length;
		this.arr = arr;
		// TODO Auto-generated constructor stub
		sort(0, length - 1);
	}

	void sort(int l, int r) {

		if (l >= r)
			return;
		int splitIx = partition(l, r);
		sort(l, splitIx - 1);
		sort(splitIx + 1, r);

	}

	// randomized quick sort
	int partition(int l, int r) {// taking inclusive range

		int rIx = (int) Math.random() * (r - l) + l;
		swap(r, rIx);

		int last = arr[r];
		int splitter = l - 1;
		for (int i = l; i < r; ++i) {
			if (arr[i] < last) {
				++splitter;
				swap(i, splitter);

			}
		}
		++splitter;
		arr[r] = arr[splitter];
		arr[splitter] = last;
		return splitter;

	}

	void swap(int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

}
