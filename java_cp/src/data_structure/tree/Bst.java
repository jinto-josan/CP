package data_structure.tree;

public class Bst {
	
	class Node{
		int val;
		Node left;
		Node right;
		Node parent;
		Node(int val,Node parent){
			this.val = val;
			this.left = null;
			this.right = null;
			this.parent = parent;
		}
	}
	Node root;
	
	void add(int val) {
		
		if(root == null )
			root = new Node(val,null);
		else
			insert(val, root, null);
		
	}
	void insert(int val, Node n, Node p) {
		if(n == null) {
			if( val < p.val )
				p.left = new Node(val, p);
			else
				p.right = new Node(val, p);
			
		}
		else {
			if(val < n.val)
				insert(val, n.left,n);
			else if(val > n.val)
				insert(val,n.right,n);
		}
	}
	
	void transplant(Node x, Node y) {
		if(root == x) {
			root =y;
		}
		else if(x.parent.left == x) x.parent.left =y;
		else x.parent.right =y ;
		if(y != null)
			y.parent = x.parent;
	}
	
	Node inorderMax(Node n) {
		if(n==null) throw new IllegalArgumentException("Null node provided");
		Node p = n;
		while(n.left != null) {
			
			n = n.left;
			p = n;
		}
		return p;
	}
	
	void remove(int val, Node n) {
		if(n == null) throw new IllegalArgumentException("No such element in tree");
		if(n.val == val)
			if(n.left == null) {
				transplant(n, n.right);
			}
			else if(n.right == null) {
				transplant(n, n.left);
			}
			else {
				//finding a node which is inorder max
				Node iMax = inorderMax(n.right);
				//checking if the right node is not the imax
				if(iMax.parent != n) {
					transplant(iMax,iMax.right);
					iMax.right = n.right;
					n.right.parent = iMax;
				}
				transplant(n,iMax);
				iMax.left = n.left;
				n.left.parent = iMax;
				
			}
		else if(val < n.val ) {
			remove(val, n.left);
		}
		else {
			remove(val,n.right);
		}
	}
	
	void display(Node n, int space) {
		if(n!= null) {
			++space;
			display(n.right, space);
			for(int i=0;i<5*space;++i) {
				System.out.print(" ");
			}
			System.out.print(n.val);
			System.out.println();
			display(n.left, space);
			
		}
	}
	
	public static void main(String args[]) {
		Bst tree = new Bst();
		tree.add(200);
		tree.add(100);
		tree.add(300);
		tree.add(90);
		tree.add(320);
		tree.add(120);
		tree.add(250);
		//tree.display(tree.root,-1);
		tree.remove(200, tree.root);
		tree.remove(250, tree.root);
		tree.display(tree.root,-1);
	}
	

}
