package `2630`
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.collections.ArrayList

fun search(paper: List<List<Boolean>>, len: Int, startX: Int, startY: Int): Pair<Int, Int> {
    if (len == 1)
        return if (paper[startY][startX])
            return Pair(1, 0)
        else Pair(0, 1)
    val nextLen = len / 2
    val result = ArrayList<Pair<Int, Int>>()
    result.add(search(paper, nextLen, startX, startY))
    result.add(search(paper, nextLen, startX + nextLen, startY))
    result.add(search(paper, nextLen, startX, startY + nextLen))
    result.add(search(paper, nextLen, startX + nextLen, startY + nextLen))
    return when {
        result.all { it.first == 0 } -> Pair(0, 1)
        result.all { it.second == 0 } -> Pair(1, 0)
        else -> result.reduce { acc, pair ->
            Pair(acc.first + pair.first, acc.second + pair.second)
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val paper = List<List<Boolean>>(N) { reader.readLine().split(" ").map { it == "0" } }

    val result = search(paper, N, 0, 0)
    println("${result.first} ${result.second}")
}