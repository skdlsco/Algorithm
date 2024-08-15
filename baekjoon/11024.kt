package `Main`

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val N = reader.readLine().toInt()
    repeat(N) {
        val ans = reader.readLine().split(" ").map { it.toInt() }.sum()
        writer.write("${ans}\n")
    }
    writer.flush()
}