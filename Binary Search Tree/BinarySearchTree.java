import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Dtylan on 2016-08-09.
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements BSTInterface<T> {

    TreeNode<T> root = null;    //By default the tree is empty

    @Override
    public boolean isEmpty() {

        return root == null;
    }

    public boolean isLeaf(TreeNode<T> tNode) { //He wants them to have a isLeaf that can be called using the data item, im not going to do it.

        return (tNode.getLeft() == null && tNode.getRight() == null);
    }

    @Override
    public boolean contains(T item) {

        return contains(item, root);
    }

    private boolean contains(T item, TreeNode<T> tRoot) {

        if(tRoot == null)
            return false;

        if(item.compareTo(tRoot.getData()) == 0)
            return true;

        else if(item.compareTo(tRoot.getData()) < 0)
            return contains(item, tRoot.getLeft());

        else
            return contains(item, tRoot.getRight());
    }

    @Override
    public void add(T item) throws BSTException {

        if(isEmpty())
            root = new TreeNode(item);

        else
            add(item, root);

    }

    private void add(T item, TreeNode<T> tRoot) throws BSTException {

        if(item.compareTo(tRoot.getData()) == 0)
            throw new BSTException("Duplicate Item found in Tree: "+item);

        if(item.compareTo(tRoot.getData()) < 0) {

            if(tRoot.getLeft() == null)
                tRoot.setLeft(new TreeNode(item));

            else
                add(item, tRoot.getLeft());
        }

        else {

            if(tRoot.getRight() == null)
                tRoot.setRight(new TreeNode(item));

            else
                add(item, tRoot.getRight());
        }
    }



    @Override
    public void delete(T item) throws BSTException {

        if(isEmpty())
            throw new BSTException("Can't delete from an empty tree");

        delete(item, root);
    }

    private TreeNode delete(T item, TreeNode<T> tRoot) throws BSTException {

        if(tRoot == null)
            throw new BSTException("Can't delete from tree, item not found "+item);

        if(item.compareTo(tRoot.getData()) == 0)
            tRoot = deleteNode(tRoot);

        else if(item.compareTo(tRoot.getData()) < 0)
            tRoot.setLeft(delete(item, tRoot.getLeft()));

        else
            tRoot.setRight(delete(item, tRoot.getRight()));

        return tRoot;
    }

    private TreeNode deleteNode(TreeNode<T> tRoot) { //Returns the replacement node

        // There are four cases to consider:
        // 1. The tRoot is a leaf.
        // 2. The tRoot has no left child.
        // 3. The tRoot has no right child.
        // 4. The tRoot has two children.

        if(isLeaf(tRoot))
            return null;

        else if(tRoot.getLeft() == null)
            return tRoot.getRight();

        else if(tRoot.getRight() == null)
            return tRoot.getLeft();

        T replacementItem = findLeftMostItem(tRoot.getRight());
        tRoot.setData(replacementItem);
        tRoot.setRight(deleteLeftMostNode(tRoot.getRight()));
        return tRoot;
    }

    private T findLeftMostItem(TreeNode<T> tRoot) {

        if(tRoot.getLeft() == null)
            return tRoot.getData();

        return findLeftMostItem(tRoot.getLeft());
    }

    private TreeNode<T> deleteLeftMostNode(TreeNode<T> tRoot) {

        if(tRoot.getLeft() == null)
            return tRoot.getRight();

        tRoot.setLeft(deleteLeftMostNode(tRoot.getLeft()));
        return tRoot;
    }

    @Override
    public void removeAll() {

        root = null;
    }

    public int countNodes() {

        return countNodes(root);
    }

    private int countNodes(TreeNode<T> tRoot) {

        if(tRoot == null)
            return 0;

        return countNodes(tRoot.getLeft()) + countNodes(tRoot.getRight()) + 1;
    }

    public int minimumHeight() {

        return minimumHeight(root);
    }

    private int minimumHeight(TreeNode<T> tRoot) {

        if(tRoot == null)
            return 0;

        return 1 + Math.min(minimumHeight(tRoot.getLeft()), minimumHeight(tRoot.getRight()));
    }

    public int maximumHeight() {

        return maximumHeight(root);
    }

    private int maximumHeight(TreeNode<T> tRoot) {

        if(tRoot == null)
            return 0;

        return 1 + Math.max(maximumHeight(tRoot.getLeft()), maximumHeight(tRoot.getRight()));
    }

    public boolean isBalanced() {

        return isBalanced(root);
    }

    private boolean isBalanced(TreeNode<T> tRoot) {
        //A tree where the maximum height of any branch is no more than one more than the minimum height of any branch.

        if(tRoot == null)
            return true;

        return Math.abs(maximumHeight(tRoot) - minimumHeight(tRoot)) <= 1 &&
                (isBalanced(tRoot.getLeft()) && isBalanced(tRoot.getRight()));

    }

    public boolean isFull(String type) {

        if(type.equalsIgnoreCase("algoma"))
            return isFullAlgoma();

        return isFullNormal(root);
    }

    //Algoma's Definition = Tree has its maximum number of items 2^k - 1 Where k = max height
    private boolean isFullAlgoma() {

        return countNodes() == Math.pow(2, maximumHeight()) - 1;
    }

    //Algoma's Definition = Tree has its maximum number of items 2^k - 1 Where k = max height
    private boolean isFullAlgoma(TreeNode<T> tRoot) {

        if(isEmpty())
            return true;

        if(tRoot == null)
            return true;

        if(isLeaf(tRoot))
            return true;

        return (tRoot.getLeft() != null && tRoot.getRight() != null) &&
                (isFullAlgoma(tRoot.getLeft()) && isFullAlgoma(tRoot.getRight()));
    }

    //Normal Defintion = All nodes have either 0 children (they are leaves) or they have maximum children (2)
    private boolean isFullNormal(TreeNode<T> tRoot) {

        if(isLeaf(tRoot))
            return true;

        if(tRoot.getLeft() == null || tRoot.getRight() == null)
            return false;

        return isFullNormal(tRoot.getLeft()) && isFullNormal(tRoot.getRight());
    }

    public boolean isComplete() {

        return isComplete(root);
    }

    private boolean isComplete(TreeNode<T> tRoot) {

        if(isEmpty())
            return true;

        if(tRoot == null)
            return true;

        else if(maximumHeight(tRoot.getLeft()) == maximumHeight(tRoot.getRight()))
            return isFullAlgoma(tRoot.getLeft()) && isComplete(tRoot.getRight());

        else if(maximumHeight(tRoot.getLeft()) > maximumHeight(tRoot.getRight()))
            return isComplete(tRoot.getLeft());

        else
            return false;
    }

    public String preOrder() {

        preOrder = "";
        preOrderTraversal(root);
        return preOrder;
    }

    private String preOrder = "";
    private void preOrderTraversal(TreeNode<T> tRoot) {

        if(tRoot == null) {
            return;
        }

        if(isLeaf(tRoot)) {
            preOrder += tRoot.getData().toString() + ",";
            return;
        }

        preOrder += tRoot.getData().toString() + ",";
        preOrderTraversal(tRoot.getLeft());
        preOrderTraversal(tRoot.getRight());
    }

    public String inOrder() {

        inOrder = "";
        inOrderTraversal(root);
        return inOrder;
    }

    private String inOrder = "";
    private void inOrderTraversal(TreeNode<T> tRoot) {

        if(tRoot == null) {
            return;
        }

        if(isLeaf(tRoot)) {
            inOrder += tRoot.getData().toString() + ",";
            return;
        }

        inOrderTraversal(tRoot.getLeft());
        inOrder += tRoot.getData().toString() + ",";
        inOrderTraversal(tRoot.getRight());
    }

    public String postOrder() {

        postOrder = "";
        postOrderTraversal(root);
        return postOrder;
    }

    private String postOrder = "";
    private void postOrderTraversal(TreeNode<T> tRoot) {

        if(tRoot == null) {
            return;
        }

        if(isLeaf(tRoot)) {
            postOrder += tRoot.getData().toString() + ",";
            return;
        }

        postOrderTraversal(tRoot.getLeft());
        postOrderTraversal(tRoot.getRight());
        postOrder += tRoot.getData().toString() + ",";
    }

    public void savePreOrder() throws FileNotFoundException {

        PrintWriter printToFile = new PrintWriter(new File("preOrder_save.dat"));
        printToFile.print(preOrder());
        printToFile.close();
    }

    public void saveInOrder() throws FileNotFoundException {

        PrintWriter printToFile = new PrintWriter(new File("inOrder_save.dat"));
        printToFile.print(inOrder());
        printToFile.close();
    }

    public void savePostOrder() throws FileNotFoundException {

        PrintWriter printToFile = new PrintWriter(new File("postOrder_save.dat"));
        printToFile.print(postOrder());
        printToFile.close();
    }
}
