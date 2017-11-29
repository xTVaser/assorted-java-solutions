import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Dtylan on 2016-08-09.
 */
public class TestTree {

    public static void main(String[] args) throws Exception {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add(50);
        tree.add(25);
        tree.add(15);
        tree.add(14);
        tree.add(16);
        tree.add(26);
        tree.add(100);
        tree.add(75);
        tree.add(74);
        tree.add(76);
        tree.add(125);
        tree.add(115);
        tree.add(114);
        tree.add(116);
        tree.add(130);
        tree.add(129);
        tree.add(131);


        System.out.println("Count Nodes: " + tree.countNodes());
        System.out.println("Minimum Height: " + tree.minimumHeight());
        System.out.println("Maximum Height: " + tree.maximumHeight());
        System.out.println("Is Full (Algoma)?: " + tree.isFull("algoma"));
        System.out.println("Is Full (Normal)?: " + tree.isFull("normal"));
        System.out.println("Complete?: " + tree.isComplete());
        System.out.println("Balanced?: " + tree.isBalanced());
        System.out.println("Pre-Order: " + tree.preOrder());
        System.out.println("In-Order: " + tree.inOrder());
        System.out.println("Post-Order: " + tree.postOrder());
        System.out.println();

    }
}
