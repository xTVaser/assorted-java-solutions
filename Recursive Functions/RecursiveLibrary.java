/**
 * Created by Dtylan on 2016-07-11.
 */
public class RecursiveLibrary {

    public RecursiveLibrary() {
        //Memes
    }

    //Question 1
    public String changePi(String str) {

        if(str.length() == 0) //End of String
            return "";

        else if(str.length() >= 2 && str.substring(0,2).equals("pi")) { //We found a pi
            return "3.14" + changePi(str.substring(2));
        }

        return str.substring(0,1) + changePi(str.substring(1)); //Else just a normal character
    }

    //Question 2
    public int triangle(int rows) {

        if(rows == 0) //Base case
            return 0;

        return rows + triangle(rows-1);
    }

    //Question 3
    public String endX(String str) {

        if(str.length() == 0) //Base Case
            return "";

        else if(str.charAt(0) == 'x') //We found an X, put it at the end
            return endX(str.substring(1)) + "x";

        return str.substring(0,1) + endX(str.substring(1)); //Other character, put it in the front
    }

    //Question 4
    public int numOf6s(int[] nums, int index) {

        if(index >= nums.length) //Base case, end of array
            return 0;

        else if(nums[index] == 6) //We found a 6
            return 1 + numOf6s(nums,index+1);

        return numOf6s(nums, index+1); //We didnt
    }

    //Question 5
    public String pairStar(String str) {

        if(str.length() == 0) //Base case, end of string
            return "";

        else if(str.length() >= 2 && str.substring(0,1).equals(str.substring(1,2))) //If we found two identical characters next to each other
            return str.substring(0,1) + "*" + pairStar(str.substring(1));

        return str.substring(0,1) + pairStar(str.substring(1)); //Else we didnt, move on
    }

    //Question 6
    public int countAbc(String str) {

        if(str.length() == 0) //Base case, end of string
            return 0;

        else if(str.length() >= 3 && (str.substring(0,3).equals("abc") || str.substring(0,3).equals("aba"))) //If found one
            return 1 + countAbc(str.substring(2));

        return countAbc(str.substring(1)); //We didnt
    }
}
