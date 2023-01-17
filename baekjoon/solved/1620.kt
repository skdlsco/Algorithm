import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val scanner = Scanner(System.`in`)
    val arr = ArrayList<String>()
    val map = HashMap<String, Int>()

    val (N, M) = scanner.nextLine().split(" ").map { it.toInt() }

    repeat(N) {
        val name = scanner.nextLine()
        arr.add(name)
        map[name] = it
    }

    repeat(M) {
        val value = scanner.nextLine()
        value.toIntOrNull()?.let {
            println(arr[it - 1])
        }?: println(map[value]!! + 1)
    }
}