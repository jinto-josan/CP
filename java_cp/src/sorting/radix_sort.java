package sorting;

public class radix_sort {
	
	
	public radix_sort(int arr[]) {
		// TODO Auto-generated constructor stub
		//O(w*n)//depends only on word length 
		sort(arr);
	}

	void sort(int arr[]) {

		int max = 0;
		for (int i : arr)
			max = Math.max(i, max);

		int maxNumberOfDigits = (int) Math.log10(max);
		
		//Bucket Sort can be used if stable sorting required
		//queue can also be used place value in buckets then take it back then for next place and so on
		//ArrayDeque<Integer> buckets[] = new ArrayDeque<Integer>[10]; // as radix 10 will have 10 digits

		
		//using counting sort instable version
		int placevalue = 1;
		while (maxNumberOfDigits-- > 0) {
			count_sort(arr, placevalue);
			placevalue *= 10;
		}
	}

	void count_sort(int arr[], int place) {
		int range =10; //as there are 10 digits [0-9]
		int buckets[] = new int[10];
		int sorted[] = new int[arr.length];
		
		for(int i:arr)
			++buckets[(i/place) % range];
		for(int i=1;i<10;++i)
			buckets[i] +=buckets[i-1];
		
		for(int i=0;i<arr.length;++i) {
			sorted[--buckets[(arr[i]/place) % range]] = arr[i];
		}
		System.arraycopy(sorted, 0, arr, 0, arr.length);
		
		

	}

}
