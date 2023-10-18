package `9375`
import java.util.*
import kotlin.collections.HashMap

fun main() {
    val scanner = Scanner(System.`in`)
    val T = scanner.nextLine().toInt()
    repeat(T) {
        val n = scanner.nextLine().toInt()
        val closet = HashMap<String, Int>()

        repeat(n) {
            val (v, k) = scanner.nextLine().split(" ")
            closet[k] = (closet[k] ?: 0) + 1
        }
        var combo = 1
        closet.forEach { (t, u) ->
            combo *= (u + 1)
        }
        println(combo - 1)
    }
}