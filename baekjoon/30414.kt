import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

var N = 0
var P = 0

val init = Array<Int>(100001) { 0 }
val goal = Array<Int>(100001) { 0 }
val graph = Array<ArrayList<Int>>(100001) { ArrayList() }

// return 사용 흙, 구매 흙
fun dfs(prev: Int, cur: Int, x: Int): Pair<Int, Int> {
    val diff = init[cur] - goal[cur]
    var curDirt = diff + x
    var money = 0
    for (next in graph[cur]) {
        if (prev == next)
            continue
        val (use, buy) = dfs(cur, next, maxOf(curDirt, 0))
        money += buy
        curDirt -= use
    }
    if (curDirt < 0) {
        money += -curDirt
        curDirt = 0
    }
    return Pair(maxOf(x - curDirt, 0), money)
}

fun main() {
    val reader = MyReader(BufferedReader(InputStreamReader(System.`in`)))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    N = reader.nextInt()
    P = reader.nextInt()
    for (i in 1..N) {
        init[i] = reader.nextInt()
    }
    for (i in 1..N) {
        goal[i] = reader.nextInt()
    }
    repeat(N - 1) {
        val u = reader.nextInt()
        val v = reader.nextInt()
        graph[u].add(v)
        graph[v].add(u)
    }
    val ans = dfs(0, P, 0)
    writer.write("${ans.second}\n")
    writer.flush()
}

class MyReader(val reader: BufferedReader) {
    var line: StringTokenizer? = null

    fun nextToken(): String {
        while (line == null || !line!!.hasMoreTokens())
            line = StringTokenizer(reader.readLine())
        return line!!.nextToken()
    }

    fun nextInt(): Int {
        return nextToken().toInt()
    }
}