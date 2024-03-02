package `A`

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val A = reader.readLine().toInt()
    val B = reader.readLine().toInt()
    val C = reader.readLine().toInt()
    writer.write("${A + B - C}\n")
    writer.write("${(A.toString() + B.toString()).toInt() - C}")
    writer.flush()
}
    