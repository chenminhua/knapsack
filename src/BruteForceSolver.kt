import java.util.*
import java.util.LinkedList

fun List<Item>.subsets(): List<List<Item>> {
    val subsets = LinkedList<List<Item>>()
    if (this.isEmpty()) {
        subsets.add(LinkedList())
        return subsets
    }

    val first = this[0]
    val subsubsets = this.subList(1, this.size).subsets()
    for (subset in subsubsets) {
        subsets.add(subset)
        val augmented = LinkedList(subset)
        augmented.add(0, first)
        subsets.add(augmented)
    }
    return subsets

}

class BruteForceSolver(private val items: List<Item>, private val capacity: Int) : KnapsackSolver {
    override fun solve(): KnapsackSolution {
        val sol = KnapsackSolution()

        for (subset in items.subsets()) {
            val weight = getWeight(subset)
            if (weight <= capacity) {
                val value = getValue(subset)
                if (value > sol.value) {
                    sol.value = value
                    sol.weight = weight
                    sol.items = subset
                }
            }
        }
        return sol
    }

}

