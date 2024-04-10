package `Main`

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val line = reader.readLine()
    if (line.startsWith("\"") && line.endsWith("\"") && line.length > 2)
        writer.write("${line.slice(1 until line.lastIndex)}\n")
    else
        writer.write("CE")

    writer.flush()
}