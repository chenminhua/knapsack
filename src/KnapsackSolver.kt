import java.util.*
import sun.text.normalizer.UTF16.append



//class KnapsackSolver(val items: List<Item>, val capacity: Int) {
//    fun solve() {
//
//    }
//}

class KnapsackSolution {

    var items: List<Item> = ArrayList()
    var weight: Int = 0
    var value: Int = 0

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append(value)
        builder.append(" ")
        builder.append(weight)
        builder.append("\n")

        Collections.sort(items, byLabel())

        for ((label) in items) {
            builder.append(label)
            builder.append(" ")
        }

        return builder.toString()
    }

}

fun getWeight(items: List<Item>) = items.fold(0) { sum, it -> sum + it.weight }

fun getValue(items: List<Item>) = items.fold(0) { sum, it -> sum + it.value }

interface KnapsackSolver {

    fun solve(): KnapsackSolution
}