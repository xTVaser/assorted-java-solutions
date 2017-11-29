/**
 * Created by Dtylan on 2016-07-05.
 */
public class Cat extends Creature {

    String color;



    public Cat() {

        String[] colors = {"black", "white", "grey", "orange"};

        color = colors[(int)(Math.random()*4)];

        int genderInt = (int)(Math.random()*2);

        if(genderInt == 0)
            gender = true;
        else
            gender = false;
    }

    public String toString() {

        if(color.equals("black"))
            return "B";
        if(color.equals("white"))
            return "W";
        if(color.equals("grey"))
            return "G";
        else
            return "O";
    }
}
