
class Solution {

    private companion object {
        val RANGE_OF_VALUES = intArrayOf(1, 6)
        val NO_VALID_ANSWER = IntArray(0)
    }

    fun missingRolls(rolls: IntArray, mean: Int, numberOfMissingObservations: Int): IntArray {
        val sumOfMissingObservations = calculateSumOfMissingObservations(rolls, mean, numberOfMissingObservations)

        if (sumOfMissingObservations < numberOfMissingObservations * RANGE_OF_VALUES[0]
            || sumOfMissingObservations > numberOfMissingObservations * RANGE_OF_VALUES[1]) {
            return NO_VALID_ANSWER
        }

        return extractMissingValues(sumOfMissingObservations, numberOfMissingObservations)
    }

    private fun calculateSumOfMissingObservations(rolls: IntArray, mean: Int, numberOfMissingObservations: Int): Int {
        var sumObservedValues = 0
        for (value in rolls) {
            sumObservedValues += value
        }
        return mean * (rolls.size + numberOfMissingObservations) - sumObservedValues
    }

    private fun extractMissingValues(sumOfMissingObservations: Int, numberOfMissingObservations: Int): IntArray {
        val missingValues = IntArray(numberOfMissingObservations)
        val roundedDownMeanOfMissingObsarvations = sumOfMissingObservations / numberOfMissingObservations
        var remainder = sumOfMissingObservations % numberOfMissingObservations

        for (i in missingValues.indices) {
            missingValues[i] = roundedDownMeanOfMissingObsarvations
            if (remainder > 0) {
                ++missingValues[i]
                --remainder
            }
        }
        return missingValues
    }
}
