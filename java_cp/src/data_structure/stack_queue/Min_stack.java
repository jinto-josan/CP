package data_structure.stack_queue;

public class Min_stack {
	/*
	 * A special kind of stack 
	 * 	@ which will give minimum of all elements in the stack in O(1)
	 * 	@ While keeping the same asymptotic behaviour for rest of elements
	 * 
	 */
	Elem top;// a pointer to top element	
	class Elem{
		int val;//stores the actual value
		int min;//stores the min value till that elem in stack
		Elem next;
		Elem(Elem next, int val, int min){
			this.next = next;
			this.val = val;
			this.min = min;
			
		}
		
	}
	
	int min(int a, int b) {
		return a<b?a:b;
	}
	
	void push(int elem) {
		if(top == null) {
			top = new Elem(null,elem,elem);
		}
		else {
			top = new Elem(top,elem,min(elem,top.min));
		}
		
	}
	int pop() {
		if(!isEmpty()) {
			int val = top.val;
			top = top.next;
			return val;
		}
		else throw new NullPointerException("Stack is empty");
		
	}
	
	int getMin() {
		if(!isEmpty()) {
			return top.min;
		}
		else throw new NullPointerException("Stack is empty");
		
	}
	
	boolean isEmpty() {
		return top == null;
	}

}
