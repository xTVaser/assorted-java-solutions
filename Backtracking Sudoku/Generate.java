import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Generate {

    public static void main(String[] args) {

        String fileName = "puzzle1.dat";

        int[][] board = {   {4,7,0,12,  0,11,14,0, 0,6,5,0, 0,0,0,9},
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

        board = new int[16][16];
        board[0][0] = 16;
        board[15][15] = 16;

        try(ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))){

            dos.writeObject(board);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
