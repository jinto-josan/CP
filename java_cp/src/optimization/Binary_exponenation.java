package optimization;

public class Binary_exponenation {
	
	//So only log n multiplications required
	
	static long pow(int a, int b) {
		
		long res =1;
		while(b > 0) {
			if((b & 1) == 1)
				res*= a;			
			a*= a;			
			b = b>>1;
		}
		return res;
	}
	
	public static void main(String arg[]) {
		System.out.println( pow(3,3));
	}

}
