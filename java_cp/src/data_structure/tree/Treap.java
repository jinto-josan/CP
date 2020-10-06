package data_structure.tree;

public class Treap {

	/*
	 * @ binary-search tree + heap
	 * 
	 * @ Max heap - priority of parent is greatest
	 * 
	 * @ Min heap - priority of child is greatest
	 * 
	 * @ Operations Insert, Split, Meld, erase, search
	 * 
	 */

	// Max heap
	class Node {
		Node left, right, parent;
		int priority, val;

		Node(Node parent, int val, int priority) {
			this.left = null;
			this.right = null;
			this.priority = priority;
			this.val = val;
			this.parent = parent;

		}

	}

	static Node root;

	void leftR(Node p) {

		Node c = p.right;
		if (root == p) {
			root = c;
		} else {
			if (p.parent.right == p)
				p.parent.right = c;
			else
				p.parent.left = c;
		}
		c.parent = p.parent;
		p.parent = c;

		p.right = c.left;
		if (c.left != null)
			c.left.parent = p;

		c.left = p;

	}

	void rightR(Node p) {

		Node c = p.left;
		if (root == p) {
			root = c;
		} else {
			if (p.parent.right == p)
				p.parent.right = c;
			else
				p.parent.left = c;
		}
		c.parent = p.parent;
		p.parent = c;

		p.left = c.right;
		if (c.right != null)
			c.right.parent = p;

		c.right = p;

	}

	void insert(int val, int priority) {
		// insert as bst then rotate as necessary till priority is ok
		if (root == null) {
			root = new Node(null, val, priority);
		} else {
			binaryInsert(root, null, val, priority);
		}

	}

	void binaryInsert(Node n, Node p, int val, int priority) {
		if (n == null) {

			Node temp = new Node(p, val, priority);

			if (val < p.val) {
				p.left = temp;
			} else {
				p.right = temp;
			}
			priorityCorrect(temp);

		} else if (val < n.val) {
			binaryInsert(n.left, n, val, priority);

		} else if (val > n.val) {
			binaryInsert(n.right, n, val, priority);

		} else {
			System.out.println("This value already exists");
		}

	}

	void priorityCorrect(Node n) {
		// priority correct based on max heap
		Node p = n.parent;
		while (p.priority < n.priority) {
			if (p.right == n) {
				leftR(p);
			} else {
				rightR(p);
			}
			if (n == root)
				break;
			p = n.parent;
		}
	}

	static Node rootr, rootl;

	void split(int val) {
		// this returns tree into two such that left is less than val and right is
		// greater
		// than val
		insert(val, Integer.MAX_VALUE);
		rootr = root.right;
		rootr.parent = null;
		rootl = root.left;
		rootl.parent = null;
	}

	Node meld(Node rl, Node rr) {
		// join two trees having neither priority same and ranges dont overlap and is
		// not as merge.
		// as ranges dont overlap so every element in one subtree will be less than
		// other
		// root is the highest priroity root among both trees
		// and the right child of the highest priority root becomes inorder of other
		// tree which is in turn
		// right child of highest priority tree

		// @ Assuming rl has everything less than rr
		if (rl == null)
			return rr;
		if (rr == null) {
			return rl;
		}
		if (rl.priority > rr.priority) {
			// rl higher priority
			rr.parent = rl;
			Node u = rr;
			while (u.left != null) {
				u = u.left;
			}
			rl.right.parent = u;
			u.left = rl.right; // as every element at right of r1 will be lesser than least element in r2
			rl.right = rr;
			return rl;
		} else {
			// rr higher priority
			Node u = rr;
			while (u.left != null) {
				u = u.left;
			}
			rl.parent = u;
			u.left = rl; // as every element at right of r1 will be lesser than least element in r2
			return rr;
		}

	}

	void delete(Node r, int val) {
		// remove that node then use meld at that point
		// Assumping the value exist in the treap
		Node temp = r;
		while (temp.val != val) {
			if (val < temp.val)
				temp = temp.left;
			else if (val > temp.val)
				temp = temp.right;
			if (temp == null)
				System.out.printf("There is no %d in this tree\n", val);
		}
		
		//if temp is a leaf node
		if(temp.left == null && temp.right == null) {
			if(temp.parent.right == temp)
				temp.parent.right = null;
			else
				temp.parent.left = null;
			temp.parent = null;
			return;
		}
		
		if(temp.left != null)
			temp.left.parent = null;
		if(temp.right!= null)
			temp.right.parent = null;
		
		Node n = meld(temp.left, temp.right);
		n.parent = temp.parent;
		if (temp == root) {
			root = n;
			return;
		}
		if (temp.parent.left == temp)
			temp.parent.left = n;
		else
			temp.parent.right = n;
	}

	void show() {
		traverse(root, -1);
	}

	void traverse(Node n, int i) {
		if (n != null) {
			++i;
			traverse(n.right, i);
			for (int j = 0; j < 5 * i; ++j) {
				System.out.print(" ");

			}
			System.out.print(n.val + " " + n.priority);
			System.out.println();
			traverse(n.left, i);

		}
	}

	public static void main(String args[]) {
		Treap tr = new Treap();
		tr.insert(10, 10);
		tr.insert(15, 4);
		tr.insert(13, 2);
		tr.insert(16, 1);
		tr.insert(14, 11);
		tr.insert(9, 3);
		//tr.show();
		tr.delete(root, 14);
//		tr.split(11);
//		tr.traverse(root1, -1);
//		System.out.println();
//		tr.traverse(root2, -1);
//		tr.meld(root2,root1);
//		tr.traverse(root1, -1);
		tr.show();

	}

}
