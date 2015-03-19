// unrefaktorisierte quelle: https://github.com/emilybache/Yatzy-Refactoring-Kata/blob/master/java/Yatzy.java

assert 0 == twoPairs([1, 2, 3, 4, 5])
assert 6 == twoPairs([1, 1, 2, 2, 5])
assert 4 == twoPairs([1, 1, 1, 1, 5])
assert 4 == twoPairs([1, 1, 1, 1, 1])

def twoPairs(def dice) {
    def pairs = findPairs(dice)
    return scorePairs(pairs)
}

def findPairs(def dice) {
    def groupedDice = groupByDiceValue(dice)
    def candidates = selectPairCandidates(groupedDice)
    return splitQuads(candidates)
}

def groupByDiceValue(List<Integer> dice) {
    return dice.groupBy { it }.collect { it.value }
}

def selectPairCandidates(def groupedDice) {
    return groupedDice.findAll { it.size() >= 2 }
}

List<Integer> splitQuads(List<List<Integer>> candidates) {
    candidates.collectMany {
        if (it.size() >= 4)
            return [it[0], it[0]]
        else
            return [it[0]]
    }
}

def scorePairs(List<Integer> pairs) {
    pairs.isEmpty() ? 0 : pairs.collect { 2 * it }.sum()
}