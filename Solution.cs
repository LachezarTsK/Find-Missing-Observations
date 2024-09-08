
using System;

public class Solution
{
    private static readonly int[] RANGE_OF_VALUES = { 1, 6 };
    private static readonly int[] NO_VALID_ANSWER = { };

    public int[] MissingRolls(int[] rolls, int mean, int numberOfMissingObservations)
    {
        int sumOfMissingObservations = CalculateSumOfMissingObservations(rolls, mean, numberOfMissingObservations);

        if (sumOfMissingObservations < numberOfMissingObservations * RANGE_OF_VALUES[0]
            || sumOfMissingObservations > numberOfMissingObservations * RANGE_OF_VALUES[1])
        {
            return NO_VALID_ANSWER;
        }

        return ExtractMissingValues(sumOfMissingObservations, numberOfMissingObservations);
    }

    private int CalculateSumOfMissingObservations(int[] rolls, int mean, int numberOfMissingObservations)
    {
        int sumObservedValues = 0;
        foreach (int value in rolls)
        {
            sumObservedValues += value;
        }
        return mean * (rolls.Length + numberOfMissingObservations) - sumObservedValues;
    }

    private int[] ExtractMissingValues(int sumOfMissingObservations, int numberOfMissingObservations)
    {
        int[] missingValues = new int[numberOfMissingObservations];
        int roundedDownMeanOfMissingObsarvations = sumOfMissingObservations / numberOfMissingObservations;
        int remainder = sumOfMissingObservations % numberOfMissingObservations;

        for (int i = 0; i < missingValues.Length; ++i)
        {
            missingValues[i] = roundedDownMeanOfMissingObsarvations;
            if (remainder > 0)
            {
                ++missingValues[i];
                --remainder;
            }
        }
        return missingValues;
    }
}
