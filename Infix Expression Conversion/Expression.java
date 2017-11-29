import java.util.Scanner;

/**
 * Created by Dtylan on 2016-08-04.
 */
public class Expression {

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);

        String test = "a+(b*c)";


        System.out.println(findExpression(test, 0, test.length()-1));
        if(findExpression(test, 0, test.length()-1) == test.length()-1)
            System.out.println("GOOD");
        else
            System.out.println("NOT GOOD");
    }

    public static int findExpression(String str, int start, int end) {

        start = findTerm(str, start, end);

        if(start < end && start != -1 &&
                (str.charAt(start + 1) == '+' || str.charAt(start + 1) == '-'))
            return findTerm(str, start+2,end);

        return start;
    }

    public static int findTerm(String str, int start, int end) {

        start = findFactor(str, start, end);

        if(start < end && start != -1 &&
                (str.charAt(start + 1) == '*' || str.charAt(start + 1) == '/'))
            return findFactor(str, start+2,end);

        return start;
    }

    public static int findFactor(String str, int start, int end) {

        if(Character.isLetter(str.charAt(start)))
            return start;

        else if(str.charAt(start) == '(') {

            start = findExpression(str, start+1, end);

            if(start < end && str.charAt(start+1) != ')')
                return -1;

            return start+1;
        }

        return -1;

    }
}
