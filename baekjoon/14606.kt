val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val N = reader.readLine().toInt()
    writer.write("${(1 until N).sum()}\n")
    writer.flush()
}

