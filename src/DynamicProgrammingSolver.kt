import java.util.ArrayList

class DynamicProgrammingSolver(private val items: List<Item>, private val capacity: Int) : KnapsackSolver {

    private var table: Array<IntArray> = Array(capacity+1, { _ -> IntArray(items.size, { -1 }) })
    override fun solve(): KnapsackSolution {
        getCell(capacity, items.size-1)
        return traceTable()
    }

    private fun traceTable(): KnapsackSolution {

        val best = KnapsackSolution()
        best.items = ArrayList()

        var remainingCapacity = capacity

        for (i in items.size-1 downTo 0) {
            val item = items[i]
            val without = if (i == 0) 0 else table[remainingCapacity][i - 1]
            if (table[remainingCapacity][i] != without) {
                (best.items as ArrayList<Item>).add(item)
                best.value += item.value
                best.weight += item.weight
                remainingCapacity -= item.weight
            }
        }
        return best
    }

    private fun getCell(j: Int, i: Int): Int {

        if (i < 0 || j < 0) return 0
        val item = items[i]

        if (table[j][i] == -1) {
            val with = if (item.weight > j) -1 else item.value + getCell(j - item.weight, i - 1)
            val without = getCell(j, i - 1)
            table[j][i] = Math.max(with, without)
        }

        return table[j][i]
    }


}