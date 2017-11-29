/**
 * Author - Tyler Wilding
 * Class which holds the actual 2-3 Tree, handles the insertion special cases, etc.
 */
public class TwoThreeTree {

    private TwoThreeNode root;
    private String preOrder = "";
    private int numberOfItems; //Just for Debugging

    /**
     * Method to create a basic 2-3 tree, constructs the root.
     */
    public TwoThreeTree() {

        root = new TwoThreeNode();
        numberOfItems = 0;
    }

    /**
     * Method to insert a value, and ultimately a node into a 2-3 tree.
     * @param value Just pass it the value we want to insert.
     */
    public void insert(int value) {

        TwoThreeNode insertionLeaf = findPosition(root, value);

        insertionLeaf.insertItem(value);

        if(insertionLeaf.getNumberOfItems() > 2)
            splitNode(insertionLeaf);

        numberOfItems++; //Not really nessecary
    }

    /**
     * Recursive method that is used to find the insertion leaf
     * @param node The node currently being examined
     * @param value The value that we want to insert
     * @return Returns the leaf where we should insert the value
     */
    private TwoThreeNode findPosition(TwoThreeNode node, int value) {

        if(node.isLeaf())
            return node;

        else {

            if(node.getItem(0) >= value)
                return findPosition(node.getChild(0),value);

            else if((node.getNumberOfItems() < 2) || node.getItem(1) >= value)
                return findPosition(node.getChild(1),value);

            else
                return findPosition(node.getChild(2),value);
        }
    }

    /**
     * Method used to handle the special cases for splitting a node upon insertion
     * @param node Pass it the node we need to split.
     */
    private void splitNode(TwoThreeNode node) {

        TwoThreeNode parent = node.getParent();
        TwoThreeNode sibling = new TwoThreeNode();


        //If its a leaf and also the root
        if(node.isLeaf() && parent == null) {

            TwoThreeNode newParent = new TwoThreeNode();
            newParent.setIsLeaf(false);

            newParent.insertItem(node.deleteItem(1));
            sibling.insertItem(node.deleteItem(2));

            newParent.insertChild(node);
            newParent.insertChild(sibling);

            node.setParent(newParent);
            sibling.setParent(newParent);

            root = newParent;
        }

        //If it is a leaf and has a normal parent
        if(node.isLeaf() && parent != null) {

            parent.insertItem(node.deleteItem(1));

            sibling.insertItem(node.deleteItem(2));
            sibling.setParent(parent);

            parent.insertChild(sibling);
        }

        //If its a root with children
        if(!node.isLeaf() && parent == null) {

            TwoThreeNode newParent = new TwoThreeNode();
            newParent.setIsLeaf(false);

            newParent.insertItem(node.deleteItem(1));
            sibling.insertItem(node.deleteItem(2));
            sibling.setParent(newParent);
            sibling.setIsLeaf(false);

            node.setParent(newParent);

            sibling.insertChild(node.deleteChild(2));
            sibling.insertChild(node.deleteChild(3));

            sibling.getChild(0).setParent(sibling);
            sibling.getChild(1).setParent(sibling);

            newParent.insertChild(node);
            newParent.insertChild(sibling);

            root = newParent;
        }

        //If its an internal and not the root
        else if(!node.isLeaf() && node != root) {

            parent.insertItem(node.deleteItem(1));

            sibling.insertItem(node.deleteItem(2));
            sibling.setParent(parent);
            sibling.setIsLeaf(false);

            sibling.insertChild(node.deleteChild(2));
            sibling.insertChild(node.deleteChild(3));

            sibling.getChild(0).setParent(sibling);
            sibling.getChild(1).setParent(sibling);

            parent.insertChild(sibling);
        }

        if(parent != null && parent.getNumberOfItems() > 2) {
            splitNode(parent);
        }
    }

    /**
     * Public accessor to the ascendingOrder method.
     */
    public void ascendingOrder() {

        ascendingOrder(root);
    }

    /**
     * The recursive method that actually handles the ascending order, essentially an in-order sort.
     * @param node The node currently examined.
     */
    private void ascendingOrder(TwoThreeNode node) {

        if(node == null)
            return;

        if(node.isLeaf()) {

            System.out.printf("%4d ",node.getItem(0));
            if(node.getNumberOfItems() > 1)
                System.out.printf("%4d ",node.getItem(1));
        }

        else if(node.getNumberOfChildren() == 2) {

            ascendingOrder(node.getChild(0));
            System.out.printf("%4d ",node.getItem(0));
            ascendingOrder(node.getChild(1));
        }

        else if(node.getNumberOfChildren() == 3) {

            ascendingOrder(node.getChild(0));
            System.out.printf("%4d ",node.getItem(0));
            ascendingOrder(node.getChild(1));
            System.out.printf("%4d ",node.getItem(1));
            ascendingOrder(node.getChild(2));
        }
    }

    /**
     * Public accessor for descendingOrder method.
     */
    public void descendingOrder() {

        descendingOrder(root);
    }

    /**
     * Recursive method that prints out the tree in descending order, this is a slightly modified in-order sort.
     * @param node The node currently examined.
     */
    private void descendingOrder(TwoThreeNode node) {

        if(node == null)
            return;

        if(node.isLeaf()) {

            if(node.getNumberOfItems() > 1)
                System.out.printf("%4d ",node.getItem(1));
            System.out.printf("%4d ",node.getItem(0));

        }

        else if(node.getNumberOfChildren() == 2) {

            descendingOrder(node.getChild(1));
            System.out.printf("%4d ",node.getItem(0));
            descendingOrder(node.getChild(0));
        }

        else if(node.getNumberOfChildren() == 3) {

            descendingOrder(node.getChild(2));
            System.out.printf("%4d ",node.getItem(1));
            descendingOrder(node.getChild(1));
            System.out.printf("%4d ",node.getItem(0));
            descendingOrder(node.getChild(0));


        }
    }

    /**
     * Public accessor for the method to save the tree.
     */
    public String saveToFile() {

        saveToFile(root);
        return preOrder;
    }

    /**
     * Recursive method to save the tree into a file, essentially a pre-order traversal.
     * @param node The node currently examined
     * @return A string containing the pre-order traversal.
     */
    private void saveToFile(TwoThreeNode node) {

        if(node == null)
            return;

        if(node.isLeaf()) {

            preOrder += node.getItem(0) + "\n";
            if(node.getNumberOfItems() > 1)
                preOrder += node.getItem(1) + "\n";
        }

        else if(node.getNumberOfChildren() == 2) {

            preOrder += node.getItem(0) + "\n";
            saveToFile(node.getChild(0));
            saveToFile(node.getChild(1));
        }

        else if(node.getNumberOfChildren() == 3) {

            preOrder += node.getItem(0) + "\n";
            saveToFile(node.getChild(0));
            preOrder += node.getItem(1) + "\n";
            saveToFile(node.getChild(1));
            saveToFile(node.getChild(2));
        }
    }
}
