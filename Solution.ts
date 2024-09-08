
function missingRolls(rolls: number[], mean: number, numberOfMissingObservations: number): number[] {
    const RANGE_OF_VALUES = [1, 6];
    const NO_VALID_ANSWER = [];

    const sumOfMissingObservations = calculateSumOfMissingObservations(rolls, mean, numberOfMissingObservations);

    if (sumOfMissingObservations < numberOfMissingObservations * RANGE_OF_VALUES[0]
        || sumOfMissingObservations > numberOfMissingObservations * RANGE_OF_VALUES[1]) {
        return NO_VALID_ANSWER;
    }

    return extractMissingValues(sumOfMissingObservations, numberOfMissingObservations);
};

function calculateSumOfMissingObservations(rolls: number[], mean: number, numberOfMissingObservations: number): number {
    let sumObservedValues = 0;
    for (let value of rolls) {
        sumObservedValues += value;
    }
    return mean * (rolls.length + numberOfMissingObservations) - sumObservedValues;
}

function extractMissingValues(sumOfMissingObservations: number, numberOfMissingObservations: number): number[] {
    const missingValues: number[] = new Array(numberOfMissingObservations);
    const roundedDownMeanOfMissingObsarvations = Math.floor(sumOfMissingObservations / numberOfMissingObservations);
    let remainder = sumOfMissingObservations % numberOfMissingObservations;

    for (let i = 0; i < missingValues.length; ++i) {
        missingValues[i] = roundedDownMeanOfMissingObsarvations;
        if (remainder > 0) {
            ++missingValues[i];
            --remainder;
        }
    }
    return missingValues;
}
