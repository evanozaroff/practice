import java.util.Stack;

public class BinaryTree <E> {

    //Initialize Class Variables
    private int weight;
    private E value;
    private BinaryTree<E> childLeft;
    private BinaryTree<E> childRight;

    //Standard Constructor creates root node with weight of 0
    public BinaryTree(E val){
        weight = 0;
        value = val;
        childLeft = null;
        childRight = null;
    }

    //Constructor merges two trees under root node
    public BinaryTree(BinaryTree t1, BinaryTree t2){
        weight = t1.getWeight() + t2.getWeight();
        value = null;
        childLeft = t1;
        childRight = t2;
    }

    //Increments Weight
    public void incrementWeight()
    {
        weight++;
    }

    //Gets Weight
    public int getWeight()
    {
        return weight;
    }

    //Gets Value
    public E getValue()
    {
        return value;
    }

    //Returns Left Child
    public BinaryTree getLeftChild()
    {
        return childLeft;
    }

    //Returns Right Child
    public BinaryTree getRightChild()
    {
        return childRight;
    }

    //Returns true if referenced tree is leaf node
    public boolean isLeaf()
    {
        if(value == null) return false;
        return true;
    }

    //Merge binary trees
    public BinaryTree merge(BinaryTree t1, BinaryTree t2)
    {
        return new BinaryTree(t1,t2);
    }

    void iterativePreorder()
    {
        iterativePreorder(root);
    }

    // An iterative process to print preorder traversal of Binary tree
    void iterativePreorder(BinaryTree node) {

        // Base Case
        if (node.isLeaf()) {
            return;
        }

        // Create an empty stack and push root to it
        Stack<BinaryTree> nodeStack = new Stack<BinaryTree>();
        nodeStack.push(root);

        /* Pop all items one by one. Do following for every popped item
         a) print it
         b) push its right child
         c) push its left child
         Note that right child is pushed first so that left is processed first */
        while (nodeStack.empty() == false) {

            // Pop the top item from stack and print it
            Node mynode = nodeStack.peek();
            System.out.print(mynode.data + " ");
            nodeStack.pop();

            // Push right and left children of the popped node to stack
            if (mynode.right != null) {
                nodeStack.push(mynode.right);
            }
            if (mynode.left != null) {
                nodeStack.push(mynode.left);
            }
        }
    }
}
