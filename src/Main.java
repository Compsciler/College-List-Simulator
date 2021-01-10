import java.util.*;

public class Main {
    private static int collegeTotal = 50;
    private static int collegeListTotal = 8;
    private static int noAcceptanceRankScore = 100; // = (collegeTotal + 1) to (collegeTotal * 2)

    private static int[] collegeList = new int[collegeListTotal];
    private static int[] minExpectedRankCollegeList;
    private static double minExpectedRank = Double.MAX_VALUE;
    private static double chanceAddedPerRank = 1.0 / collegeTotal;

    public static void main(String[] args) {
        calculateMinExpectedRankCollegeList();
    }

    public static void calculateMinExpectedRankCollegeList(){
        for (int i = 0; i < collegeListTotal; i++){
            collegeList[i] = i + 1;
        }

        outerLoop:
        while (true){
            double expectedRank = expectedRank();

            if (expectedRank < minExpectedRank){
                minExpectedRank = expectedRank;
                minExpectedRankCollegeList = collegeList.clone();
            }

            try {
                incrementCollegeList();
            } catch (IllegalArgumentException e){
                break outerLoop;
            }
        }

        System.out.println(Arrays.toString(minExpectedRankCollegeList));
        System.out.println("Expected rank: " + minExpectedRank);
    }

    public static double expectedRank(){
        double res = 0;
        double noPreviousAcceptancesChance = 1;
        for (int college: collegeList){
            double collegeChance = college * chanceAddedPerRank;
            res += noPreviousAcceptancesChance * collegeChance * college;
            noPreviousAcceptancesChance *= (1 - collegeChance);
        }
        res += noPreviousAcceptancesChance * noAcceptanceRankScore;

        return res;
    }

    public static void incrementCollegeList() throws IllegalArgumentException{
        int incrementIndex = collegeListTotal;
        do {
            incrementIndex--;
            if (incrementIndex < 0){
                throw new IllegalArgumentException();
            }
            collegeList[incrementIndex]++;
        } while (collegeList[incrementIndex] >= collegeTotal - (collegeListTotal - incrementIndex) + 2);
        for (int i = incrementIndex + 1; i < collegeListTotal; i++){
            collegeList[i] = collegeList[i - 1] + 1;
        }
    }
}