package `9461`
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val T = scanner.nextInt()
    val arr = Array<Long>(101) { 0 }
    arr[1] = 1
    arr[2] = 1
    (3..100).forEach {
        arr[it] = arr[it - 2] + arr[it - 3]
    }
    repeat(T) {
        println(arr[scanner.nextInt()])
    }
}