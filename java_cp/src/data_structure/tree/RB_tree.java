package data_structure.tree;

public class RB_tree {

	int red = 1;
	int black = 0;

	Node nil = new Node(null, null, null, 0, black);
	Node root = nil;

	private class Node {
		Node left;
		Node right;
		Node parent;
		int data;
		int color;

		Node(Node left, Node right, Node parent, int data, int color) {
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.data = data;
			this.color = color;
		}
	}
	
	void show() {
		traverse(root,-1);
	}
	void traverse(Node n, int i) {
		if(n!= nil) {
			++i;
			traverse(n.right,i);
			for(int j=0;j<5*i;++j) {
				System.out.print(" ");
				
			}
			System.out.print(n.data+""+(n.color == red ?'R':'B'));
			System.out.println();
			traverse(n.left,i);
			
		}
	}

	void add(int data) {
		if (root == nil) {
			root = new Node(nil, nil, nil, data, black);
		} else {
			Node temp = root;
			Node p = root.parent;
			while (temp != nil) {
				if (data < temp.data) {
					p = temp;
					temp = temp.left;					
				} else {
					p = temp;
					temp = temp.right;
					
				}
			}
			if (data < p.data) {
				p.left = new Node(nil, nil, p, data, red);
				rbCorrect(p.left);
			} else {
				p.right = new Node(nil, nil, p, data, red);
				rbCorrect(p.right);
			}
			
		}
	}
	void lRotate(Node n) {
		if(n.right != nil ) {
			Node rN = n.right;
			if(n == root) {
				root = rN;
			}
			else {
				if(n.parent.left == n) {
					n.parent.left = rN;
				}
				else {
					n.parent.right = rN;
				}
				
			}
			rN.parent = n.parent;
			n.parent = rN;
			
			if(rN.left != nil) {
				rN.left.parent = n;
			}
			n.right = rN.left;
			rN.left =n;
			
		}
	}
	void rRotate(Node n) {
	if(n.left != nil ) {
		Node lN = n.left;
		if(n == root) {
			root = lN;
		}
		else {
			if(n.parent.left == n) {
				n.parent.left = lN;
			}
			else {
				n.parent.right = lN;
			}
			
		}
		lN.parent = n.parent;
		n.parent = lN;
		
		if(lN.right != nil) {
			lN.right.parent = n;
		}
		n.left = lN.right;
		lN.right =n;
		
	}
}
	void rbCorrect(Node n) {
		while(n.parent.color != black) {
			Node p = n.parent;
			Node g = n.parent.parent;
			if(g.left == p) {
				Node u = g.right;
				if(u.color == red) {
					g.color = red;
					p.color = black;
					u.color  = black;
					n = g;
				}
				else {
					if(p.right == n) {
						lRotate(p);
						p = n;
					}
					p.color = black;
					g.color = red;
					rRotate(g);
					
				}
			}
			else if(g.right == p) {
				Node u = g.left;
				if(u.color == red) {
					g.color = red;
					p.color = black;
					u.color  = black;
					n = g;
				}
				else {
					if(p.left == n) {
						p =n;
						rRotate(p);
					}
					p.color = black;
					g.color = red;
					lRotate(g);
					
				}
			}
			
		}
		root.color = black;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RB_tree tree = new RB_tree();
		tree.add(300);
		tree.add(100);
		tree.add(200);
		tree.add(400);
		tree.add(500);
		tree.add(600);
		tree.add(700);
		tree.add(800);
		tree.add(900);
		tree.add(50);
		tree.add(120);
		tree.add(25);
		tree.show();

	}

}