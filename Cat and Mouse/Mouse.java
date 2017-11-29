/**
 * Created by Dtylan on 2016-07-05.
 */
public class Mouse extends Creature {

    public Mouse() {

        int genderInt = (int)(Math.random()*2);

        if(genderInt == 0)
            gender = true;
        else
            gender = false;
    }

    public String toString() {

        return "M";
    }
}
