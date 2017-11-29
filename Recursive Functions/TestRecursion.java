/**
 * Created by Dtylan on 2016-07-11.
 */
public class TestRecursion {

    public static void main(String[] args) {

        RecursiveLibrary libs = new RecursiveLibrary();

        //Question 1
        System.out.println(libs.changePi("xpix"));
        System.out.println(libs.changePi("pipi"));
        System.out.println(libs.changePi("pip"));
        System.out.println();

        //Question 2
        System.out.println(libs.triangle(0));
        System.out.println(libs.triangle(1));
        System.out.println(libs.triangle(2));
        System.out.println();

        //Question 3
        System.out.println(libs.endX("xxre"));
        System.out.println(libs.endX("xxhixx"));
        System.out.println(libs.endX("xhixhix"));
        System.out.println();

        //Question 4
        System.out.println(libs.numOf6s(new int[] {1,6,4,6,1}, 0));
        System.out.println(libs.numOf6s(new int[] {1,4}, 0));
        System.out.println(libs.numOf6s(new int[] {6}, 0));
        System.out.println();

        //Question 5
        System.out.println(libs.pairStar("hello"));
        System.out.println(libs.pairStar("xxyy"));
        System.out.println(libs.pairStar("aaaa"));
        System.out.println();

        //Question 6
        System.out.println(libs.countAbc("abc"));
        System.out.println(libs.countAbc("abcxxabc"));
        System.out.println(libs.countAbc("abaxxabcaba"));

    }
}
