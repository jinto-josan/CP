package sorting;

public class insertion_sort {

	public insertion_sort(int arr[]) {
		// TODO Auto-generated constructor stub

		sort(arr);
	}

	void sort(int arr[]) {
		int len = arr.length;
		// invariant is that the elements left of the key are sorted in ascending order
		for (int i = 0; i < len; ++i) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0) {
				if (arr[j] > key)
					arr[j + 1] = arr[j];
				else {
					break;
				}
				--j;
			}
			arr[j + 1] = key;
		}
	}
}
