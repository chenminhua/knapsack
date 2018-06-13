import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*
import java.util.Comparator


fun byLabel(): Comparator<Item> {
    return Comparator { (label1), (label2) -> label1 - label2 }
}

fun byRatio(): Comparator<Item> {
    return Comparator { i1, i2 -> java.lang.Double.compare(i2.getRatio(), i1.getRatio()) }
}

data class Item(var label: Int, var value: Int, var weight: Int) {
    fun getRatio() = value.toDouble() / weight
}

fun main(args: Array<String>) {
    val file = File("src/test")
    val stream = FileInputStream(file)
    val scanner = Scanner(stream)
    val count = scanner.nextInt()

    val items = LinkedList<Item>()
    for (i in 0 until count) {
        val item = Item(scanner.nextInt(), scanner.nextInt(), scanner.nextInt())
        items.add(item)
    }
    val capacity = scanner.nextInt()

    val solver = BruteForceSolver(items, capacity)

    val dSolver = DynamicProgrammingSolver(items, capacity)
    println(solver.solve())

    println(dSolver.solve())

}