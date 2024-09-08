
package main

import "fmt"

var RANGE_OF_VALUES = [2]int{1, 6}
var NO_VALID_ANSWER []int

func missingRolls(rolls []int, mean int, numberOfMissingObservations int) []int {
    sumOfMissingObservations := calculateSumOfMissingObservations(rolls, mean, numberOfMissingObservations)

    if sumOfMissingObservations < numberOfMissingObservations*RANGE_OF_VALUES[0] ||
        sumOfMissingObservations > numberOfMissingObservations*RANGE_OF_VALUES[1] {
        return NO_VALID_ANSWER
    }

    return extractMissingValues(sumOfMissingObservations, numberOfMissingObservations)
}

func calculateSumOfMissingObservations(rolls []int, mean int, numberOfMissingObservations int) int {
    var sumObservedValues = 0
    for _, value := range rolls {
        sumObservedValues += value
    }
    return mean*(len(rolls)+numberOfMissingObservations) - sumObservedValues
}

func extractMissingValues(sumOfMissingObservations int, numberOfMissingObservations int) []int {
    missingValues := make([]int, numberOfMissingObservations)
    roundedDownMeanOfMissingObsarvations := sumOfMissingObservations / numberOfMissingObservations
    var remainder = sumOfMissingObservations % numberOfMissingObservations

    for i := range missingValues {
        missingValues[i] = roundedDownMeanOfMissingObsarvations
        if remainder > 0 {
            missingValues[i]++
            remainder--
        }
    }
    return missingValues
}
