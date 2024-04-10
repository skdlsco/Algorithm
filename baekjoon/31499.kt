val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val (N, M) = reader.readLine().split(" ").map { it.toLong() }
    var ans = 1L
    for (i in 1..N) {
        ans *= i
        ans %= M
    }
    writer.write("${ans}\n")
    writer.flush()
}