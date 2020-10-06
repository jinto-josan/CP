package sorting;

public class counting_sort {
	
	public counting_sort(int arr[]) {
		// TODO Auto-generated constructor stub
		sort(arr);
	}
	
	
	
	void sort(int arr[]) {
		//[min,max]
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i: arr) {
			if(i>max)
				max = i;
			if(i<min)
				min =i;
		}
		int c[] = new int[max-min+1];
		int sorted[] = new int[arr.length];//as repitition can come
		for(int i:arr)
			++c[i-min];
		
		//this is not stable
//		for(int i=1;i<c.length;++i) {
//			c[i] =c[i-1];
//		}
//		for(int i=0; i<arr.length;++i)
//			sorted[--c[arr[i]-min]] =arr[i];
		
		//this is stable
		for(int i=0,k=0;i<c.length;++i) {
			while(c[i]-- > 0) {//reduce count in counting array using post increment
				sorted[k++] = i+min;
				
			}
		}
	}

}
