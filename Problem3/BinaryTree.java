package comp2402a3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.HashMap;

public class BinaryTree<Node extends BinaryTree.BTNode<Node>> {

	public static class BTNode<Node extends BTNode<Node>> {
		public Node left;
		public Node right;
		public Node parent;
	}

	/**
	 * An extension of BTNode that you can actually instantiate.
	 */
	protected static class EndNode extends BTNode<EndNode> {
			public EndNode() {
				this.parent = this.left = this.right = null;
			}
	}

	/**
	 * Used to make a mini-factory
	 */
	protected Node sampleNode;

	/**
	 * The root of this tree
	 */
	protected Node r;

	/**
	 * This tree's "null" node
	 */
	protected Node nil;

	/**
	 * Create a new instance of this class
	 * @param sampleNode - a sample of a node that can be used
	 * to create a new node in newNode()
	 * @param nil - a node that will be used in place of null
	 */
	public BinaryTree(Node sampleNode, Node nil) {
		this.sampleNode = sampleNode;
		this.nil = nil;
		r = nil;
	}

	/**
	 * Create a new instance of this class
	 * @param sampleNode - a sample of a node that can be used
	 * to create a new node in newNode()
	 */
	public BinaryTree(Node sampleNode) {
		this.sampleNode = sampleNode;
	}

