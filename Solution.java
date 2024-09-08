
public class Solution {

    private static final int[] RANGE_OF_VALUES = {1, 6};
    private static final int[] NO_VALID_ANSWER = {};

    public int[] missingRolls(int[] rolls, int mean, int numberOfMissingObservations) {
        int sumOfMissingObservations = calculateSumOfMissingObservations(rolls, mean, numberOfMissingObservations);

        if (sumOfMissingObservations < numberOfMissingObservations * RANGE_OF_VALUES[0]
                || sumOfMissingObservations > numberOfMissingObservations * RANGE_OF_VALUES[1]) {
            return NO_VALID_ANSWER;
        }

        return extractMissingValues(sumOfMissingObservations, numberOfMissingObservations);
    }

    private int calculateSumOfMissingObservations(int[] rolls, int mean, int numberOfMissingObservations) {
        int sumObservedValues = 0;
        for (int value : rolls) {
            sumObservedValues += value;
        }
        return mean * (rolls.length + numberOfMissingObservations) - sumObservedValues;
    }

    private int[] extractMissingValues(int sumOfMissingObservations, int numberOfMissingObservations) {
        int[] missingValues = new int[numberOfMissingObservations];
        int roundedDownMeanOfMissingObsarvations = sumOfMissingObservations / numberOfMissingObservations;
        int remainder = sumOfMissingObservations % numberOfMissingObservations;

        for (int i = 0; i < missingValues.length; ++i) {
            missingValues[i] = roundedDownMeanOfMissingObsarvations;
            if (remainder > 0) {
                ++missingValues[i];
                --remainder;
            }
        }
        return missingValues;
    }
}
