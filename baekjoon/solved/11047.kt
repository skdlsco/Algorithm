package `11047`
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var (N, K) = scanner.nextLine().split(" ").map { it.toInt() }
    val coins = List<Int>(N) { scanner.nextInt() }.reversed()
    var cnt = 0
    coins.forEach {
        if (it <= K) {
            val c = K / it
            cnt += c
            K -= c * it
        }
    }
    println(cnt)
}