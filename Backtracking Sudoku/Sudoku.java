import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * Author - Tyler Wilding
 * Sudoku - Parses through a Sudoku Grid and outputs the result
 */
public class Sudoku extends Application {

    private static int recursionDepth = 0;
    private static long finalTime = 0;

    //Very difficult sudoku puzzle
    private static int[][] board = {{4,7,0,12,  0,11,14,0, 0,6,5,0, 0,0,0,9},
                                    {11,16,0,0, 0,0,3,0, 14,1,0,0, 5,0,12,15},
                                    {0,0,9,0,   8,16,1,7, 0,0,15,0, 0,0,0,0},
                                    {3,0,0,10,  0,0,0,0, 11,0,2,16, 0,0,0,6},
                                    {0,0,11,0,  12,0,0,15, 0,14,0,0, 3,0,0,0},
                                    {13,0,8,0,  0,1,0,0, 0,0,7,0, 12,0,6,4},
                                    {16,2,10,0, 0,3,0,11, 5,9,0,0, 1,8,0,0},
                                    {0,0,6,0,   9,0,7,14, 0,8,0,13, 0,0,16,0},
                                    {0,11,0,7,  0,15,4,0, 10,0,0,5, 6,1,0,0},
                                    {1,8,0,0,   10,0,12,0, 0,4,9,0, 16,5,15,3},
                                    {15,0,0,3,  0,6,5,0, 0,0,8,0, 0,12,0,7},
                                    {10,0,0,16, 0,0,0,3, 1,0,0,15, 14,2,0,0},
                                    {0,10,0,0,  13,9,0,0, 6,15,0,7, 0,0,0,0},
                                    {14,0,0,0,  1,0,16,0, 12,0,4,8, 0,0,0,13},
                                    {0,4,0,0,   0,2,0,5, 0,10,11,0, 8,0,3,12},
                                    {8,6,0,2,   0,12,0,0, 0,5,13,0, 0,15,11,1}};

    public static void main(String[] args) throws Exception {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(("puzzle1.dat")));
        board = (int[][])in.readObject();

        long time1 = System.currentTimeMillis();

        printBoard(board);
        System.out.println();

        //Solve the Board
        solve(board);

        finalTime = System.currentTimeMillis() - time1;

        printBoard(board);
        System.out.print("Recursion Depth "+recursionDepth+" at "+finalTime+"ms");
        Application.launch(args);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("puzzle1_solution.dat"));
        out.writeObject(board);
    }

    /**
     * Prints a 2D array to the console
     * @param board the 2D array to be printed
     */
    public static void printBoard(int[][] board) {

        for(int row =0; row < 16; row++) {

            for (int column=0; column < 16; column++) {

                System.out.print(board[row][column]+" ");
            }
            System.out.println();
        }
    }

    /**
     * Shows the GUI window.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane pane = new GridPane();
        pane.setHgap(1);
        pane.setVgap(1);

        Rectangle[][] rectangleArray = new Rectangle[16][16];

        //Make 81 Rectangles
        for (int row = 0; row < 16; row++) {
            for(int column = 0; column < 16; column++) {

                rectangleArray[row][column] = new Rectangle(36,36);
                rectangleArray[row][column].setFill(Color.WHITE);
                rectangleArray[row][column].setStroke(Color.BLACK);
                rectangleArray[row][column].setStrokeWidth(1);
            }
        }

        //Place the rectangles and the values into the gridpane
        for(int row = 0; row < 16; row++) {

            for(int column = 0; column < 16; column++) {

                Label newLabel = new Label(Integer.toString(board[row][column]));
                GridPane.setHalignment(newLabel, HPos.CENTER);

                pane.add(rectangleArray[row][column],column,row);
                pane.add(newLabel,column,row);


            }
        }

        pane.add(new Label("\tRecursion Depth "+recursionDepth+"\n       At "+finalTime+"ms Execution Time"),16,8);

        Scene scene = new Scene(pane, 875, 550);
        primaryStage.setTitle("Sudoku Board");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Method that returns whether or not the value desired can be inserted with the rules of sudoku
     * @param board the 2D array that holds the values
     * @param input the value that is to be placed, maybe
     * @param row the row it is in
     * @param column the column it is in
     * @return true or false
     */
    public static boolean validInput(int[][] board, int input, int row, int column) {

        //Check Row
        for(int i = 0; i < 16; i++) {

            if(board[row][i] == input)
                return false;
        }

        //Check Column
        for(int i = 0; i < 16; i++) {

            if(board[i][column] == input)
                return false;
        }

        //Check Sub-Grid
        //Get the topleft coordinate of that subgrid
        int topLeftRow      = (row/4)*4;
        int topLeftColumn   = (column/4)*4;

        for (int x = 0; x < 4; x++) {

            for(int y = 0; y < 4; y++) {

                if(board[topLeftRow+x][topLeftColumn+y] == input)
                    return false;
            }
        }

        return true;
    }

    /**
     * Fills the sudoku board using backtracking
     * @param board the array that holds the starting values
     * @return true or false, but this is not to be stored
     * @throws Exception
     */
    public static boolean solve(int[][] board) {

        recursionDepth++;

        //Pass through every item in the array
        for (int row = 0; row < 16; row++) {

            for (int column = 0; column < 16; column++) {

                if (board[row][column] != 0) //If the position is not empty, move on
                    continue;

                for (int potentialValue = 1; potentialValue <= 16; potentialValue++) {

                    if (validInput(board, potentialValue, row, column)) { //If the number can be inserted, insert it

                        board[row][column] = potentialValue;
                        //System.out.println(row+","+column);
                        //printBoard(board);
                        //System.out.println();

                        if (solve(board)) //Move to the next position, if everything goes smoothly, return
                            return true;

                        else //If things did not go smoothly, set to 0 so it will be filled in after backtracking
                            board[row][column] = 0;

                    }
                }
                //Return to the previous stack frame, backtrack because we didnt already return true
                return false;
            }
        }
        //If everything is filled in, were done
        return true;
    }
}
