import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Dtylan on 2016-07-05.
 */
public class RunMe {

    static ArrayList<Creature> dontMove = new ArrayList<>();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the number of columns");
        int cols = in.nextInt();

        System.out.println("Please enter the number of rows");
        int rows = in.nextInt();

        System.out.println("How many iterations do you want to run? (0 for exit)");
        int its = in.nextInt();

        Creature[][] eco = new Creature[rows][cols];

        System.out.println("How many initial Cats");
        int cats = in.nextInt();

        System.out.println("How many initial Mice");
        int mice = in.nextInt();

        //Cant be more than the amount of spots in the array (rows * col)

        ArrayList<Spot> emptySpots = createEcosystem(eco, cats, mice);
        displayEcosystem(eco);

        for(int i = 0; i < its; i++) {

            iteration(eco, emptySpots);
        }
    }

    public static void displayEcosystem(Creature[][] eco) {

        for(int row = 0; row < eco.length; row++) {

            for(int col = 0; col < eco[row].length; col++) {

                if(eco[row][col] == null)
                    System.out.print("- ");
                else
                    System.out.print(eco[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    final static int DONT_MOVE = 0;
    final static int MICE_MATE = 1;
    final static int CAT_MATE = 2;
    final static int CAT_INTO_MOUSE = 3;
    final static int MOUSE_INTO_CAT = 4;
    final static int NORMAL_MOVE = 5;

    final static int CAT_LIKELYHOOD_TO_MOVE = 60;
    final static int MOUZ_LIKELYHOOD_TO_MOVE = 90;

    public static void move(Creature[][] array, String move, int row, int col, ArrayList<Spot> emptySpots) {

        //Runs into another same gender animal or off the grid or animal does not want to move
        if(validateMove(array, move, row, col) == DONT_MOVE) {
            System.out.println(array[row][col] +" Didn't Move");
            return;
        }

        int[] newIndexes = calcIndexes(move, row, col); // 0 =row, 1 = col

        //Runs into opposite gender same animal, mate, but still dont move
        if(validateMove(array, move, row, col) == MICE_MATE) {

            int numBabies = ((int)Math.random()*3) + 3;

            while(numBabies > 0 && emptySpots.size() > 0) {

                Spot spot = emptySpots.remove(0);
                Mouse mouse = new Mouse();
                array[spot.row][spot.col] = mouse;
                dontMove.add(mouse);
                numBabies--;
            }

            System.out.println(array[row][col] +" Mated");
            return;
        }

        //Runs into opposite gender same animal, mate, but still dont move
        if(validateMove(array, move, row, col) == CAT_MATE) {

            int numBabies = 2;

            while(numBabies > 0 && emptySpots.size() > 0) {

                Spot spot = emptySpots.remove(0);
                Cat cat = new Cat();
                array[spot.row][spot.col] = cat;
                dontMove.add(cat);
                numBabies--;
            }

            System.out.println(array[row][col] +" Mated");
            return;
        }

        //Cat runs into mouse, dead and cat moves
        if(validateMove(array, move, row, col) == CAT_INTO_MOUSE) {

            System.out.println(array[row][col] +" Ate a Mouse");
            dontMove.add(array[row][col]);
            array[newIndexes[0]][newIndexes[1]] = array[row][col];
            array[row][col] = null;
            emptySpots.add(new Spot(row, col));
            return;

        }

        //Mouse runs into cat, dead and cat stays still
        if(validateMove(array, move, row, col) == MOUSE_INTO_CAT) {

            System.out.println(array[row][col] +" Ate by Cat");
            array[row][col] = null;
            emptySpots.add(new Spot(row, col));

            return;
        }

        //Just a normal movement
        if(validateMove(array, move, row, col) == NORMAL_MOVE) {

            System.out.println(array[row][col] +" Moved");
            dontMove.add(array[row][col]);
            array[newIndexes[0]][newIndexes[1]] = array[row][col];
            array[row][col] = null;
            emptySpots.add(new Spot(row, col));
            return;
        }
    }

    public static int validateMove(Creature[][] array, String move, int row, int col) {

        int moveChance = (int)(Math.random()*101);

        if(array[row][col] instanceof Cat && moveChance >= CAT_LIKELYHOOD_TO_MOVE)
            return DONT_MOVE;

        else if(array[row][col] instanceof Mouse && moveChance >= MOUZ_LIKELYHOOD_TO_MOVE)
            return DONT_MOVE;

        else if(dontMove.contains(array[row][col]))
            return DONT_MOVE;

        int[] newIndexes = calcIndexes(move, row, col); // 0 =row, 1 = col

        //Out of bounds
        if(newIndexes[0] < 0 || newIndexes[0] >= array.length || newIndexes[1] < 0 || newIndexes[1] >= array[0].length)
            return DONT_MOVE;

        //Same gender, same animal
        if(array[row][col] instanceof Cat && array[newIndexes[0]][newIndexes[1]] instanceof Cat &&
                array[row][col].gender == array[newIndexes[0]][newIndexes[1]].gender) {
            return DONT_MOVE;
        }

        else if(array[row][col] instanceof Mouse && array[newIndexes[0]][newIndexes[1]] instanceof Mouse &&
                array[row][col].gender == array[newIndexes[0]][newIndexes[1]].gender) {
            return DONT_MOVE;
        }

        //Cat runs into mouse
        if(array[row][col] instanceof Cat && array[newIndexes[0]][newIndexes[1]] instanceof Mouse)
            return CAT_INTO_MOUSE;

        //Mouse runs into cat
        if(array[row][col] instanceof Mouse && array[newIndexes[0]][newIndexes[1]] instanceof Cat)
            return MOUSE_INTO_CAT;

        //Run into opposite gender same animal
        if(array[row][col] instanceof Cat && array[newIndexes[0]][newIndexes[1]] instanceof Cat &&
                array[row][col].gender != array[newIndexes[0]][newIndexes[1]].gender) {
            return CAT_MATE;
        }

        else if(array[row][col] instanceof Mouse && array[newIndexes[0]][newIndexes[1]] instanceof Mouse &&
                array[row][col].gender != array[newIndexes[0]][newIndexes[1]].gender) {
            return MICE_MATE;
        }

        else
            return NORMAL_MOVE;


    }

    public static int[] calcIndexes(String move, int row, int col) {

        if(move.equals("LEFT")) {

            int[] array = {row, col-1};
            return array;
        }
        if(move.equals("UP")) {

            int[] array = {row+1, col};
            return array;
        }
        if(move.equals("DOWN")) {

            int[] array = {row-1, col};
            return array;
        }
        if(move.equals("RIGHT")) {

            int[] array = {row, col+1};
            return array;
        }

        return null;
    }

    public static void iteration(Creature[][] eco, ArrayList<Spot> emptySpots) {

        for(int row = 0; row < eco.length; row++) {

            for(int col = 0; col < eco[row].length; col++) {

                if(eco[row][col] == null)
                    continue;

                String[] moves = {"LEFT", "RIGHT", "UP", "DOWN"};

                String move = moves[(int)(Math.random()*4)];

                move(eco, move, row, col, emptySpots);
            }
        }

        Collections.shuffle(emptySpots);
        dontMove.clear();
        displayEcosystem(eco);
    }

    public static ArrayList<Spot> createEcosystem(Creature[][] eco, int cats, int mice) {

        ArrayList<Spot> emptySpots = new ArrayList<Spot>();

        while(cats != 0) {
            for(int row = 0; row < eco.length; row++) {

                for(int col = 0; col < eco[row].length; col++) {

                    if(eco[row][col] == null && (int)(Math.random()*10) == 5) {
                        eco[row][col] = new Cat();
                        cats--;
                    }
                    if(cats == 0)
                        break;
                }
                if(cats == 0)
                    break;
            }
        }

        while(mice != 0) {
            for(int row = 0; row < eco.length; row++) {

                for(int col = 0; col < eco[row].length; col++) {

                    if(eco[row][col] == null && (int)(Math.random()*10) == 5) {
                        eco[row][col] = new Mouse();
                        mice--;
                    }
                    if(mice == 0)
                        break;
                }
                if(mice == 0)
                    break;
            }
        }

        for(int row = 0; row < eco.length; row++) {

            for(int col = 0; col < eco[row].length; col++) {

                if(eco[row][col] == null)
                    emptySpots.add(new Spot(row, col));
            }
        }

        //So i can just remove the first one so its random
        Collections.shuffle(emptySpots);
        return emptySpots;
    }
}
