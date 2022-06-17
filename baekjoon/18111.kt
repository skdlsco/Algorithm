import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, B) = bufferedReader.readLine().split(" ").map { it.toInt() }
    val arr = ArrayList<Int>()
    repeat(N) {
        val stringTokenizer = StringTokenizer(bufferedReader.readLine())
        repeat(M) {
           arr.add(stringTokenizer.nextToken().toInt())
        }
    }
    val maxHeight = arr.maxOf { it }
    var result = Pair(Int.MAX_VALUE, 0)
    (0..maxHeight).forEach { target ->
        var blockCnt = B
        var time = 0
        arr.forEach {
            if (it > target) {
                blockCnt += it - target
                time += 2 * (it - target)
            } else if (it < target) {
                blockCnt -= target - it
                time += target - it
            }
        }
        if (blockCnt >= 0 && time <= result.first)
            result = Pair(time, target)
    }
    println("${result.first} ${result.second}")
}
