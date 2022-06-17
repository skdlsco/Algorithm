package clear

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val scanner = Scanner(System.`in`)
    val T = scanner.nextInt()
    val arr = Array<Boolean>(10001) { true }
    arr[0] = false
    arr[1] = false
    (2..100).forEach {
        if (arr[it])
            ((it + it)..(10000) step it).forEach {
                arr[it] = false
            }
    }
    val prime = arr.mapIndexed { idx, _ -> idx }
        .filter { arr[it] }
    repeat(T) {
        val n = scanner.nextInt()
        val result = ArrayList<Pair<Int, Int>>()
        prime.forEachIndexed { idx, p ->
            (idx until prime.size).forEach {
                if (p + prime[it] == n) {
                    result.add(Pair(p, prime[it]))
                    return@forEachIndexed
                }
                if (p + prime[it] > n)
                    return@forEachIndexed
            }
        }
        val minimum = result.minByOrNull { it.second - it.first }
        println("${minimum?.first} ${minimum?.second}")
    }
}