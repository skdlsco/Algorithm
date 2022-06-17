import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()

    repeat(T) {
        val commands = reader.readLine()
        val arrSize = reader.readLine().toInt()
        val arrString = reader.readLine().removePrefix("[").removeSuffix("]").split(",")
        var isReversed = false
        var start = 0
        var end = arrSize
        commands.forEach {
            when (it) {
                'R' -> {
                    isReversed = !isReversed
                }
                'D' -> {
                    if (isReversed)
                        end--
                    else
                        start++
                }
            }
        }
        if (start <= end) {
            val result = arrString.subList(start, end).let {
                if (isReversed)
                    it.reversed()
                else
                    it
            }
            writer.write(result.joinToString(",", "[", "]"))
        } else
            writer.write("error")
        writer.newLine()
    }
    writer.flush()
}