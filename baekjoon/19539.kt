package `19539`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val trees = reader.readLine().split(" ").map { it.toInt() }.sorted()
    var sum = 0
    var twoCnt = 0
    for (i in 0 until N) {
        var cur = trees[i]
        if (trees[i] == 1)
            twoCnt++
        else {
            val useTwo = min(twoCnt, cur / 2)
            cur -= useTwo * 2
            twoCnt -= useTwo
            if (cur == 1)
                twoCnt++
        }
        sum += trees[i]
    }
    if (twoCnt == 0 && sum % 3 == 0)
        writer.write("YES")
    else
        writer.write("NO")
    writer.flush()
}