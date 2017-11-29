import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by Dtylan on 2016-08-03.
 */
public class Verify {

    public static void main(String[] args) throws Exception {

        int[][] board;

        ObjectInputStream stream = new ObjectInputStream(new FileInputStream("puzzle1_solution.dat"));

        board = (int[][])stream.readObject();

        System.out.println(validInput(board));
    }
    public static boolean validInput(int[][] a) {

        //check rows
        for(int row = 0; row < 16; row++){
            for(int sCol = 0; sCol < 15; sCol++){
                int chkVal = a[row][sCol];
                if(chkVal==0) continue;
                for(int curCol = sCol+1; curCol < 16; curCol++){
                    if(a[row][curCol] ==  chkVal) {
                        System.out.println("row:sCol:chkVal:curCol:curVal -->"
                                + row +":"+ sCol +":"+ chkVal +":"+ curCol +":"+ a[row][curCol]);
                        return false;
                    }
                }
            }
        }

        //check columns
        for(int col = 0; col < 16; col++){
            for(int sRow = 0; sRow < 15; sRow++){
                int chkVal = a[sRow][col];
                if(chkVal==0) continue;
                for(int curRow = sRow+1; curRow < 16; curRow++){
                    if(a[curRow][col] ==  chkVal) {
                        System.out.println("sRow:col:chkVal:curRow:curVal -->"
                                + sRow +":"+ col +":"+ chkVal +":"+ curRow +":"+ a[sRow][col]);
                        return false;
                    }
                }
            }
        }

        //check regions
        for(int rRow = 0; rRow < 4; rRow++){
            for(int rCol = 0; rCol < 4; rCol++){
                for(int pos = 0; pos < 15; pos++){
                    int chkVal = a[rCol*4 + (pos/4)][rCol*4 + pos%4];
                    if(chkVal==0) continue;
                    for(int cPos = pos+1; cPos < 16; cPos++){
                        if(a[rCol*4 + (cPos/4)][rCol*4 + cPos%4] == chkVal) {
                            System.out.println("rRow:rCol:pos:chkVal:cPos:curVal -->"
                                    + rRow +":"+ rCol +":"+ pos +":"+ chkVal +":"+ cPos +":"+
                                    a[rCol*4 + (cPos/4)][rCol*4 + cPos%4]);
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean checkSubGrid(int[][] board, int row, int column, int k) {

        int topLeftRow      = (row/4)*4;
        int topLeftColumn   = (column/4)*4;

        for (int x = 0; x < 4; x++) {

            for(int y = 0; y < 4; y++) {

                if(x != row && y != column) {

                    if(board[topLeftRow+x][topLeftColumn+y] == k)
                        return false;
                }
            }
        }
        return true;
    }
}
