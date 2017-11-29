/**
 * Author - Tyler Wilding
 * Node Class for the use in a 2-3 Tree, almost all functionality and complication is contained in this class
 * It handles insertions of items and children abstractly, you only have to pass it the item/child.
 */
public class TwoThreeNode {

    private int numberOfItems;
    private int numberOfChildren;
    private boolean isLeaf;

    private TwoThreeNode parent;

    private int[] items = new int[3]; //Holds a Temporary 3rd Item
    private TwoThreeNode[] children = new TwoThreeNode[4]; //Holds a Temporary 4th Child

    /**
     * Basic constructor for a 2-3 Node, has 0 items, no parent, and is a leaf by default.
     */
    public TwoThreeNode() {

        numberOfItems = 0;
        parent = null;
        isLeaf = true;
    }

    /**
     * @return Returns the number of items contained within the node.
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * @return Returns the number of children pointed to by the node.
     */
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    /**
     * @return Returns the parent of the node
     */
    public TwoThreeNode getParent() {
        return parent;
    }

    /**
     * @param parent Pass the node that you wish to be the new parent.
     */
    public void setParent(TwoThreeNode parent) {
        this.parent = parent;
    }

    /**
     * @return Returns whether or not the node is a leaf.
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * @param isLeaf Pass a boolean value to declare whether or not the node is a leaf.
     */
    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * @param index The index of which child you want to return
     * @return Returns the child asked for.
     */
    public TwoThreeNode getChild(int index) {

        return children[index];
    }

    /**
     * @param index The index of the item you want to return
     * @return Returns the item asked for.
     */
    public int getItem(int index) {

        return items[index];
    }

    /**
     * Method that is used to insert an item into the node
     * @param item Pass the value that you want inserted into the node
     */
    public void insertItem(int item) {

        if(numberOfItems == 0)
            items[0] = item;

        else {

            int index = 0;

            for(int i = 0; i < numberOfItems; i++) {
                if(item > items[i])
                    index++;
            }

            for(int i = items.length-1; i > index; i--) {

                items[i] = items[i-1];
            }

            items[index] = item;
        }

        numberOfItems++;
    }

    /**
     * Method used to delete an item from the node
     * @param index The index of the item you want deleted
     * @return The item will also be returned.
     */
    public int deleteItem(int index) {

        int returnValue = items[index];
        items[index] = 0;
        numberOfItems--;
        return returnValue;
    }

    /**
     * Method to find the max item out of all of the items
     * @return Returns the maximum item.
     */
    private int maxItem() {

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < items.length; i++) {

            if(items[i] > max)
                max = items[i];
        }

        return max;
    }

    /**
     * Method to insert a child into the node
     * @param node The node that is desired to be inserted into the node
     */
    public void insertChild(TwoThreeNode node) {

        if(numberOfChildren == 0)
            children[0] = node;

        else {

            int index = 0;

            for(int i = 0; i < numberOfChildren; i++) {
                if(children[i] != null && node.maxItem() > children[i].maxItem())
                    index++;
            }

            for(int i = children.length-1; i > index; i--) {

                children[i] = children[i-1];
            }

            children[index] = node;
        }

        numberOfChildren++;
    }

    /**
     * Method to delete a child from the node
     * @param index The index of the child wanting to be deleted
     * @return The node that is deleted will be returned.
     */
    public TwoThreeNode deleteChild(int index) {

        TwoThreeNode returnValue = children[index];
        children[index] = null;
        numberOfChildren--;
        return returnValue;
    }

    /**
     * String representation of the node, useful for debugging
     * @return Returns a string that contains the items contained within the node
     */
    public String toString() {

        String output = "";

        for(int i = 0; i < items.length; i++) {

            output += items[i] + ",";
        }

        return output;
    }
}
