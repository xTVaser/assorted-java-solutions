import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TreeTest {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        /**
        TwoThreeTree treeTest = new TwoThreeTree();
        treeTest.insert(40);
        treeTest.insert(20);
        treeTest.insert(10);
        treeTest.insert(30);
        treeTest.insert(80);
        treeTest.insert(60);
        treeTest.insert(50);
        treeTest.insert(70);
        treeTest.insert(100);
        treeTest.insert(90);
        treeTest.insert(110);
        treeTest.ascendingOrder();
        System.out.println();
        treeTest.descendingOrder();
        System.out.println();
        String testString = treeTest.saveToFile();
        */

        TwoThreeTree tree = new TwoThreeTree();

        if (args[0] == null) {
            System.out.println("You did not enter a file, try again");
            System.exit(1);
        }

        System.out.println("The file you passed was "+args[0]);

        File inputFile = new File("txt/"+args[0]);

        if(!inputFile.exists()) {
            System.out.println("The file does not exist, try again");
            System.exit(1);
        }

        Scanner fileScanner = new Scanner(inputFile);

        while(fileScanner.hasNextInt()) {

            tree.insert(fileScanner.nextInt());
        }
        fileScanner.close();


        boolean loop = true;
        while(loop) {

            System.out.println();
            System.out.println( "  .:::.           .::.      :::::::::::::::::::..   .,::::::  .,::::::   .::::::. \n" +
                                " ,;'``;.         ;'`';;,    ;;;;;;;;'''';;;;``;;;;  ;;;;''''  ;;;;''''  ;;;`    ` \n" +
                                " ''  ,[['           .n[[         [[      [[[,/[[['   [[cccc    [[cccc   '[==/[[[[,\n" +
                                " .c$$P'    cccc    ``\"$$$.       $$      $$$$$$c     $$\"\"\"\"    $$\"\"\"\"     '''    $\n" +
                                "d88 _,oo,          ,,o888\"       88,     888b \"88bo, 888oo,__  888oo,__  88b    dP\n" +
                                "MMMUP*\"^^          YMMP\"         MMM     MMMM   \"W\"  \"\"\"\"YUMMM \"\"\"\"YUMMM  \"YMmMY\" \n");
            System.out.println("============================MENU=============================");
            System.out.println("[1]:Insert a Value into the Tree");
            System.out.println("[2]:Print the Tree in Ascending Order");
            System.out.println("[3]:Print the Tree in Descending Order");
            System.out.println("[4]:Save the Tree to a File and Exit:");
            System.out.println("=============================================================");

            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();

            final Pattern pattern = Pattern.compile("^[1-4]");

            if(!pattern.matcher(Integer.toString(input)).matches())
                System.out.println("Invalid Input, Try Again");

            else {

                if(input == 1) {

                    System.out.println("Enter the Number you Wish to Insert:");
                    int newValue = scanner.nextInt();

                    tree.insert(newValue);
                    System.out.println("Number has been Successfully Inserted!");
                }
                else if(input == 2) {

                    System.out.print(" ");
                    tree.ascendingOrder();
                }
                else if(input == 3) {

                    System.out.print(" ");
                    tree.descendingOrder();
                }
                else if(input == 4) {
                    String sendToFile = tree.saveToFile();

                    OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream("txt/SavedTree.txt"));

                    output.write(sendToFile);
                    output.close();
                    System.out.println("Saved!");
                }
            }
            System.out.println();
        }
    }
}