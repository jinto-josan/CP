package data_structure.stack_queue;

public class Min_queue {

	/*
	 * Here two min stacks are used s1,s2
	 * 
	 * @ pushing to stack 1 and popping from stack 2
	 * 
	 * @ Minimum is min of stack 1 and stack 2
	 * 
	 * @application :- min of all subarray of length m for an array
	 */
	Min_stack s1 = new Min_stack();// this one for pushing
	Min_stack s2 = new Min_stack();// this one for popping

	void push(int elem) {
		s1.push(elem);
	}
	int min(int a, int b) {return a>b ? b :a;}
	int getMin() {
		if (s1.isEmpty() && s2.isEmpty())
			throw new NullPointerException("Queue is empty");
		else if (s1.isEmpty() || s2.isEmpty()) {
			return s1.isEmpty() ? s2.getMin() : s1.getMin();
		} else {
			return min(s1.getMin(),s2.getMin());
		}
	}
	
	int pop() {
		
		if(s1.isEmpty() && s2.isEmpty()) {
			throw new NullPointerException("Queue is empty");
		}
		
		//transferring all elements  of s1 to s2 in min_stack form so that bottom of s1 comes to top of s2
		else if(s2.isEmpty()) {
			while(!s1.isEmpty()) {
				s2.push(s1.pop());
			}
		}
		return s2.pop();
	}

}
