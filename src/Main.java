public class Main {
    private static int collegeTotal = 50;
    private static int collegeListTotal = 8;
    private static int noAcceptanceRankScore = 51;

    private static int[] collegeList = new int[8];
    public static void main(String[] args) {
        for (int i = 0; i < collegeListTotal; i++){
            collegeList[i] = i + 1;
        }

        outerLoop:
        while (true){
            

            try {
                incrementCollegeList();
            } catch (IllegalArgumentException e){
                break outerLoop;
            }
        }
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