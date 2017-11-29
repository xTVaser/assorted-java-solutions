/**
 * Created by Dtylan on 2016-07-11.
 */
public class LotteryWinnings {

    public static void main(String[] args) {

        System.out.println(earningsInWeek(10));
        System.out.println(lotteryWinnings(10));
        System.out.println(earningsInWeek(10));
        System.out.println(lotteryWinnings(20));
    }

    public static double earningsInWeek(int week) {

        if (week == 1)
            return 0.01;

        return 2 * earningsInWeek(week-1);
    }

    public static double lotteryWinnings(int week) {

        if(week == 1)
            return 0.01;

        return earningsInWeek(week) + lotteryWinnings(week-1);
    }
}
