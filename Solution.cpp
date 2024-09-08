
#include <span>
#include <array>
#include <vector>
using namespace std;

/*
 The code will run faster with ios::sync_with_stdio(0).
 However, this should not be used in production code and interactive problems.
 In this particular problem, it is ok to apply ios::sync_with_stdio(0).

 Many of the top-ranked C++ solutions for time on leetcode apply this trick,
 so, for a fairer assessment of the time percentile of my code
 you could outcomment the lambda expression below for a faster run.
*/

/*
 const static auto speedup = [] {
	ios::sync_with_stdio(0);
	return nullptr;
 }();
*/

class Solution {

    static constexpr array<int, 2> RANGE_OF_VALUES{ { 1, 6 } };
    inline static const vector<int> NO_VALID_ANSWER;

public:
    vector<int> missingRolls(const vector<int>& rolls, int mean, int numberOfMissingObservations) const {
        int sumOfMissingObservations = calculateSumOfMissingObservations(rolls, mean, numberOfMissingObservations);

        if (sumOfMissingObservations < numberOfMissingObservations * RANGE_OF_VALUES[0]
            || sumOfMissingObservations > numberOfMissingObservations * RANGE_OF_VALUES[1]) {
            return NO_VALID_ANSWER;
        }

        return extractMissingValues(sumOfMissingObservations, numberOfMissingObservations);
    }

private:
    int calculateSumOfMissingObservations(span <const int> rolls, int mean, int numberOfMissingObservations) const {
        int sumObservedValues = 0;
        for (int value : rolls) {
            sumObservedValues += value;
        }
        return mean * (rolls.size() + numberOfMissingObservations) - sumObservedValues;
    }

    vector<int> extractMissingValues(int sumOfMissingObservations, int numberOfMissingObservations) const {
        vector<int> missingValues(numberOfMissingObservations);
        int roundedDownMeanOfMissingObsarvations = sumOfMissingObservations / numberOfMissingObservations;
        int remainder = sumOfMissingObservations % numberOfMissingObservations;

        for (auto& missingValue : missingValues) {
            missingValue = roundedDownMeanOfMissingObsarvations;
            if (remainder > 0) {
                ++missingValue;
                --remainder;
            }
        }
        return missingValues;
    }
};
