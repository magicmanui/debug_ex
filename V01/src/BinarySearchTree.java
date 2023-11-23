
public class BinarySearchTree {
	// ADDITIONAL IMPORTS ARE NOT ALLOWED

	public static void main(String[] args) {
		// Uncomment the following two lines if you want to read from a file
		In.open("public/large.in");
		Out.compareTo("public/large.out");

		BinaryTree T = new BinaryTree();

		int m = In.readInt(); // number of operations
		for (int i = 0; i < m; i++) {
			char operation = In.readChar();
			if (operation == 'I') {
				int key = In.readInt();
				int value = In.readInt();
				T.insert(key, value);
			} else {
				int x = In.readInt();
				Out.println(T.query(x));
			}
		}

		// Uncomment the following line if you want to read from a file
		In.close();
	}
}

class TreeNode {
	public int key;
	public int value;
	public TreeNode parent;
	public TreeNode left;
	public int leftMax;
	public TreeNode right;
	public int rightMax;

	TreeNode(int key, int value) {
		this.key = key;
		this.value = value;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
}

class BinaryTree {
	TreeNode root;

	BinaryTree() {
		this.root = null;
	}

	// Inserts a node with the given key and value in the binary tree rooted at
	// BinaryTree.root.
	public void insert(int key, int value) {
		if (root == null) {
			root = new TreeNode(key, value);
		} else {
			insert(root, key, value);
		}
	}

	// Inserts a node with the given key and value in the binary tree rooted at
	// node.
	public void insert(TreeNode node, int key, int value) {
		if (key < node.key) { // insert in left subtree
			if (node.left != null) {
				insert(node.left, key, value);
				node.leftMax = Math.max(node.leftMax, value);
			} else {
				node.left = new TreeNode(key, value);
				node.left.parent = node;
				node.leftMax = value;
			}
		} else { // insert in right subtree
			if (node.right != null) {
				insert(node.right, key, value);
				node.rightMax = Math.max(node.rightMax, value);
			} else {
				node.rightMax = value;
				node.right = new TreeNode(key, value);
				node.right.parent = node;
			}
		}
	}

	// Returns the maximum value associated with a key that is less than or equal
	// to x in the binary tree rooted at BinaryTree.root.
	public int query(int x) {
		// initialize the recursion
		return query(x, this.root);
	}

	public int query(int x, TreeNode current) {
		// initialize return value
		int max;
		// base case: we reach a leaf
		if (current == null)
			return 0;
		// the current node's key is smaller than or equal to x
		if (current.key <= x) {
			// check the left subtree (if it exists) and the current node for their highest
			// value
			if (current.left != null) {
				max = Math.max(current.value, current.leftMax);
			} else
				max = current.value;
			// find the highest value in the right subtree
			max = Math.max(max, query(x, current.right));
		}
		// the current node is too large.
		else {
			// continue looking in the left subtree
			max = query(x, current.left);
		}

		return max;
	}

}
