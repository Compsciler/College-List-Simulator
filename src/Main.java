public class Main {
    private static int collegeTotal = 50;
    private static int collegeListTotal = 8;
    private static int noAcceptanceRankScore = 51;

    private static int[] collegeList = new int[8];
    private static int[] minExpectedRankCollegeList;
    private static double minExpectedRank = Double.MAX_VALUE;

    public static void main(String[] args) {
        for (int i = 0; i < collegeListTotal; i++){
            collegeList[i] = i + 1;
        }

        double chanceAddedPerRank = 0.02;

        outerLoop:
        while (true){
            double expectedRank = 0;
            double noPreviousAcceptancesChance = 1;
            for (int college: collegeList){
                double collegeChance = college * chanceAddedPerRank;
                expectedRank += noPreviousAcceptancesChance * collegeChance * college;
                noPreviousAcceptancesChance *= (1 - collegeChance);
            }
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

        System.out.println(minExpectedRankCollegeList);
        System.out.println("Expected rank: " + minExpectedRank);
    }
    public static void incrementCollegeList() throws IllegalArgumentException{
        int incrementIndex = collegeListTotal;
        do {
            incrementIndex--;
            if (incrementIndex < 0){
                throw new IllegalArgumentException();
            }
            collegeList[incrementIndex]++;
        } while (collegeList[incrementIndex] >= collegeTotal + (collegeListTotal - incrementIndex));
        for (int i = incrementIndex + 1; i < collegeListTotal; i++){
            collegeList[i] = collegeList[i - 1] + 1;
        }
    }
}