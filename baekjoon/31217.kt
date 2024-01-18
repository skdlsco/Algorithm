package `main`

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()
val MOD = 1000000007

fun main() {
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(n + 1) { ArrayList() }
    repeat(m) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }
    var sum = 0L
    for (i in 1..n) {
        if (graph[i].size >= 3) {
            sum += graph[i].size * (graph[i].size - 1) * (graph[i].size - 2) / 6
            sum %= MOD
        }
    }
    writer.write("${sum}\n")
    writer.flush()
}
    