import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val M = reader.readLine().toInt()
    val set = HashSet<Int>()
    repeat(M) {
        val line = reader.readLine().split(" ")
        val command = line[0]
        val num = (line.getOrNull(1)?: "0").toInt()
        when (command) {
            "add" -> {
                set.add(num)
            }
            "remove" -> {
                set.remove(num)
            }
            "check" -> {
                val result = if (set.contains(num)) "1" else "0"
                writer.write(result)
                writer.newLine()
            }
            "toggle" -> {
                if (set.contains(num))
                    set.remove(num)
                else
                    set.add(num)
            }
            "all" -> {
                set.addAll((1..20))
            }
            "empty" -> {
                set.clear()
            }
        }
    }
    writer.flush()
}