	/**
	 * Allocate a new node for use in this tree
	 * @return newly created node
	 */
	@SuppressWarnings({"unchecked"})
	protected Node newNode() {
		try {
			Node u = (Node)sampleNode.getClass().getDeclaredConstructor().newInstance();
			u.parent = u.left = u.right = nil;
			return u;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Construct a random binary tree
	 * @return an n-node BinaryTree that has the shape of a random
	 * binary search tree.
	 */
	public static BinaryTree<EndNode> randomBST(int n) {
		Random rand = new Random();
		EndNode sample = new EndNode();
		BinaryTree<EndNode> t = new BinaryTree<EndNode>(sample);
		t.r = randomBSTHelper(n, rand);
		return t;
	}

	protected static EndNode randomBSTHelper(int n, Random rand) {
		if (n == 0) {
			return null;
		}
		EndNode r = new EndNode();
		int ml = rand.nextInt(n);
		int mr = n - ml - 1;
		if (ml > 0) {
			r.left = randomBSTHelper(ml, rand);
			r.left.parent = r;
		}
		if (mr > 0) {
			r.right = randomBSTHelper(mr, rand);
			r.right.parent = r;
		}
		return r;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		toStringHelper(sb, r);
		return sb.toString();
	}

	protected void toStringHelper(StringBuilder sb, Node u) {
		if (u == null) {
			return;
		}
		sb.append('(');
		toStringHelper(sb, u.left);
		toStringHelper(sb, u.right);
		sb.append(')');
	}

	/**
	 * Tree empty or not
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return r == nil;
	}

	/**
	 * Make this tree into the empty tree
	 */
	public void clear() {
		r = nil;
	}

	/**
	 * Compute the depth (distance to the root) of u
	 * @param u
	 * @return the distanct between u and the root, r
	 */
	public int depth(Node u) {
		int d = 0;
		while (u != r) {
			u = u.parent;
			d++;
		}
		return d;
	}

	/**
	 * Demonstration of a recursive traversal
	 * @param u
	 */
	public void traverse(Node u) {
		if (u == nil) return;
		traverse(u.left);
		traverse(u.right);
	}

	/**
	 * Demonstration of a non-recursive traversal
	 */
	public void traverse2() {
		Node u = r, prev = nil, next;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.left != nil) {
					next = u.left;
				} else if (u.right != nil) {
					next = u.right;
				}	else {
					
					next = u.parent;
				}
			} else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;
				} else {
					next = u.parent;
				}
			}else {
				next = u.parent;
			}
			prev = u;
			u = next;
		}
	}

	/**
	 * Demonstration of a breadth-first traversal
	 */
	public void bfTraverse() {
		Queue<Node> q = new LinkedList<Node>();
		if (r != nil) q.add(r);
		while (!q.isEmpty()) {
			Node u = q.remove();
			if (u.left != nil) q.add(u.left);
			if (u.right != nil) q.add(u.right);
		}
	}

	/**
	 * Compute the size (number of nodes) of this tree
	 * @warning uses recursion so could cause a stack overflow
	 * @return the number of nodes in this tree
	 */
	public int size() {
		return size(r);
	}

	/**
	 * @return the size of the subtree rooted at u
	 */
	protected int size(Node u) {
		if (u == nil) return 0;
		return 1 + size(u.left) + size(u.right);
	}

	/**
	 * Compute the maximum depth of any node in this tree
	 * @return the maximum depth of any node in this tree
	 */
	public int height() {
		return height(r);
	}

	/**
	 * @return the height of the subtree rooted at u
	 */
	protected int height(Node u) {
		if (u == nil) return -1;
		return 1 + Math.max(height(u.left), height(u.right));
	}

	public int leafAndOnlyLeaf() {
		Node u = r, prev = nil, next;
		int counter = 0;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.left != nil) {
					next = u.left;
				} else if (u.right != nil) {
					next = u.right;
				}	else {
					next = u.parent;
					counter++;
				}
			} else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;
				} else {
					next = u.parent;
				}
			} else {
				next = u.parent;
			}
			prev = u;
			u = next;
		}
		return counter;
	}
	public int leafAndOnlyLeaf2() {
        // TODO: Your code goes here, must avoid recursion
        if(r == nil)return 0;
        return leafAndOnlyLeafHelper(r);
    }
	protected int leafAndOnlyLeafHelper(Node u) {
		if(u == nil)return 0;
		if(u.left == nil && u.right == nil)return 1;
		return leafAndOnlyLeafHelper(u.left)+ leafAndOnlyLeafHelper(u.right);
	}

	public int dawnOfSpring() {
		if(r == nil){
			return -1;
		}
		Node u = r, prev = nil, next;
		
		int deep = 0;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.left != nil) {
					next = u.left;
				} else if (u.right != nil) {
					next = u.right;
				}else{
					next = u.parent;
					if(deep == 0){
						deep = depth(u);
					}else if(deep > depth(u)){
						deep = depth(u);
					}
				}
			}else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;
				} else {
					next = u.parent;
				}
			} else {
				next = u.parent;
			}
			prev = u;
			u = next;
		}		
		return deep;
	}
	public int dawnOfSpring2() {
        // TODO: Your code goes here, must avoid recursion
        if(r == nil)return -1;
        if(r.left == nil && r.right == nil)return 0;
        return dawnOfSpringHelper(r, 0);
    }
	protected int dawnOfSpringHelper(Node u, int d) {
		if(u.left == nil && u.right == nil)
			return d;
		else if(u.left == nil)
			return dawnOfSpringHelper(u.right, d+1);
		else if(u.right == nil)
			return dawnOfSpringHelper(u.left, d+1);
		else
			return Math.min(dawnOfSpringHelper(u.left, d+1), dawnOfSpringHelper(u.right, d+1));
	}

	public int monkeyLand() {
		Node u = r, prev = nil, next;
		HashMap<Integer, Integer> deep = new HashMap<>();
		int deeper = 0;
		while (u != nil) {
			
			if (prev == u.parent){
				if (u.left != nil) {
					next = u.left;
					deeper++;

					if(deep.containsKey(deeper) == true){
						deep.put(deeper, deep.get(deeper)+1);
					}else{
						deep.put(deeper, 1);
					}
				}else if (u.right != nil) {
					next = u.right;
					deeper++;
					if(deep.containsKey(deeper) == true){
						deep.put(deeper, deep.get(deeper)+1);
					}else{
						deep.put(deeper, 1);
					}
				}else{
					deeper--;
					next = u.parent;
				}
			}else if (prev == u.left) {
				
				if (u.right != nil) {
					next = u.right;
					deeper++;
					if(deep.containsKey(deeper) == true){

						deep.put(deeper, deep.get(deeper)+1);
					}else{
						deep.put(deeper, 1);
					}
				} else {
					deeper--;
					next = u.parent;
					
				}
			}else{
				deeper--;
				next = u.parent;
			}
			
			prev = u;
			u = next;
		}
		int greatest = 0;
		for(int value: deep.values()){			
			if(greatest < value){
				greatest = value;
			}
		}
		return greatest;
	}
	protected int monkeyLand2(){
		int h = height(), max_width = 0;
        for(int i=0; i<h; i++){
            int width = monkeyLandHelper(r, 0, i);
            if(width > max_width)
                max_width = width;
        }
        return max_width;
	}
	protected int monkeyLandHelper(Node u, int d, int D) {
		if(u == nil) return 0;
		if(d == D)return 1;
		return monkeyLandHelper(u.left, d+1, D) + monkeyLandHelper(u.right, d+1, D);
	}
	public String bracketSequence() {

		StringBuilder sb = new StringBuilder();
		Node u = r, prev = nil, next;
		if(r == null){
			sb.append(".");
			return sb.toString();
		}
		while (u != nil) {

			if (prev == u.parent){
				sb.append("(");
				if (u.left != nil) {
					next = u.left;
					
				}else if (u.right != nil) {
					sb.append(".");
					next = u.right;
					
				}else{

					sb.append("..");
					sb.append(")");

					next = u.parent;
					
				}
			}else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;

					
				} else {
					next = u.parent;
					sb.append(".");
					sb.append(")");
				}
			}else{
				sb.append(")");
				next = u.parent;
			}
			
			prev = u;
			u = next;
		}		
		return sb.toString();
	}
	public String bracketSequence2() {
		StringBuilder sb = new StringBuilder();
		// TODO: Your code goes here, use sb.append(), must avoid recursion
		bracketSequenceHelper(r, sb);
		return sb.toString();
	}

	protected void bracketSequenceHelper(Node u, StringBuilder sb){
		if (u == nil) {
			sb.append('.');
			return;
		}
		sb.append('(');
		bracketSequenceHelper(u.left, sb);
		bracketSequenceHelper(u.right, sb);
		sb.append(')');
	}
}
