package sorting;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class test {
	
	public static void main(String args[])throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the array size :- ");
		int i = Integer.parseInt(br.readLine());
		int arr[] = new int[i];
		for(; i>0; --i) {
			arr[i-1] = (int)(Math.random()*10000 + 0);
		}
		display(arr);
		int arrMS[]= arr.clone();
		int arrQS[] = arr.clone();
		//int arr1[] = {72  ,46 , 60,  91,  87,  59,  44,  47,  99,  8};
		
		System.out.println("Insertion Sort");
		long start = System.nanoTime();
		new insertion_sort(arr);
		//String algo1="insertion_sort";
		System.out.println("Time taken :- "+(System.nanoTime() -start));
		display(arr);
		//timeit(algo1,arr);
		
		System.out.println("Merge Sort");
		start = System.nanoTime();
		new merge_sort(arrMS);
		System.out.println("Time taken :- "+(System.nanoTime() -start));
		display(arrMS);
		
		System.out.println("Quick Sort");
		start = System.nanoTime();
		new quick_sort(arrQS);
		System.out.println("Time taken :- "+(System.nanoTime() -start));
		display(arrQS);
		
		int arrCS[] = arr.clone();
		System.out.println("Counting Sort");
		start = System.nanoTime();
		new counting_sort(arrCS);
		System.out.println("Time taken :- "+(System.nanoTime() -start));
		display(arrCS);
		
		int arrRS[] = arr.clone();
		System.out.println("Radix Sort");
		start = System.nanoTime();
		new counting_sort(arrRS);
		System.out.println("Time taken :- "+(System.nanoTime() -start));
		display(arrRS);
	}
	
	static void display(int arr[]) {
		for(int i=0;i<arr.length;++i) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}
	
	static void timeit(String cl, int arr[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Class c= Class.forName(cl);
		long start = System.currentTimeMillis();
		//class.getDeclaredConstructor(int [].class).newInstance(arr);
		c.newInstance();
		System.out.println("Time taken :- "+(System.currentTimeMillis() -start));

		
		
	}

}
